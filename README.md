# Whiteboard Drawing App

A fully functional Android whiteboard/drawing application with multiple drawing tools and features.

## Features

✏️ **Drawing Tools**
- Pen: Black ink drawing
- Pencil: Gray pencil drawing
- Eraser: Remove drawn content

🎨 **Board Colors**
- White Board
- Black Board
- Green Board

🔍 **Zoom Controls**
- Zoom In: Increase canvas size
- Zoom Out: Decrease canvas size

🔧 **Utilities**
- Brush Size Control: Adjustable via SeekBar (2-52 pixels)
- Clear: Clear entire canvas
- Undo: Remove last drawn stroke

## Project Structure

```
WhiteboardApp/
├── src/main/
│   ├── kotlin/com/example/whiteboardapp/
│   │   ├── DrawingActivity.kt       # Main activity with UI controls
│   │   └── WiringBoardView.kt       # Custom drawing view
│   └── res/
│       └── layout/
│           └── activity_drawing.xml  # UI layout
├── build.gradle.kts                  # Build configuration
└── AndroidManifest.xml               # App manifest
```

## Technical Details

### WiringBoardView
- Custom View with touch event handling
- Quadratic Bezier curves for smooth drawing
- Supports multiple drawing modes (Pen, Pencil, Eraser)
- Zoom and pan functionality
- Path history for undo feature

### DrawingActivity
- Manages UI controls and button listeners
- Coordinates with WiringBoardView for user interactions
- SeekBar listener for brush size adjustment

## Requirements

- Android SDK 21+
- Kotlin 1.9+
- Android Gradle Plugin 8.0+

## Installation

1. Clone the repository
2. Open in Android Studio
3. Build and run on an Android device or emulator

## Usage

1. Launch the app
2. Select a drawing tool (Pen, Pencil, or Eraser)
3. Adjust brush size using the SeekBar
4. Draw on the canvas
5. Change board colors as needed
6. Use Zoom controls to navigate
7. Use Clear to reset or Undo for the last stroke

## Future Enhancements

- Color picker for custom colors
- Save/load drawings
- Multiple canvas layers
- Shape tools (rectangle, circle, line)
- Text tool
- Export as image
- Dark mode support

## License

MIT License
