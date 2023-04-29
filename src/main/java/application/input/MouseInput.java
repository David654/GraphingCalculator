package application.input;

import application.functions.Function;
import application.grid.CoordinateGrid;
import application.math.vector.Vector2;

import java.awt.event.*;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private final CoordinateGrid coordinateGrid;
    private Vector2 mousePosition;
    private Vector2 mouseDragPosition;
    private float zoom = 0;

    public MouseInput(CoordinateGrid coordinateGrid)
    {
        this.coordinateGrid = coordinateGrid;
        mousePosition = new Vector2();
        mouseDragPosition = new Vector2();

        update();
    }

    public Vector2 getMousePosition()
    {
        return mousePosition;
    }

    public Vector2 getMouseDragPosition()
    {
        return mouseDragPosition;
    }

    public Vector2 getMousePositionOnCoordinateGrid()
    {
        //System.out.println(coordinateGrid.getCenterPoint());
        return coordinateGrid.getCenterPoint().subtract(mousePosition.subtract(new Vector2(coordinateGrid.getGridWidth(), coordinateGrid.getGridHeight()).multiply(0.5)));
    }

    public void update()
    {
        Vector2 dist = new Vector2(mouseDragPosition.getX(), -mouseDragPosition.getY()).subtract(coordinateGrid.getCenterPoint());
        coordinateGrid.setCenterPoint(coordinateGrid.getCenterPoint().add(dist.multiply(0.2)));
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e)
    {
        mousePosition = new Vector2(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(MouseEvent e)
    {
        mousePosition = new Vector2(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e)
    {
        Vector2 newMousePosition = new Vector2(e.getX(), e.getY());
        Vector2 dp = newMousePosition.subtract(mousePosition);

        Vector2 lastMouseDragPosition = mouseDragPosition;
        mouseDragPosition = mouseDragPosition.add(dp);
        //mouseDragPosition = mouseDragPosition.lerp(lastMouseDragPosition, 0.5);

        mousePosition = newMousePosition;

        //coordinateGrid.setCenterPoint(new Vector2(mouseDragPosition.getX(), -mouseDragPosition.getY()));
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        zoom -= e.getWheelRotation() * 0.5;
        coordinateGrid.setZoom(zoom);



        //mouseDragPosition = mouseDragPosition.add(mousePosition);

        if(zoom < 0)
        {
            //coordinateGrid.setZoom((float) Math.pow(0.1, Math.abs(zoom)));
        }

        System.out.println(coordinateGrid.getZoom() + "; " + ((int) coordinateGrid.getZoom() % 2));
    }
}