package application.graphics.geom;

import application.graphics.color.GLColor;
import com.jogamp.opengl.GL2;

public class Circle extends AbstractShape
{
    private float x;
    private float y;
    private float radius;
    private GLColor color;

    public Circle(float x, float y, float radius, GLColor color)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public GLColor getColor()
    {
        return color;
    }

    public void setColor(GLColor color)
    {
        this.color = color;
    }

    public void draw(GL2 gl)
    {
        double increment = 2 * Math.PI / 50;

        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        for(double angle = 0; angle < 2 * Math.PI; angle += increment)
        {
            float x1 = x + (float) Math.cos(angle) * radius;
            float y1 = y + (float) Math.sin(angle) * radius;
            float x2 = x + (float) Math.cos(angle + increment) * radius;
            float y2 = y + (float) Math.sin(angle + increment) * radius;

            gl.glVertex2f(x1, y1);
            gl.glVertex2f(x2, y2);
        }
        gl.glEnd();
    }

    public void fill(GL2 gl)
    {
        double increment = 2 * Math.PI / 50;

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        for(double angle = 0; angle < 2 * Math.PI; angle += increment)
        {
            float x1 = x + (float) Math.cos(angle) * radius;
            float y1 = y + (float) Math.sin(angle) * radius;
            float x2 = x + (float) Math.cos(angle + increment) * radius;
            float y2 = y + (float) Math.sin(angle + increment) * radius;

            gl.glVertex2d(x, y);
            gl.glVertex2f(x1, y1);
            gl.glVertex2f(x2, y2);
        }
        gl.glEnd();
    }
}