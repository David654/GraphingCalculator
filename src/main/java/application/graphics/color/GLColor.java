package application.graphics.color;

import application.math.MathUtils;

import java.awt.*;

public class GLColor
{
    private float red;
    private float green;
    private float blue;

    public GLColor(float red, float green, float blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public GLColor(Color color)
    {
        this(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
    }

    public float getRed()
    {
        return red;
    }

    public void setRed(float red)
    {
        this.red = red;
    }

    public float getGreen()
    {
        return green;
    }

    public void setGreen(float green)
    {
        this.green = green;
    }

    public float getBlue()
    {
        return blue;
    }

    public void setBlue(float blue)
    {
        this.blue = blue;
    }

    public Color getColor()
    {
        return new Color(MathUtils.clamp(red * 255, 0, 255), MathUtils.clamp(green * 255, 0, 255), MathUtils.clamp(blue * 255, 0, 255));
    }
}