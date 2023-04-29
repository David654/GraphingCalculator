package application.gui.themes;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;

public class DarkTheme extends Theme
{
    public DarkTheme()
    {
        super(new FlatDarculaLaf(), new Color(43, 43, 43), new Color(187, 187, 187),
                new Color(187, 187, 187), new Color(85, 85, 85), new Color(54, 56, 58));
    }
}