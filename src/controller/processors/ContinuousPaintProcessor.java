package controller.processors;

import common.PaintConfiguration;
import controller.ControllerManager;
import controller.listeners.PaintPanelListener;
import model.services.InterfacePaintService;
import common.shapes.AbstractShape;

import javax.swing.*;
import java.awt.*;

public class ContinuousPaintProcessor extends AbstractPaintProcessor {

    InterfacePaintService paintServiceBridge;
    AbstractShape shape;
    Point previousPoint;
    Point currentPoint;

    public ContinuousPaintProcessor(InterfacePaintService paintService) {
        super(paintService);
        paintServiceBridge = paintService;
        previousPoint = new Point();
        currentPoint = new Point();
    }

    @Override
    public void process(JPanel paintPanel, PaintConfiguration paintConfiguration) {
        PaintPanelListener paintPanelListener = (PaintPanelListener)paintPanel.getMouseListeners()[0];

        if(paintPanelListener.getPaintPermission()){
            previousPoint = paintPanel.getMousePosition();

            while(ControllerManager.instance().getPaintPanelListener().getPaintPermission() && previousPoint != null){
                currentPoint = paintPanel.getMousePosition();

                if(currentPoint != null){
                    shape = paintServiceBridge.createShape(paintConfiguration, previousPoint, currentPoint);

                    if(shape != null){
                        paintServiceBridge.writeShape(shape);
                        previousPoint = currentPoint;
                        update(paintPanel.getGraphics());
                    }
                }
            }
        }
    }
}
