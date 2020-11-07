package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MazePanel extends View {

    private Paint mazePaint; // temporary until I can figure out what Paint objects are necessary

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
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    }
}
