package application.graphics.geom;

import com.jogamp.opengl.GL2;

public abstract class AbstractShape
{
    public abstract void draw(GL2 gl);

    public abstract void fill(GL2 gl);
}