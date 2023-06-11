package model.services;

import common.DataElements;
import common.PaintConfiguration;
import common.shapes.AbstractShape;

import java.awt.*;

public class PaintServiceBridge implements InterfacePaintService{
    InterfacePaintService paintService[];

    public PaintServiceBridge(){
        paintService = new InterfacePaintService[DataElements.Tools.values().length-1];
        paintService[DataElements.Tools.freeLine.getValue()] = new LinePaintService();
        paintService[DataElements.Tools.oval.getValue()] = new OvalPaintService();
        paintService[DataElements.Tools.rectangle.getValue()] = new RectanglePaintService();
    }


    @Override
    public AbstractShape createShape(PaintConfiguration paintConfiguration, Point previousPoint, Point currentPoint) {
        if(isValidPaintRequest(paintConfiguration)){
            return paintService[paintConfiguration.tool.getValue()].createShape(paintConfiguration, previousPoint, currentPoint);
        }

        return null;
    }

    @Override
    public void writeShape(AbstractShape shape) {
        paintService[shape.getShape().getValue()].writeShape(shape);
    }

    @Override
    public void deleteShape(AbstractShape shape) {
        paintService[shape.getShape().getValue()].deleteShape(shape);
    }

    @Override
    public void changePositionOf(AbstractShape shape, Point offsetPoint, Point newPoint){
        paintService[shape.getShape().getValue()].changePositionOf(shape, offsetPoint, newPoint);
    }

    @Override
    public void paint(Graphics graphics, AbstractShape abstractShape) {
        paintService[abstractShape.getShape().getValue()].paint(graphics, abstractShape);
    }

    private boolean isValidPaintRequest(PaintConfiguration paintConfiguration){
        return (paintConfiguration.tool != DataElements.Tools.none);
    }
}
