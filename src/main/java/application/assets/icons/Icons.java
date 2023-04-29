package application.assets.icons;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class Icons
{
    public static final int ICON_SIZE = 16;

    public static final String ICON_DIRECTORY = "src\\main\\resources\\icons\\";

    public static final String APPLICATION_ICON_PATH = ICON_DIRECTORY + "logo.png";
    public static final String SELECTED_ICON_PATH = ICON_DIRECTORY + "circle 2.png";
    public static final String UNSELECTED_ICON_PATH = ICON_DIRECTORY + "circle.png";
    public static final String OPTIONS_ICON_PATH = ICON_DIRECTORY + "dots.png";

    public static final ImageIcon SELECTED_ICON = createIcon(SELECTED_ICON_PATH);
    public static final ImageIcon UNSELECTED_ICON = createIcon(UNSELECTED_ICON_PATH);
    public static ImageIcon OPTIONS_ICON = createIcon(OPTIONS_ICON_PATH);


    public static ImageIcon createIcon(String fileName)
    {
        return Icons.resize(new ImageIcon(fileName), ICON_SIZE);
    }

    public static ImageIcon createEmptyIcon()
    {
        Image image = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(image);
    }

    public static ImageIcon resize(ImageIcon icon, int size)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private static BufferedImage iconToBufferedImage(ImageIcon icon)
    {
        BufferedImage image = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        icon.paintIcon(null, g2d, 0,0);
        g2d.dispose();
        return image;
    }

    public static ImageIcon changeColor(ImageIcon icon, Color color)
    {
        BufferedImage image = iconToBufferedImage(icon);

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int rgb = image.getRGB(x, y);
                Color col = new Color(rgb, true);
                if(col.getAlpha() > 0)
                {
                    Color pixelColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), col.getAlpha());
                    image.setRGB(x, y, pixelColor.getRGB());
                }
            }
        }
        return new ImageIcon(image);
    }
}