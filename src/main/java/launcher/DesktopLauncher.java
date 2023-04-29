package launcher;

import application.gui.components.Canvas;
import application.gui.components.Window;
import application.gui.themes.Theme;
import application.settings.GraphicsSettings;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class DesktopLauncher
{
    private Window window;
    private GLProfile glProfile;
    private GLCapabilities glCapabilities;
    private GLJPanel glPanel;
    private FPSAnimator fpsAnimator;

    public DesktopLauncher()
    {

    }

    public void launch()
    {
        initWindow();
        initUI();
        initGraphics();
    }

    private void initWindow()
    {
        window = new Window(1280, 720, "Graphing Calculator");
    }

    private void initGraphics()
    {
        glProfile = GLProfile.get(GLProfile.GL2);
        glCapabilities = new GLCapabilities(glProfile);
        glPanel = new GLJPanel(glCapabilities);
        Canvas canvas = window.getCanvas();
        glPanel.addGLEventListener(canvas);
        glPanel.addMouseListener(canvas.getMouseInput());
        glPanel.addMouseMotionListener(canvas.getMouseInput());
        glPanel.addMouseWheelListener(canvas.getMouseInput());
        glPanel.setFocusable(true);
        fpsAnimator = new FPSAnimator(glPanel, 1000, true);

        window.add(glPanel, BorderLayout.CENTER);
        fpsAnimator.start();

        window.setVisible(true);
    }

    private void initUI()
    {
        Theme.applyTheme();
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.trackArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.trackInsets", new Insets(999, 999, 999, 999));
        UIManager.put("ScrollPane.smoothScrolling", true);
    }

    public static void main(String[] args)
    {
        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.launch();
    }
}