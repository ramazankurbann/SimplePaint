package view.mainPanels;

import common.DataElements;
import common.PaintConfiguration;
import view.components.ColorBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationPanel extends JPanel {

    private PaintConfiguration paintConfiguration;
    private ColorBox colorBoxes[];

    JPanel colorPanel;
    JPanel buttonPanel;

    private JButton rectangleButton;
    private JButton ovalButton;
    private JButton lineButton;
    private JButton dragButton;

    public ConfigurationPanel() {

        paintConfiguration = new PaintConfiguration();

        colorPanel = new JPanel();
        buttonPanel = new JPanel();

        rectangleButton = new JButton("Dikdörtgen Çiz");
        ovalButton = new JButton("Oval Çiz");
        lineButton = new JButton("Kalemle Çiz");
        dragButton = new JButton("Taşı");

        colorBoxes = new ColorBox[7];
        colorBoxes[0] = new ColorBox(Color.blue, paintConfiguration);
        colorBoxes[1] = new ColorBox(Color.red ,paintConfiguration);
        colorBoxes[2] = new ColorBox(Color.green ,paintConfiguration);
        colorBoxes[3] = new ColorBox(Color.yellow ,paintConfiguration);
        colorBoxes[4] = new ColorBox(Color.orange ,paintConfiguration);
        colorBoxes[5] = new ColorBox(Color.magenta ,paintConfiguration);
        colorBoxes[6] = new ColorBox(Color.black ,paintConfiguration);
    }

    public void initialize(){
        setLayout(new GridLayout(2,1));
        setMaximumSize(new Dimension(1024, 100));
        setPreferredSize(new Dimension(1024, 100));

        paintConfiguration.tool = DataElements.Tools.none;
        paintConfiguration.color = Color.BLACK;

        add(colorPanel);
        add(buttonPanel);

        buttonPanel.add(rectangleButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(lineButton);
        buttonPanel.add(dragButton);
        colorPanel.add(colorBoxes[0]);
        colorPanel.add(colorBoxes[1]);
        colorPanel.add(colorBoxes[2]);
        colorPanel.add(colorBoxes[3]);
        colorPanel.add(colorBoxes[4]);
        colorPanel.add(colorBoxes[5]);
        colorPanel.add(colorBoxes[6]);

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintConfiguration.tool = DataElements.Tools.rectangle;
            }
        });

        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintConfiguration.tool = DataElements.Tools.oval;
            }
        });

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintConfiguration.tool = DataElements.Tools.freeLine;
            }
        });

        dragButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintConfiguration.tool = DataElements.Tools.drag;
            }
        });
    }

    public PaintConfiguration getPaintConfiguration()
    {
        return paintConfiguration;
    }
}
