package application.gui.themes;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.*;

public class LightTheme extends Theme
{
    public LightTheme()
    {
        super(new FlatIntelliJLaf(), new Color(255, 255, 255), Color.BLACK,
                new Color(0, 0, 0), new Color(192, 192, 192), new Color(234, 234, 234));
    }
}