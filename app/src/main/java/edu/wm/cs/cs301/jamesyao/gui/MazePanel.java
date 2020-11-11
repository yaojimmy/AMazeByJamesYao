package edu.wm.cs.cs301.jamesyao.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MazePanel extends View implements P5Panel{

    // set paints for each needed color
    private Paint mazePaint; // temporary until I can figure out what Paint objects are necessary
    private Canvas mazeCanvas; // internal canvas that is drawn on until it needs to be sent
    private Bitmap bitmap; // internal bitmap
    private int bitmapWidth; // stores bitmap width for creating bitmap
    private int bitmapHeight; // stores bitmap height for creating bitmap

    public MazePanel() {
        super(null);
    }

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

        myTestImage(canvas);
    }

    private void myTestImage(Canvas c) {
        // set mazeCanvas equal to canvas so it appears during test
        mazeCanvas = c;

        // paint red ball
        mazePaint.setColor(Color.RED);
        addFilledOval(0, 0, 100, 100);

        // paint green ball
        mazePaint.setColor(Color.GREEN);
        addFilledOval(120, 0, 100, 100);

        // paint yellow rectangle
        mazePaint.setColor(Color.YELLOW);
        addFilledRectangle(240, 0, 150, 100);

        // paint blue polygon
        mazePaint.setColor(Color.BLUE);
        int[] xPoints = new int[] {120, 240, 180};
        int[] yPoints = new int[] {120, 120, 240};
        addFilledPolygon(xPoints, yPoints, 3);

        // paint some lines
        mazePaint.setColor(Color.MAGENTA);
        addLine(50, 250, 350, 250);
        mazePaint.setColor(Color.LTGRAY);
        addLine(50, 350, 300, 270);
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

    public void update(Canvas c) {
        paint(c);
    }
    /**
     * Method to draw the bitmap on a canvas object that is
     * obtained from the superclass.
     * Warning: do not override getGraphics() or drawing might fail.
     */
    public void update() {
        paint(mazeCanvas);
    }

    /**
     * Draws the bitmap to the given canvas object.
     * This method is called when this panel should redraw itself.
     * The given canvas object is the one that actually shows
     * on the screen.
     */
    public void paint(Canvas c) {

        if (null == c) {
            System.out.println("MazePanel.paint: no canvas object, skipping drawImage operation");
        }
        else {
            RectF rect = new RectF();
            c.drawBitmap(bitmap, null, rect, mazePaint);
        }
    }

    /**
     * Obtains a canvas object that can be used for drawing.
     * This MazePanel object internally stores the canvas object
     * and will return the same graphics object over multiple method calls.
     * The canvas object acts like a notepad where all clients draw
     * on to store their contribution to the overall image that is to be
     * delivered later.
     * To make the drawing visible on screen, one needs to trigger
     * a call of the paint method, which happens
     * when calling the update method.
     * @return canvas object to draw on, null if impossible to obtain image
     */
    public Canvas getBufferGraphics() {

        // if necessary instantiate and store a graphics object for later use
        if (null == mazeCanvas) {
            if (null == bitmap) {
                bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
                //bitmap = Bitmap.createBitmap(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
                if (null == bitmap)
                {
                    System.out.println("Error: creation of bitmap failed, presumedly container not displayable");
                    return null; // still no buffer image, give up
                }
            }
            mazeCanvas = new Canvas(bitmap);
            // do rest when find equivalent of renderinghints

            //if (null == mazeCanvas) {
            //    System.out.println("Error: creation of canvas for buffered image failed, presumedly container not displayable");
            //}
            //else {
                // System.out.println("MazePanel: Using Rendering Hint");
                // For drawing in FirstPersonDrawer, setting rendering hint
                // became necessary when lines of polygons
                // that were not horizontal or vertical looked ragged
            /*
                setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);

             */
            //}
        }
        return mazeCanvas;


    }

    @Override
    public void commit() {

    }

    @Override
    public boolean isOperational() {
        if (mazeCanvas != null) {
            return true;
        }
        return false;
    }

    private Color col;

    /**
     * @param rgb value of color
     */
    @Override
    public void setColor(int rgb) {
        mazePaint.setColor(rgb);
    }

    /**
     * sets Color with rgba value
     * @param rgba value
     */
    public void setColor(float[] rgba) {
        mazePaint.setARGB((int)rgba[3], (int)rgba[0], (int)rgba[1], (int)rgba[2]);
    }

    /**
     * @return rgb value of color
     */
    @Override
    public int getColor() {
        return mazePaint.getColor();
    }

    /**
     * @param decode color to be decoded
     * @return rgb value of decoded color
     */
    public static int getColor(String decode) {
        return Color.parseColor(decode);
    }

    public enum CommonColors {
        WHITE,
        GRAY,
        BLACK,
        RED,
        YELLOW,
        GREEN
    }
    /**
     * @param color an enumerated common color (for convenience)
     * @return
     */
    public static int getColor(CommonColors color) {
        switch (color) {
            case WHITE:
                return Color.WHITE;
            case GRAY:
                return Color.GRAY;
            case BLACK:
                return Color.BLACK;
            case RED:
                return Color.RED;
            case YELLOW:
                return Color.YELLOW;
            case GREEN:
                return Color.GREEN;
        }
        return 0;
    }

    // for now, not dealing with fonts
    /**
     *
     * @param fontCode
     * @return font name
     */
    public static String getFontName(String fontCode) {
        //return Font.decode(fontCode).getFontName();
        return "";
    }

    /**
     *
     * @param fontCode
     * @return font style int
     */
    public static int getFontStyle(String fontCode) {
        //return Font.decode(fontCode).getStyle();
        return 0;
    }

    /**
     *
     * @param fontCode
     * @return font size
     */
    public static int getFontSize(String fontCode) {
        //return Font.decode(fontCode).getSize();
        return 0;
    }

    // private Font fields
    private String fontName;
    private int fontStyle;
    private int fontSize;

    public void setFontName(String fn) {
        //fontName = fn;
    }

    public void setFontStyle(int fstyle) {
        //fontStyle = fstyle;
    }

    public void setFontSize(int fsize) {
        //fontSize = fsize;
    }

    /**
     * Default minimum value for RGB values.
     * For Wall class
     */
    private static final int RGB_DEF = 20;
    private static final int RGB_DEF_GREEN = 60;

    @Override
    public int getWallColor(int distance, int cc, int extensionX) {
        // compute rgb value, depends on distance and x direction
        final int part1 = distance & 7;
        final int add = (extensionX != 0) ? 1 : 0;
        final int rgbValue = ((part1 + 2 + add) * 70) / 8 + 80;
        int col;
        switch (((distance >> 3) ^ cc) % 6) {
            case 0:
                col = Color.rgb(rgbValue, RGB_DEF, RGB_DEF);
                break;
            case 1:
                col = Color.rgb(RGB_DEF, RGB_DEF_GREEN, RGB_DEF);
                break;
            case 2:
                col = Color.rgb(RGB_DEF, RGB_DEF, rgbValue);
                break;
            case 3:
                col = Color.rgb(rgbValue, RGB_DEF_GREEN, RGB_DEF);
                break;
            case 4:
                col = Color.rgb(RGB_DEF, RGB_DEF_GREEN, rgbValue);
                break;
            case 5:
                col = Color.rgb(rgbValue, RGB_DEF, rgbValue);
                break;
            default:
                col = Color.rgb(RGB_DEF, RGB_DEF, RGB_DEF);
                break;
        }
        return col;
    }

    /**
     * Draws two solid rectangles to provide a background.
     * Note that this also erases previous drawings of maze or map.
     * The color setting adjusts to the distance to the exit to
     * provide an additional clue for the user.
     * Colors transition from black to gold and from grey to green.
     * @param percentToExit gives the distance to exit
     */
    @Override
    public void addBackground(float percentToExit) {
        // black rectangle in upper half of screen
        mazePaint.setColor(getBackgroundColor(percentToExit, true));
        mazePaint.setStyle(Paint.Style.FILL);
        mazeCanvas.drawRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT/2, mazePaint);
        // grey rectangle in lower half of screen
        mazePaint.setColor(getBackgroundColor(percentToExit, false));
        mazeCanvas.drawRect(0, Constants.VIEW_HEIGHT/2, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT/2, mazePaint);
    }

    // colors for background
    static final int greenWM = Color.parseColor("#115740");
    static final int goldWM = Color.parseColor("#916f41");
    static final int yellowWM = Color.parseColor("#FFFF99");

    /**
     * Determine the background color for the top and bottom
     * rectangle as a blend between starting color settings
     * of black and grey towards gold and green as final
     * color settings close to the exit
     * @param percentToExit percent to exit
     * @param top is true for the top triangle, false for the bottom
     * @return the color to use for the background rectangle
     */
    private int getBackgroundColor(float percentToExit, boolean top) {
        return top? blend(yellowWM, goldWM, percentToExit) :
                blend(Color.LTGRAY, greenWM, percentToExit);
    }

    /**
     * Calculates the weighted average of the two given colors
     * @param c0 the one color
     * @param c1 the other color
     * @param weight0 of c0
     * @return blend of colors c0 and c1 as weighted average
     */
    private int blend(int c0, int c1, double weight0) {
        if (weight0 < 0.1)
            return c1;
        if (weight0 > 0.95)
            return c0;
        double r = weight0 * Color.red(c0) + (1-weight0) * Color.red(c1);
        double g = weight0 * Color.green(c0) + (1-weight0) * Color.green(c1);
        double b = weight0 * Color.blue(c0) + (1-weight0) * Color.blue(c1);
        double a = Math.max(Color.alpha(c0), Color.alpha(c1));

        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }

    @Override
    public void addFilledRectangle(int x, int y, int width, int height) {
        mazePaint.setStyle(Paint.Style.FILL);
        mazeCanvas.drawRect((float)x, (float)y, (float)x+width, (float)y+height, mazePaint);
    }

    private final Path polygonpath = new Path();

    @Override
    public void addFilledPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        mazePaint.setStyle(Paint.Style.FILL);

        // makes polygon through polygon path
        polygonpath.reset();
        polygonpath.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < nPoints; i++) {
            polygonpath.lineTo(xPoints[i], yPoints[i]);
        }
        polygonpath.lineTo(xPoints[0], yPoints[0]);
        mazeCanvas.drawPath(polygonpath, mazePaint);
    }

    @Override
    public void addPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        mazePaint.setStyle(Paint.Style.STROKE);

        // makes polygon through polygon path
        polygonpath.reset();
        polygonpath.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < nPoints; i++) {
            polygonpath.lineTo(xPoints[i], yPoints[i]);
        }
        polygonpath.lineTo(xPoints[0], yPoints[0]);
        mazeCanvas.drawPath(polygonpath, mazePaint);
    }

    @Override
    public void addLine(int startX, int startY, int endX, int endY) {
        mazeCanvas.drawLine(startX, startY, endX, endY, mazePaint);
    }

    @Override
    public void addFilledOval(int x, int y, int width, int height) {
        mazePaint.setStyle(Paint.Style.FILL);
        mazeCanvas.drawOval(x, y, x+width, y+height, mazePaint);
    }

    @Override
    public void addArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        // useCenter is false for now, will change to true if there is supposed to be a center
        mazeCanvas.drawArc(x, y, x+width, y+height, startAngle, arcAngle, false, mazePaint);
    }

    @Override
    public void addMarker(float x, float y, String str) {
        /*
        Font f = new Font(fontName, fontStyle, fontSize);
        GlyphVector gv = f.createGlyphVector(graphics.getFontRenderContext(), str);
        Rectangle2D rect = gv.getVisualBounds();

        x -= rect.getWidth() / 2;
        y += rect.getHeight() / 2;

        graphics.drawGlyphVector(gv, x, y);
        */
        mazeCanvas.drawText(str, x, y, mazePaint);
    }

    /*
     * uses the java.awt.RenderingHints keys and values that correspond to the enumerated values
     * @param hintKey an enumerated RenderingHints key
     * @param hintValue an enumerated RenderingHints value
     */
    @Override
    public void setRenderingHint(RenderingHints hintKey, RenderingHints hintValue) {
        /*
        switch (hintKey) {
            case KEY_RENDERING:
                if (hintValue == RenderingHints.VALUE_ANTIALIAS_ON) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                }
                else if (hintValue == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                }
                else {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
                }
                break;
            case KEY_INTERPOLATION:
                if (hintValue == RenderingHints.VALUE_ANTIALIAS_ON) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                }
                else if (hintValue == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                }
                else {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
                }
                break;
            case KEY_ANTIALIASING:
                if (hintValue == RenderingHints.VALUE_ANTIALIAS_ON) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                }
                else if (hintValue == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                }
                else {
                    graphics.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
                }
                break;
            default:
                break;

        }

         */
    }
}
