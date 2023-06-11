package model.services;

import common.PaintConfiguration;
import model.shapeCache.ObjectIDGenerator;
import model.shapeCache.ShapeCacheService;
import common.shapes.AbstractShape;
import common.shapes.OvalShape;

import java.awt.*;

public class OvalPaintService implements InterfacePaintService{

    @Override
    public AbstractShape createShape(PaintConfiguration paintConfiguration, Point previousPoint, Point currentPoint) {

        OvalShape ovalShape = new OvalShape();
        ovalShape.objectID = ObjectIDGenerator.instance().getNextObjectID();
        ovalShape.color = paintConfiguration.color;
        ovalShape.width = (int)Math.abs(currentPoint.getX()-previousPoint.getX());
        ovalShape.height = (int)Math.abs(currentPoint.getY() - previousPoint.getY());

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

        ovalShape.initialPoint.setLocation(x,y);

        return ovalShape;
    }

    @Override
    public void writeShape(AbstractShape shape) {
        OvalShape ovalShape = (OvalShape)shape;
        if(ovalShape.objectID == 0) {
            ovalShape.objectID = ObjectIDGenerator.instance().getNextObjectID();
        }
        ShapeCacheService.instance().write(ovalShape);
    }

    @Override
    public void deleteShape(AbstractShape shape) {
        OvalShape ovalShape = (OvalShape)shape;
        ShapeCacheService.instance().delete(ovalShape);
    }

    @Override
    public void changePositionOf(AbstractShape shape, Point offsetPoint, Point newPoint){
        OvalShape ovalShape = (OvalShape) shape;
        ovalShape.initialPoint.setLocation(offsetPoint.getX() + newPoint.getX(), offsetPoint.getY() + newPoint.getY());
    }

    @Override
    public void paint(Graphics graphics, AbstractShape shape) {
        OvalShape ovalShape = (OvalShape) shape;
        graphics.setColor(ovalShape.color);
        graphics.fillOval((int)ovalShape.initialPoint.getX(), (int)ovalShape.initialPoint.getY(),(int)ovalShape.width, (int)ovalShape.height);
    }
}
