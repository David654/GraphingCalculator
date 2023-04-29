package application.grid;

import application.graphics.color.GLColor;
import application.math.vector.Vector2;
import com.jogamp.opengl.GL2;

public class CartesianGrid implements Grid
{
    public void drawGrid(GL2 gl, Vector2 centerPoint, double minorUnit, double majorUnit, GLColor minorGridLineColor, GLColor majorGridLineColor, double aspect)
    {
        gl.glBegin(GL2.GL_LINES);

        // Minor grid lines
        gl.glColor3f(minorGridLineColor.getRed(), minorGridLineColor.getGreen(), minorGridLineColor.getBlue());

        // X minor grid lines
        int minorMinBoundX = (int) -((aspect + centerPoint.getY()) / minorUnit) - 2;
        int minorMaxBoundX = (int) ((aspect - centerPoint.getY()) / minorUnit) + 2;
        int minorMinBoundY = (int) -((aspect + centerPoint.getX()) / minorUnit) - 2;
        int minorMaxBoundY = (int) ((aspect - centerPoint.getX()) / minorUnit) + 2;

        for(int i = minorMinBoundX; i < minorMaxBoundX; i++)
        {
            gl.glVertex2d(-aspect, i * minorUnit + centerPoint.getY());
            gl.glVertex2d(aspect, i * minorUnit + centerPoint.getY());
        }

        // Y minor grid lines
        for(int i = minorMinBoundY; i < minorMaxBoundY; i++)
        {
            gl.glVertex2d(i * minorUnit + centerPoint.getX(), -1);
            gl.glVertex2d(i * minorUnit + centerPoint.getX(), 1);
        }

        // Major grid lines
        gl.glColor3f(majorGridLineColor.getRed(), majorGridLineColor.getGreen(), majorGridLineColor.getBlue());

        // X major grid lines
        int majorMinBoundX = (int) -((aspect + centerPoint.getY()) / majorUnit) - 2;
        int majorMaxBoundX = (int) ((aspect - centerPoint.getY()) / majorUnit) + 2;
        int majorMinBoundY = (int) -((aspect + centerPoint.getX()) / majorUnit) - 2;
        int majorMaxBoundY = (int) ((aspect - centerPoint.getX()) / majorUnit) + 2;

        for(int i = majorMinBoundX; i < majorMaxBoundX; i++)
        {
            gl.glVertex2d(-aspect, i * majorUnit + centerPoint.getY());
            gl.glVertex2d(aspect, i * majorUnit + centerPoint.getY());
        }

        // Y major grid lines
        for(int i = majorMinBoundY; i < majorMaxBoundY; i++)
        {
            gl.glVertex2d(i * majorUnit + centerPoint.getX(), -1);
            gl.glVertex2d(i * majorUnit + centerPoint.getX(), 1);
        }

        gl.glEnd();
    }
}