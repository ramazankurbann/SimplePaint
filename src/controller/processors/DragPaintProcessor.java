package controller.processors;

import common.PaintConfiguration;
import controller.ControllerManager;
import controller.listeners.PaintPanelListener;
import model.services.InterfacePaintService;
import model.shapeCache.ShapeCacheService;
import common.shapes.AbstractShape;

import javax.swing.*;
import java.awt.*;

public class DragPaintProcessor extends AbstractPaintProcessor {

    InterfacePaintService paintServiceBridge;
    AbstractShape shape;
    AbstractShape lastShape;
    Point offsetPoint;
    Point previousPoint;
    Point initialPoint;
    Point currentPoint;
    int repaintFrequency;

    public DragPaintProcessor(InterfacePaintService paintService) {
        super(paintService);
        paintServiceBridge = paintService;
        initialPoint = new Point();
        previousPoint = new Point();
        currentPoint = new Point();
    }

    @Override
    public void process(JPanel paintPanel, PaintConfiguration paintConfiguration) {
        PaintPanelListener paintPanelListener = (PaintPanelListener)paintPanel.getMouseListeners()[0];

        if(paintPanelListener.getPaintPermission()){
            initialPoint = paintPanel.getMousePosition();
            lastShape = null;
            repaintFrequency = 0;

            if(initialPoint != null){
                AbstractShape shape = ShapeCacheService.instance().getAnyShapeWithFilter(initialPoint);

                if(shape != null){
                    offsetPoint = shape.getOffset(initialPoint);

                    while(ControllerManager.instance().getPaintPanelListener().getPaintPermission() && shape != null){
                        currentPoint = paintPanel.getMousePosition();

                        if(currentPoint != null && pointTolerance(previousPoint, currentPoint)){
                            if((++repaintFrequency % 10) == 0 ){
                                paintPanel.repaint(); // to clear screen
                            }

                            paintServiceBridge.changePositionOf(shape, offsetPoint, currentPoint);
                            paintServiceBridge.writeShape(shape);
                            update(paintPanel.getGraphics());

                            try {
                                Thread.sleep(3);
                            }
                            catch (Exception e){
                            }
                            previousPoint = currentPoint;
                        }
                    }

                    paintPanel.repaint();
                    update(paintPanel.getGraphics());
                }
            }
        }
    }
}

