package model.services;

import common.PaintConfiguration;
import model.shapeCache.ObjectIDGenerator;
import model.shapeCache.ShapeCacheService;
import common.shapes.AbstractShape;
import common.shapes.RectangleShape;

import java.awt.*;

public class RectanglePaintService implements InterfacePaintService{
    @Override
    public AbstractShape createShape(PaintConfiguration paintConfiguration, Point previousPoint, Point currentPoint) {
        RectangleShape rectangleShape = new RectangleShape();
        rectangleShape.objectID = ObjectIDGenerator.instance().getNextObjectID();
        rectangleShape.color = paintConfiguration.color;
        rectangleShape.width = (int)Math.abs(currentPoint.getX()-previousPoint.getX());
        rectangleShape.height = (int)Math.abs(currentPoint.getY() - previousPoint.getY());

        int x;
        int y;

        if(previousPoint.getX() < currentPoint.getX()){
            x = (int)previousPoint.getX();
        }
        else{
            x = (int)currentPoint.getX();
        }

        if(previousPoint.getY() < currentPoint.getY()){
            y = (int)previousPoint.getY();
        }
        else{
            y = (int)currentPoint.getY();
        }

        rectangleShape.initialPoint.setLocation(x,y);

        return rectangleShape;
    }

    @Override
    public void writeShape(AbstractShape shape) {
        RectangleShape rectangleShape = (RectangleShape)shape;
        if(rectangleShape.objectID == 0){
            rectangleShape.objectID = ObjectIDGenerator.instance().getNextObjectID();
        }
        ShapeCacheService.instance().write(rectangleShape);
    }

    @Override
    public void deleteShape(AbstractShape shape) {
        RectangleShape rectangleShape = (RectangleShape)shape;
        ShapeCacheService.instance().delete(rectangleShape);
    }

    @Override
    public void changePositionOf(AbstractShape shape, Point offsetPoint, Point newPoint){
        RectangleShape rectangleShape = (RectangleShape) shape;
        rectangleShape.initialPoint.setLocation(offsetPoint.getX() + newPoint.getX(), offsetPoint.getY() + newPoint.getY());
    }

    @Override
    public void paint(Graphics graphics, AbstractShape shape) {
        RectangleShape rectangleShape = (RectangleShape) shape;
        graphics.setColor(rectangleShape.color);
        graphics.fillRect((int)rectangleShape.initialPoint.getX(), (int)rectangleShape.initialPoint.getY(),(int)rectangleShape.width, (int)rectangleShape.height);
    }
}
