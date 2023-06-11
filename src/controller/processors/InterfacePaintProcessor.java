package controller.processors;

import common.PaintConfiguration;

import javax.swing.*;
import java.awt.*;

public interface InterfacePaintProcessor {

    public void process(JPanel paintPanel, PaintConfiguration paintConfiguration);
    public void update(Graphics paintPanelGraphics);
}
