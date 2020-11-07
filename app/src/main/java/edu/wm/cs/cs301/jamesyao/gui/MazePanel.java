package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MazePanel extends View {

    // set paints for each needed color
    private Paint mazePaint; // temporary until I can figure out what Paint objects are necessary
    private Canvas mazeCanvas; // internal canvas that is drawn on until it needs to be sent
    private Bitmap bitmap; // internal bitmap
    private int bitmapWidth; // stores bitmap width for creating bitmap
    private int bitmapHeight; // stores bitmap height for creating bitmap

    public MazePanel(Context context) {
        super(context);

        init(null);
    }

    public MazePanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public MazePanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public MazePanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    /**
     * Called in constructor
     * Creates necessary Paint objects and sets attributes
     */
    private void init(@Nullable AttributeSet attrs) {
        // assigns (temporary) mazePaint object, have not assigned attributes yet
        mazePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // does whatever it's usually supposed to do first
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // does whatever it's usually supposed to do first
        super.onSizeChanged(w, h, oldw, oldh);

        // creates and assigns bitmap, then uses it to create and assign the canvas
        // creates bitmap from calculated width and height
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        mazeCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        bitmapWidth = MeasureSpec.getSize(widthMeasureSpec);
        bitmapHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
