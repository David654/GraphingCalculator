package application.grid;

import application.functions.Function;
import application.graphics.ShapeRenderer;
import application.graphics.color.BasicColors;
import application.graphics.color.GLColor;
import application.math.vector.Vector2;
import application.settings.GraphicsSettings;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoordinateGrid
{
    private Grid grid;

    private int gridWidth;
    private int gridHeight;
    private final double aspect = 1.7777777777778f;

    private GLColor axisColor;
    private GLColor majorGridLineColor;
    private GLColor minorGridLineColor;

    private float majorUnit = 0.1f;
    private float minorUnit = 0.02f;
    private final float arrowSize = 0.01f;
    private float zoom = 0;

    private boolean minorGridLinesEnabled = true;

    private Vector2 centerPoint = new Vector2();

    private final TextRenderer textRenderer;
    private Color textColor;
    private int fontSize = 12;

    private double increment = 0;
    private double divider = 1;

    private ArrayList<Function> functions;

    private double minBound;
    private double maxBound;

    private ShapeRenderer shapeRenderer;

    public CoordinateGrid()
    {
        grid = new CartesianGrid();
        axisColor = new GLColor(GraphicsSettings.THEME.getAxisColor());
        majorGridLineColor = new GLColor(GraphicsSettings.THEME.getMajorGridLineColor());
        minorGridLineColor = new GLColor(GraphicsSettings.THEME.getMinorGridLineColor());

        textColor = GraphicsSettings.THEME.getForeGroundColor();
        textRenderer = new TextRenderer(new Font("Calibri", Font.PLAIN, fontSize));

        functions = new ArrayList<>();
        //functions.add(new Function("x", BasicColors.RED));
        //functions.add(new Function("x^(3)-3x^(2)+1", BasicColors.BLUE));
        //functions.add(new Function("cos(2 x) e^(((x)/(5)))", BasicColors.GREEN));
        //functions.add(new Function("e^(x)", BasicColors.ORANGE));
        functions.add(new Function("sqrt(x)", BasicColors.ORANGE));

        shapeRenderer = new ShapeRenderer();
    }

    public Grid getGrid()
    {
        return grid;
    }

    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }

    public int getGridWidth()
    {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth)
    {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight)
    {
        this.gridHeight = gridHeight;
    }

    public Vector2 getCenterPoint()
    {
        return centerPoint;
    }

    public void setCenterPoint(Vector2 centerPoint)
    {
        this.centerPoint = centerPoint;
    }

    public float getZoom()
    {
        return zoom;
    }

    public void setZoom(float zoom)
    {
        this.zoom = zoom;
    }

    public double getDivider()
    {
        return divider;
    }

    private void update()
    {
        axisColor = new GLColor(GraphicsSettings.THEME.getAxisColor());
        majorGridLineColor = new GLColor(GraphicsSettings.THEME.getMajorGridLineColor());
        minorGridLineColor = new GLColor(GraphicsSettings.THEME.getMinorGridLineColor());
        textColor = GraphicsSettings.THEME.getForeGroundColor();
    }

    public void drawGrid(GL2 gl)
    {
        update();

        gl.glLineWidth(1);
        Vector2 centerPoint = new Vector2(this.centerPoint.getX() / gridWidth, this.centerPoint.getY() / gridHeight);

        if((int) zoom % (4) == 0)
        {
            int x = (int) zoom / 4;
            divider = (Math.pow(x % 3, 2) + 1) * Math.pow(10, Math.floor(x / 3.0));
            //zoom = 0;
        }

        double minorUnit = (1 + zoom) / divider * this.minorUnit;
        double majorUnit = (1 + zoom) / divider * this.majorUnit;

        if(minorUnit > majorUnit)
        {
            grid.drawGrid(gl, centerPoint, majorUnit, minorUnit, minorGridLineColor, majorGridLineColor, aspect);
        }
        else
        {
            grid.drawGrid(gl, centerPoint, minorUnit, majorUnit, minorGridLineColor, majorGridLineColor, aspect);
        }

        gl.glColor3f(axisColor.getRed(), axisColor.getGreen(), axisColor.getBlue());

        // X axis
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-aspect, centerPoint.getY());
        gl.glVertex2d(aspect, centerPoint.getY());
        gl.glEnd();

        // Y axis
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(centerPoint.getX(), -1);
        gl.glVertex2d(centerPoint.getX(), 1);
        gl.glEnd();

        // X axis end arrow
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(aspect - arrowSize, centerPoint.getY() + arrowSize);
        gl.glVertex2d(aspect, centerPoint.getY());
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(aspect - arrowSize, centerPoint.getY() - arrowSize);
        gl.glVertex2d(aspect, centerPoint.getY());
        gl.glEnd();

        // Y axis end arrow
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(centerPoint.getX(), 1);
        gl.glVertex2d(centerPoint.getX() + arrowSize, 1 - arrowSize);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(centerPoint.getX(), 1);
        gl.glVertex2d(centerPoint.getX() - arrowSize, 1 - arrowSize);
        gl.glEnd();

        majorUnit = (1 + zoom) / 2 * this.majorUnit;

        int majorMinBoundX = (int) -((1 + centerPoint.getY() / 2) / majorUnit) - 2;
        int majorMaxBoundX = (int) ((1 - centerPoint.getY() / 2) / majorUnit) + 2;
        int majorMinBoundY = (int) -((1 + centerPoint.getX() / 2) / majorUnit) - 2;
        int majorMaxBoundY = (int) ((1 - centerPoint.getX() / 2) / majorUnit) + 2;

        minBound = majorMinBoundY * 2 + majorMinBoundX;
        maxBound = majorMaxBoundY * 2 + majorMaxBoundX;

        for(Function function : functions)
        {
            function.addValuesInRange(minBound, maxBound, Function.PRECISION);

            GLColor color = new GLColor(function.getColor());
            gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
            gl.glLineWidth(3);

            gl.glBegin(GL2.GL_LINES);
            for(double x = minBound; x < maxBound - Function.PRECISION; x += Function.PRECISION)
            {
                double x1 = x;
                double x2 = x + Function.PRECISION;

                Double y1 = function.getY(x);
                Double y2 = function.getY(x + Function.PRECISION);

                if(y1 != null)
                {
                    gl.glVertex2d(x1 * majorUnit + centerPoint.getX(), y1 * majorUnit + centerPoint.getY());
                    gl.glVertex2d(x2 * majorUnit + centerPoint.getX(), y2 * majorUnit + centerPoint.getY());
                }
            }
            gl.glEnd();
        }

        gl.glLineWidth(1);

        // X axis ticks

        textRenderer.beginRendering(gridWidth, gridHeight);
        textRenderer.setColor(textColor);
        textRenderer.setSmoothing(true);

        String decimal = "#";
        DecimalFormat df = new DecimalFormat("#." + decimal.repeat(String.valueOf((int) zoom).length() * 2));

        for(double i = majorMinBoundY; i < majorMaxBoundY; i += 1 / divider)
        {
            String text = df.format(i);

            if(i != 0)
            {
                textRenderer.draw(text, (int) (gridWidth * i * majorUnit / aspect + gridWidth / 2 + this.centerPoint.getX() / aspect / 2) - fontSize / 4 * text.length() - (text.length() - 1) * fontSize / 16, gridHeight / 2 + (int) this.centerPoint.getY() / 2 - fontSize);
            }
            else
            {
                textRenderer.draw(text, (int) (gridWidth / 2 + this.centerPoint.getX() / aspect / 2) - fontSize, gridHeight / 2 + (int) this.centerPoint.getY() / 2 - fontSize);
            }
        }

        // Y axis ticks
        for(double i = majorMinBoundX; i < majorMaxBoundX; i += 1 / divider)
        {
            if(i != 0)
            {
                String text = df.format(i);
                textRenderer.draw(text, gridWidth / 2 + (int) (this.centerPoint.getX() / aspect / 2 - fontSize - (text.length() - 1) * fontSize / 2), (int) (gridHeight * i * majorUnit + gridHeight / 2 + this.centerPoint.getY() / 2) - fontSize / 4);
            }
        }
        textRenderer.endRendering();
    }
}