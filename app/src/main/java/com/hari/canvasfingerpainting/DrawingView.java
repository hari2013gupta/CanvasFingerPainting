package com.hari.canvasfingerpainting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {

    //hari
    private static final String TAG = "Desenho";
    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0x00000000, paintAlpha = 255;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    //brush sizes
    private float brushSize, lastBrushSize;
    private boolean erase = false;
    private ShapeDrawable rectangle;
    private Paint paint;
    private float currX, currY;
    private Rect blue, gray;

    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();

        currX = 1;
        currY = 1;

        gray = new Rect(50, 30, 200, 150);
        blue = new Rect(200, 200, 400, 350);

        paint = new Paint();
        rectangle = new ShapeDrawable(new RectShape());

    }

    //setup drawing
    private void setupDrawing() {

        //prepare for drawing and setup paint stroke properties
        brushSize = getResources().getInteger(R.integer.small_size);
        lastBrushSize = brushSize;
        drawPath = new Path();
        paths.add(drawPath);
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    //size assigned to view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    //draw the view - will be called after touch event
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);

//		canvas.drawRect(30, 30, 100, 100, drawPaint);
///////////////////////////////
//        drawPaint.setStyle(Paint.Style.FILL);
//        drawPaint.setColor(Color.WHITE);
//
//        //Custom View
//        rectangle.getPaint().setColor(Color.GRAY);
//        rectangle.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
//        rectangle.getPaint().setStrokeWidth(3);
//        gray.set((int)(50+currX), (int)(30+currY), (int)(200+currX), (int)(150+currY));
//        rectangle.setBounds(gray);
//        gray = rectangle.getBounds();
//        rectangle.draw(canvas);
//
//        rectangle.getPaint().setColor(Color.BLUE);
//        rectangle.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
//        rectangle.getPaint().setStrokeWidth(3);
//        blue.set((int)(200+currX), (int)(200+currY), (int)(400+currX), (int)(350+currY));
//        rectangle.setBounds(blue);
//        blue = rectangle.getBounds();
//        rectangle.draw(canvas);
        ////////////////////////
//		   canvas.drawColor(Color.WHITE);
//
//		      String blackText = "black";
//		      String redText = " red";
//
//		      Paint mPaint = new Paint();
//		      mPaint.setAntiAlias(true);
//		      mPaint.setTextSize(30);
//		      mPaint.setTypeface(Typeface.create(Typeface.SERIF,
//		          Typeface.ITALIC));
//
//		      float canvasWidth = canvas.getWidth();
//		      float blackTextWidth = mPaint.measureText(blackText);
//		      float sentenceWidth = mPaint.measureText(blackText + redText);
//		      float startPositionX = (canvasWidth - sentenceWidth) / 2;
//
//		      mPaint.setTextAlign(Paint.Align.LEFT);
//		      canvas.translate(0, 80);
//
//		      mPaint.setColor(Color.BLACK);
//		      canvas.drawText(blackText, startPositionX, 0, mPaint);
//		      mPaint.setColor(Color.RED);
//		      canvas.drawText(redText, startPositionX + blackTextWidth, 0,mPaint);


    }

    //register user touches as drawing action
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        //respond to down, move and up events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //redraw
        invalidate();
        return true;

    }

    //update color
    public void setColor(String newColor) {
        invalidate();
        //check whether color value or pattern name
        if (newColor.startsWith("#")) {
            paintColor = Color.parseColor(newColor);
            drawPaint.setColor(paintColor);
            drawPaint.setShader(null);

        } else {
            int patternID = getResources().getIdentifier(
                    newColor, "drawable", "com.hari.canvasfingerpainting");
            //decode
            Bitmap patternBMP = BitmapFactory.decodeResource(getResources(), patternID);
            //create shader
            BitmapShader patternBMPshader = new BitmapShader(patternBMP,
                    Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            //color and shader
            drawPaint.setColor(0xffffffff);
            drawPaint.setShader(patternBMPshader);
        }
    }

    //set brush size
    public void setBrushSize(float newSize) {
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public float getLastBrushSize() {
        return lastBrushSize;
    }

    //get and set last brush size
    public void setLastBrushSize(float lastSize) {
        lastBrushSize = lastSize;
    }

    //set erase true or false
    public void setErase(boolean isErase) {
        erase = isErase;
        if (erase) {

//			drawPaint = new Paint(); 
//			drawPaint.setAntiAlias(true); 
//			drawPaint.setColor(Color.TRANSPARENT); 
//			drawPaint.setStyle(Paint.Style.FILL); 
/////////////////////hariJ_experim
//			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));


//            LogService.log("PaintActivity", "------in eraseListener");
//			drawPaint = new Paint(); 
//			drawPaint.setAntiAlias(true); 
//			drawPaint.setColor(Color.TRANSPARENT); 
//			drawPaint.setStyle(Paint.Style.FILL); 
//			 
//			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR)); 
//			 
//			drawPaint.setXfermode(null); 

            //			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//			drawPaint.setAlpha(0xFF);//transperent color
///////////////////////endhariexperimnt			

//			drawCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//			drawPaint.setAlpha(Color.TRANSPARENT);
//			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//			drawPaint.setColor(Color.BLUE);
        } else drawPaint.setXfermode(null);
    }

    public void startNew() {
        drawPaint.setColor(Color.BLACK);//harijugd
        drawCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    //return current alpha
    public int getPaintAlpha() {
        return Math.round((float) paintAlpha / 255 * 100);
    }

    //set alpha
    public void setPaintAlpha(int newAlpha) {
        paintAlpha = Math.round((float) newAlpha / 100 * 255);
        drawPaint.setColor(paintColor);
        drawPaint.setAlpha(paintAlpha);
    }

    /////////////////////////hari_start_fucntiopns///////////////////
    public Bitmap getBitmap() {
        return canvasBitmap;
    }

    //    public void clearPoints() {
//        DrawingView points = null;
//		points.clear();
//        invalidate();
//    }
    public void onClickUndo() {
        if (paths.size() > 0) {
            undonePaths.add(paths.remove(paths.size() - 1));
            invalidate();
        } else {

        }

    }

    public void onClickRedo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            invalidate();
        } else {

        }
    }

    ///////////////////////////////////////////////////////////////
    class Point {
        float x, y;

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }

}
