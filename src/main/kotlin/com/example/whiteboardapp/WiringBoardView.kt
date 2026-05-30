package com.example.whiteboardapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class WiringBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 5f
        isAntiAlias = true
    }

    private var backgroundColor = Color.WHITE
    private var paths = mutableListOf<DrawPath>()
    private var currentPath = Path()
    private var lastX = 0f
    private var lastY = 0f
    private var zoom = 1f
    private var panX = 0f
    private var panY = 0f

    // Drawing modes
    enum class DrawingMode {
        PEN, PENCIL, ERASER
    }

    private var drawingMode = DrawingMode.PEN

    data class DrawPath(
        val path: Path,
        val paint: Paint,
        val mode: DrawingMode
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Fill background
        canvas.drawColor(backgroundColor)

        // Apply transformations
        canvas.save()
        canvas.translate(panX, panY)
        canvas.scale(zoom, zoom)

        // Draw all paths
        for (drawPath in paths) {
            val pathPaint = Paint(drawPath.paint)
            when (drawPath.mode) {
                DrawingMode.ERASER -> {
                    pathPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                }
                else -> {
                    pathPaint.xfermode = null
                }
            }
            canvas.drawPath(drawPath.path, pathPaint)
        }

        // Draw current path
        if (drawingMode != DrawingMode.ERASER) {
            canvas.drawPath(currentPath, paint)
        } else {
            val eraserPaint = Paint(paint).apply {
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
            canvas.drawPath(currentPath, eraserPaint)
        }

        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = (event.x - panX) / zoom
        val y = (event.y - panY) / zoom

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(x, y)
                lastX = x
                lastY = y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // Use quadratic bezier for smooth curves
                val midX = (lastX + x) / 2
                val midY = (lastY + y) / 2
                currentPath.quadTo(lastX, lastY, midX, midY)
                lastX = x
                lastY = y
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                currentPath.lineTo(x, y)
                paths.add(DrawPath(currentPath, Paint(paint), drawingMode))
                currentPath = Path()
                invalidate()
                return true
            }
        }
        return false
    }

    // Public methods to control the view
    fun setDrawingMode(mode: DrawingMode) {
        drawingMode = mode
    }

    fun setBrushColor(color: Int) {
        paint.color = color
    }

    fun setBrushSize(size: Float) {
        paint.strokeWidth = size
    }

    fun setBackgroundBoardColor(color: Int) {
        backgroundColor = color
        invalidate()
    }

    fun clearCanvas() {
        paths.clear()
        currentPath = Path()
        invalidate()
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            invalidate()
        }
    }

    fun zoom(factor: Float) {
        zoom *= factor
        invalidate()
    }

    fun pan(dx: Float, dy: Float) {
        panX += dx
        panY += dy
        invalidate()
    }

    fun setOpacity(alpha: Int) {
        paint.alpha = alpha
    }

    fun getPaths(): List<DrawPath> = paths.toList()
}
