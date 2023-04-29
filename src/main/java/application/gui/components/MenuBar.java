package application.gui.components;

import application.gui.themes.DarkTheme;
import application.gui.themes.LightTheme;
import application.gui.themes.Theme;
import application.settings.GraphicsSettings;

import javax.swing.*;
import java.awt.event.InputEvent;

public class MenuBar extends JMenuBar implements GUIComponent
{
    private JMenu fileMenu;
    private JMenu toolsMenu;
    private JMenu gridMenu;
    private JMenu preferencesMenu;

    // File menu
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem exitMenuItem;

    // Tools menu


    // Grid menu
    private JCheckBoxMenuItem showAxesMenuItem;
    private JCheckBoxMenuItem showMajorGridlinesItem;
    private JCheckBoxMenuItem showMinorGridlinesItem;
    private JMenu gridSubMenu;
    private JCheckBoxMenuItem cartesianGridMenuItem;
    private JCheckBoxMenuItem polarGridMenuItem;

    // Preferences menu
    private JMenuItem settingsMenuItem;
    private JMenu themeSubMenu;
    private JCheckBoxMenuItem lightThemeMenuItem;
    private JCheckBoxMenuItem darkThemeMenuItem;

    public MenuBar()
    {
        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        // File menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        openMenuItem = fileMenu.add("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));

        saveMenuItem = fileMenu.add("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));

        saveAsMenuItem = fileMenu.add("Save As");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        fileMenu.addSeparator();

        exitMenuItem = fileMenu.add("Exit");

        // Tools menu
        toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic('T');

        // Grid menu
        gridMenu = new JMenu("Grid");
        gridMenu.setMnemonic('G');

        showAxesMenuItem = new JCheckBoxMenuItem("Show Axes");
        showAxesMenuItem.setSelected(true);
        showAxesMenuItem.setAccelerator(KeyStroke.getKeyStroke("F5"));

        showMajorGridlinesItem = new JCheckBoxMenuItem("Show Major Gridlines");
        showMajorGridlinesItem.setSelected(true);
        showMajorGridlinesItem.setAccelerator(KeyStroke.getKeyStroke("F6"));

        showMinorGridlinesItem = new JCheckBoxMenuItem("Show Minor Gridlines");
        showMinorGridlinesItem.setSelected(true);
        showMinorGridlinesItem.setAccelerator(KeyStroke.getKeyStroke("F7"));

        gridMenu.add(showAxesMenuItem);
        gridMenu.add(showMajorGridlinesItem);
        gridMenu.add(showMinorGridlinesItem);
        gridMenu.addSeparator();

        gridSubMenu = new JMenu("Grid");
        cartesianGridMenuItem = new JCheckBoxMenuItem("Cartesian Grid");
        cartesianGridMenuItem.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        polarGridMenuItem = new JCheckBoxMenuItem("Polar Grid");
        polarGridMenuItem.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        ButtonGroup gridButtonGroup = new ButtonGroup();
        gridButtonGroup.add(cartesianGridMenuItem);
        gridButtonGroup.add(polarGridMenuItem);

        gridSubMenu.add(cartesianGridMenuItem);
        gridSubMenu.add(polarGridMenuItem);

        gridMenu.add(gridSubMenu);

        // Preferences menu
        preferencesMenu = new JMenu("Preferences");
        preferencesMenu.setMnemonic('P');

        settingsMenuItem = preferencesMenu.add("Settings");
        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));

        preferencesMenu.addSeparator();

        themeSubMenu = new JMenu("Theme");
        lightThemeMenuItem = new JCheckBoxMenuItem("Light Theme");
        lightThemeMenuItem.setSelected(GraphicsSettings.THEME instanceof LightTheme);
        lightThemeMenuItem.addActionListener(e ->
        {
            GraphicsSettings.THEME = new LightTheme();
            Theme.applyTheme();
        });

        darkThemeMenuItem = new JCheckBoxMenuItem("Dark Theme");
        darkThemeMenuItem.setSelected(GraphicsSettings.THEME instanceof DarkTheme);
        darkThemeMenuItem.addActionListener(e ->
        {
            GraphicsSettings.THEME = new DarkTheme();
            Theme.applyTheme();
        });

        ButtonGroup themeButtonGroup = new ButtonGroup();
        themeButtonGroup.add(lightThemeMenuItem);
        themeButtonGroup.add(darkThemeMenuItem);

        themeSubMenu.add(lightThemeMenuItem);
        themeSubMenu.add(darkThemeMenuItem);

        preferencesMenu.add(themeSubMenu);

        this.add(fileMenu);
        this.add(toolsMenu);
        this.add(gridMenu);
        this.add(preferencesMenu);
    }
}