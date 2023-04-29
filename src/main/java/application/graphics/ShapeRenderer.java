package application.graphics;

import application.graphics.color.GLColor;
import application.graphics.geom.Circle;
import com.jogamp.opengl.GL2;

import java.awt.*;

public class ShapeRenderer
{
    public void drawCircle(GL2 gl, float x, float y, float radius, GLColor glColor)
    {
        Circle circle = new Circle(x, y, radius, glColor);
        circle.draw(gl);
    }

    public void fillCircle(GL2 gl, float x, float y, float radius, GLColor glColor)
    {
        Circle circle = new Circle(x, y, radius, glColor);
        circle.fill(gl);
    }
}