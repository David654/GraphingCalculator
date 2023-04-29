package application.grid;

import application.graphics.color.GLColor;
import application.math.vector.Vector2;
import com.jogamp.opengl.GL2;

public interface Grid
{
    void drawGrid(GL2 gl, Vector2 centerPoint, double minorUnit, double majorUnit, GLColor minorGridLineColor, GLColor majorGridLineColor, double aspect);
}