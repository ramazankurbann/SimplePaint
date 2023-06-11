package common.shapes;

import common.DataElements;

import java.awt.*;

public class RectangleShape extends AbstractShape{
    public Integer objectID;
    public Color color;
    public Point initialPoint;
    public Integer width;
    public Integer height;

    public RectangleShape() {
        shape = DataElements.Shapes.rectangle;
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
    public boolean isInside(Point point) {
        return (point.getX() <= (initialPoint.getX() + width) &&
                point.getX() >= initialPoint.getX() &&
                point.getY() <= (initialPoint.getY() + height)  &&
                point.getY() >= initialPoint.getY() );
    }

    @Override
    public Point getOffset(Point point){
        return new Point((int)(initialPoint.getX() - point.getX()), (int)(initialPoint.getY() - point.getY()));
    }

    @Override
    public RectangleShape clone(){
        RectangleShape rectangleShape = new RectangleShape();

        rectangleShape.shape = this.shape;
        rectangleShape.objectID = this.objectID;
        rectangleShape.color = this.color;
        rectangleShape.initialPoint = this.initialPoint;
        rectangleShape.width = this.width;
        rectangleShape.height = this.height;

        return rectangleShape;
    }

    @Override
    public int hashCode() {
        return objectID;
    }
}
