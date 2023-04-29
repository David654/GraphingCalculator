package application.gui.components;

import application.assets.icons.Icons;
import application.functions.FunctionCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements GUIComponent, ComponentListener
{
    private int windowWidth;
    private int windowHeight;
    private String windowTitle;
    private Canvas canvas;

    public Window(int windowWidth, int windowHeight, String windowTitle)
    {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowTitle = windowTitle;

        createAndShowGUI();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void createAndShowGUI()
    {
        this.setSize(windowWidth, windowHeight);
        this.setResizable(true);
        this.setTitle(windowTitle);
        this.setIconImage(Icons.createIcon(Icons.APPLICATION_ICON_PATH).getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        canvas = new Canvas(windowWidth, windowHeight);

        MenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        DataBar dataBar = new DataBar(canvas.getCoordinateGrid(), canvas.getMouseInput());
        this.add(dataBar, BorderLayout.SOUTH);

        InputBar inputBar = new InputBar();
       // inputBar.setPreferredSize(new Dimension(windowWidth / 8, windowHeight));
        this.add(inputBar, BorderLayout.WEST);
    }

    public void componentResized(ComponentEvent e)
    {
        canvas.getCoordinateGrid().setGridWidth(this.getWidth());
        canvas.getCoordinateGrid().setGridHeight(this.getHeight());
    }

    public void componentMoved(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}

    public void componentHidden(ComponentEvent e) {}
}