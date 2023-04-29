package application.gui.components;

import application.graphics.ShapeRenderer;
import application.graphics.color.GLColor;
import application.grid.CoordinateGrid;
import application.input.MouseInput;
import application.settings.GraphicsSettings;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Canvas implements GLEventListener
{
    private int canvasWidth;
    private int canvasHeight;

    private final ShapeRenderer shapeRenderer;
    private GLColor backgroundColor;
    private GLColor foregroundColor;

    private final CoordinateGrid coordinateGrid;
    private final MouseInput mouseInput;

    public Canvas(int canvasWidth, int canvasHeight)
    {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        shapeRenderer = new ShapeRenderer();
        backgroundColor = new GLColor(GraphicsSettings.THEME.getBackgroundColor());
        foregroundColor = new GLColor(GraphicsSettings.THEME.getForeGroundColor());

        coordinateGrid = new CoordinateGrid();
        coordinateGrid.setGridWidth(canvasWidth);
        coordinateGrid.setGridHeight(canvasHeight);

        mouseInput = new MouseInput(coordinateGrid);
    }

    public CoordinateGrid getCoordinateGrid()
    {
        return coordinateGrid;
    }

    public MouseInput getMouseInput()
    {
        return mouseInput;
    }

    public void init(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glViewport(0, 0, canvasWidth, canvasHeight);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        float aspect = (float) canvasWidth / (float) canvasHeight;
        gl.glOrtho(-aspect, aspect, -1, 1, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), 1f);
        //gl.glEnable(GL2.GL_MULTISAMPLE);
    }

    private void update()
    {
        backgroundColor = new GLColor(GraphicsSettings.THEME.getBackgroundColor());
        foregroundColor = new GLColor(GraphicsSettings.THEME.getForeGroundColor());
        mouseInput.update();
    }

    public void display(GLAutoDrawable glAutoDrawable)
    {
        update();

        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), 1f);
        gl.glLoadIdentity();

        coordinateGrid.drawGrid(gl);
    }

    public void dispose(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {
        canvasWidth = width;
        canvasHeight = height;
        coordinateGrid.setGridWidth(canvasWidth);
        coordinateGrid.setGridHeight(canvasHeight);
    }
}