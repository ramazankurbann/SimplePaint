package model.services;

import common.PaintConfiguration;
import model.shapeCache.ObjectIDGenerator;
import model.shapeCache.ShapeCacheService;
import common.shapes.AbstractShape;
import common.shapes.LineShape;

import java.awt.*;


public class LinePaintService implements InterfacePaintService {

    @Override
    public AbstractShape createShape(PaintConfiguration paintConfiguration, Point previousPoint, Point currentPoint) {
        LineShape lineShape = new LineShape();
        lineShape.color = paintConfiguration.color;
        lineShape.initialPoint = previousPoint;
        lineShape.lastPoint = currentPoint;

        return lineShape;
    }

    @Override
    public void writeShape(AbstractShape shape) {
        LineShape lineShape = (LineShape)shape;
        lineShape.objectID = ObjectIDGenerator.instance().getNextObjectID();
        ShapeCacheService.instance().write(lineShape);
    }

    @Override
    public void deleteShape(AbstractShape shape) {
        LineShape lineShape = (LineShape)shape;
        ObjectIDGenerator.instance().releaseObjectID(lineShape.objectID);
        ShapeCacheService.instance().delete(lineShape);
    }

    @Override
    public void changePositionOf(AbstractShape shape, Point offsetPoint, Point newPoint){

    }

    @Override
    public void paint(Graphics graphics, AbstractShape shape) {
        LineShape lineShape = (LineShape) shape;
        graphics.setColor(lineShape.color);
        graphics.drawLine((int)lineShape.initialPoint.getX(), (int)lineShape.initialPoint.getY(), (int)lineShape.lastPoint.getX(), (int)lineShape.lastPoint.getY());
    }
}
