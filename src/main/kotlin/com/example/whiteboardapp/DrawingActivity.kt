package com.example.whiteboardapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class DrawingActivity : AppCompatActivity() {

    private lateinit var wiringBoard: WiringBoardView
    private lateinit var sizeSeekbar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        wiringBoard = findViewById(R.id.wiring_board)
        sizeSeekbar = findViewById(R.id.size_seekbar)

        // Board colors
        findViewById<Button>(R.id.btn_black).setOnClickListener {
            wiringBoard.setBackgroundBoardColor(Color.BLACK)
        }
        findViewById<Button>(R.id.btn_green).setOnClickListener {
            wiringBoard.setBackgroundBoardColor(Color.GREEN)
        }
        findViewById<Button>(R.id.btn_white).setOnClickListener {
            wiringBoard.setBackgroundBoardColor(Color.WHITE)
        }

        // Drawing tools
        findViewById<Button>(R.id.btn_pen).setOnClickListener {
            wiringBoard.setDrawingMode(WiringBoardView.DrawingMode.PEN)
            wiringBoard.setBrushColor(Color.BLACK)
        }
        findViewById<Button>(R.id.btn_pencil).setOnClickListener {
            wiringBoard.setDrawingMode(WiringBoardView.DrawingMode.PENCIL)
            wiringBoard.setBrushColor(Color.GRAY)
        }
        findViewById<Button>(R.id.btn_eraser).setOnClickListener {
            wiringBoard.setDrawingMode(WiringBoardView.DrawingMode.ERASER)
        }

        // Zoom controls
        findViewById<Button>(R.id.btn_zoom_in).setOnClickListener {
            wiringBoard.zoom(1.2f)
        }
        findViewById<Button>(R.id.btn_zoom_out).setOnClickListener {
            wiringBoard.zoom(0.8f)
        }

        // Clear and Undo
        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            wiringBoard.clearCanvas()
        }
        findViewById<Button>(R.id.btn_undo).setOnClickListener {
            wiringBoard.undo()
        }

        // Size control
        sizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                wiringBoard.setBrushSize(progress.toFloat() + 2)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
