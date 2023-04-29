package application.gui.themes;

import application.assets.icons.Icons;
import application.settings.GraphicsSettings;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;

import javax.swing.*;
import java.awt.*;

public class Theme
{
    LookAndFeel theme;
    private Color backgroundColor;
    private Color foreGroundColor;
    private Color axisColor;
    private Color majorGridLineColor;
    private Color minorGridLineColor;

    public Theme(LookAndFeel theme, Color backgroundColor, Color foreGroundColor, Color axisColor, Color majorGridLineColor, Color minorGridLineColor)
    {
        this.theme = theme;
        this.backgroundColor = backgroundColor;
        this.foreGroundColor = foreGroundColor;
        this.axisColor = axisColor;
        this.majorGridLineColor = majorGridLineColor;
        this.minorGridLineColor = minorGridLineColor;
    }

    public static void applyTheme()
    {
        if(GraphicsSettings.THEME.getTheme() instanceof FlatIntelliJLaf)
        {
            Icons.OPTIONS_ICON = Icons.changeColor(Icons.OPTIONS_ICON, new Color(110, 110, 110));
            FlatIntelliJLaf.setup();
        }

        if(GraphicsSettings.THEME.getTheme() instanceof FlatDarculaLaf)
        {
            Icons.OPTIONS_ICON = Icons.changeColor(Icons.OPTIONS_ICON, new Color(175, 177, 179));
            FlatDarculaLaf.setup();
        }

        FlatLaf.updateUI();
    }

    public LookAndFeel getTheme()
    {
        return theme;
    }

    public void setTheme(LookAndFeel theme)
    {
        this.theme = theme;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Color getForeGroundColor()
    {
        return foreGroundColor;
    }

    public void setForeGroundColor(Color foreGroundColor)
    {
        this.foreGroundColor = foreGroundColor;
    }

    public Color getAxisColor()
    {
        return axisColor;
    }

    public void setAxisColor(Color axisColor)
    {
        this.axisColor = axisColor;
    }

    public Color getMajorGridLineColor()
    {
        return majorGridLineColor;
    }

    public void setMajorGridLineColor(Color majorGridLineColor)
    {
        this.majorGridLineColor = majorGridLineColor;
    }

    public Color getMinorGridLineColor()
    {
        return minorGridLineColor;
    }

    public void setMinorGridLineColor(Color minorGridLineColor)
    {
        this.minorGridLineColor = minorGridLineColor;
    }
}