package common.shapes;

import common.DataElements;

import java.awt.*;

public class LineShape extends AbstractShape{

    public Integer objectID;
    public Color color;
    public Point initialPoint;
    public Point lastPoint;

    public LineShape() {
        shape = DataElements.Shapes.freeLine;
        objectID = 0;
        color = Color.BLACK;
        initialPoint = new Point(0,0);
        lastPoint = new Point(0,0);
    }

    @Override
    public DataElements.Shapes getShape(){
        return shape;
    }

    @Override
    public LineShape clone(){
        LineShape lineShape = new LineShape();

        lineShape.shape = this.shape;
        lineShape.objectID = this.objectID;
        lineShape.color = this.color;
        lineShape.initialPoint = this.initialPoint;
        lineShape.lastPoint = this.lastPoint;

        return lineShape;
    }

    @Override
    public boolean isInside(Point point) {
        return false;
    }

    @Override
    public Point getOffset(Point point){
        return new Point();
    }

    @Override
    public int hashCode() {
        return objectID;
    }
}
