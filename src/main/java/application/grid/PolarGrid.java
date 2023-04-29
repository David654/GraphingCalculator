package application.grid;

import application.graphics.ShapeRenderer;
import application.graphics.color.GLColor;
import application.math.vector.Vector2;
import com.jogamp.opengl.GL2;

public class PolarGrid implements Grid
{
    private final ShapeRenderer shapeRenderer;

    public PolarGrid()
    {
        shapeRenderer = new ShapeRenderer();
    }

    public void drawGrid(GL2 gl, Vector2 centerPoint, double minorUnit, double majorUnit, GLColor minorGridLineColor, GLColor majorGridLineColor, double aspect)
    {
        // Major grid lines
        gl.glColor3f(majorGridLineColor.getRed(), majorGridLineColor.getGreen(), majorGridLineColor.getBlue());

        // X major grid lines
        int bound = (int) ((1 + centerPoint.length()) / majorUnit) + 5;

        for(int i = 0; i < bound; i++)
        {
            shapeRenderer.drawCircle(gl, (float) centerPoint.getX(), (float) centerPoint.getY(), (float) (i * majorUnit), majorGridLineColor);
        }
    }
}