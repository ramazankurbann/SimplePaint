package controller.processors;

import common.PaintConfiguration;
import controller.ControllerManager;
import controller.listeners.PaintPanelListener;
import model.services.InterfacePaintService;
import common.shapes.AbstractShape;

import javax.swing.*;
import java.awt.*;

public class DiscretePaintProcessor extends AbstractPaintProcessor {

    InterfacePaintService paintServiceBridge;
    AbstractShape shape;
    AbstractShape lastShape;
    Point previousPoint;
    Point initialPoint;
    Point currentPoint;
    int repaintFrequency;

    public DiscretePaintProcessor(InterfacePaintService paintService) {
        super(paintService);
        paintServiceBridge = paintService;
        initialPoint = new Point();
        previousPoint = new Point();
        currentPoint = new Point();
    }

    @Override
    public void process(JPanel paintPanel, PaintConfiguration paintConfiguration)
    {
        PaintPanelListener paintPanelListener = (PaintPanelListener)paintPanel.getMouseListeners()[0];

        if(paintPanelListener.getPaintPermission()){
            initialPoint = paintPanel.getMousePosition();
            lastShape = null;
            repaintFrequency = 0;

            while(ControllerManager.instance().getPaintPanelListener().getPaintPermission() && initialPoint != null){
                currentPoint = paintPanel.getMousePosition();

                if(currentPoint != null && pointTolerance(previousPoint, currentPoint)){
                    shape = paintServiceBridge.createShape(paintConfiguration, initialPoint, currentPoint);

                    if(shape != null){
                        if((++repaintFrequency % 5) == 0 ){
                            paintPanel.repaint(); // to clear screen
                        }

                        paintServiceBridge.writeShape(shape);
                        update(paintPanel.getGraphics());
                        paintServiceBridge.deleteShape(shape);
                        lastShape = shape;

                        try {
                            Thread.sleep(3);
                        }
                        catch (Exception e){
                        }
                    }
                    previousPoint = currentPoint;
                }

            }

            if(lastShape != null){
                paintServiceBridge.writeShape(lastShape);
                paintPanel.repaint(); // to clear screen
                update(paintPanel.getGraphics());
            }
        }
    }
}
