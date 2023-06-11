package view.components;

import common.PaintConfiguration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ColorBox extends JPanel {

    Color color;
    PaintConfiguration paintConfiguration;

    public ColorBox(Color color, PaintConfiguration paintConfiguration) {
        this.color = color;
        this.paintConfiguration = paintConfiguration;
        initialize();
    }

    public void initialize(){
        setBackground(color);
        setPreferredSize(new Dimension(60, 30));

        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                paintConfiguration.color = color;
            }
        });
    }
}
