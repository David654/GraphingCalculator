package application.gui.components;

import application.grid.CoordinateGrid;
import application.input.MouseInput;
import application.math.vector.Vector2;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DataBar extends JPanel implements GUIComponent
{
    private final CoordinateGrid coordinateGrid;
    private final MouseInput mouseInput;

    private JLabel mousePositionsLabel;

    public DataBar(CoordinateGrid coordinateGrid, MouseInput mouseInput)
    {
        this.coordinateGrid = coordinateGrid;
        this.mouseInput = mouseInput;

        createAndShowGUI();
        update();
    }

    public void createAndShowGUI()
    {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        DecimalFormat df = new DecimalFormat("#");
        Vector2 mousePosition = mouseInput.getMousePositionOnCoordinateGrid();
        mousePositionsLabel = new JLabel("X: " + df.format(mousePosition.getX()) + ", Y: " + df.format(mousePosition.getY()));

        this.add(mousePositionsLabel);
    }

    public void update()
    {
        DecimalFormat df = new DecimalFormat("#");
        Timer timer = new Timer(1, e ->
        {
            Vector2 mousePosition = mouseInput.getMousePositionOnCoordinateGrid();
            mousePositionsLabel.setText("X: " + df.format(mousePosition.getX()) + ", Y: " + df.format(mousePosition.getY()));
        });
        timer.start();
    }
}