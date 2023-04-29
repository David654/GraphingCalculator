package application.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputBar extends JPanel implements GUIComponent
{
    private ArrayList<InputField> inputFields;
    private JScrollPane scrollPane;

    public InputBar()
    {
        inputFields = new ArrayList<>();

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        this.setLayout(new BorderLayout());

        scrollPane = new JScrollPane();

        this.add(scrollPane, BorderLayout.CENTER);

        addInputField(new InputField());
    }

    public void addInputField(InputField inputField)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        inputFields.add(inputField);

        for(int i = 0; i < inputFields.size(); i++)
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 1;
            gbc.weighty = 0;
            if(i + 1 == inputFields.size())
            {
                gbc.weighty = 1;
            }
            panel.add(inputFields.get(i), gbc);
        }

        scrollPane.setViewportView(panel);
        this.repaint();
    }
}