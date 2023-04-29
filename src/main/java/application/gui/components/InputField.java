package application.gui.components;

import application.assets.icons.Icons;
import application.functions.FunctionCalculator;
import application.graphics.color.BasicColors;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class InputField extends JPanel implements GUIComponent
{
    private JRadioButton selectButton;
    private JTextField textField;
    private JButton optionsButton;

    private Color color;

    private HashMap<Double, Double> function;

    public InputField()
    {
        color = BasicColors.RED;
        function = new HashMap<>();

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        this.setLayout(new BorderLayout());

        selectButton = new JRadioButton();
        selectButton.setSelected(true);
        selectButton.setIcon(selectButton.isSelected() ? Icons.changeColor(Icons.SELECTED_ICON, color) : Icons.changeColor(Icons.UNSELECTED_ICON, color));
        selectButton.addActionListener(e ->
        {
            selectButton.setIcon(selectButton.isSelected() ? Icons.changeColor(Icons.SELECTED_ICON, color) : Icons.changeColor(Icons.UNSELECTED_ICON, color));
        });

        textField = new JTextField(10);

        optionsButton = new JButton(Icons.changeColor(Icons.OPTIONS_ICON, new Color(110, 110, 110)));
        optionsButton.setOpaque(false);
        optionsButton.setBorderPainted(false);
        //optionsButton.setBackground(null);

        this.add(selectButton, BorderLayout.WEST);
        this.add(textField, BorderLayout.CENTER);
        this.add(optionsButton, BorderLayout.EAST);
    }

    private void calculate()
    {
        FunctionCalculator calculator = new FunctionCalculator(textField.getText());
        System.out.println(calculator.calculate(2));
    }
}