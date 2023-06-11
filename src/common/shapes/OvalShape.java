package common.shapes;

import common.DataElements;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class OvalShape extends AbstractShape {
    public Integer objectID;
    public Color color;
    public Point initialPoint;
    public Integer width;
    public Integer height;

    public OvalShape() {
        shape = DataElements.Shapes.oval;
        objectID = 0;
        color = Color.BLACK;
        initialPoint = new Point(0,0);
        width = 0;
        height = 0;
    }

    @Override
    public DataElements.Shapes getShape(){
        return shape;
    }

    @Override
    public OvalShape clone(){
        OvalShape ovalShape = new OvalShape();

        ovalShape.shape = this.shape;
        ovalShape.objectID = this.objectID;
        ovalShape.color = this.color;
        ovalShape.initialPoint = this.initialPoint;
        ovalShape.width = this.width;
        ovalShape.height = this.height;

        return ovalShape;
    }

    @Override
    public boolean isInside(Point point) {
        Ellipse2D ellipse;
        ellipse = new Ellipse2D.Double(initialPoint.getX(), initialPoint.getY(), width, height);
        return ellipse.contains(point.getX(), point.getY());
    }

    @Override
    public Point getOffset(Point point){
        return new Point((int)(initialPoint.getX() - point.getX()), (int)(initialPoint.getY() - point.getY()));
    }

    @Override
    public int hashCode() {
        return objectID;
    }
}
