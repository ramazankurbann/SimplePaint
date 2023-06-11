package common.shapes;

import common.DataElements;

import java.awt.*;

public abstract class AbstractShape {
    protected DataElements.Shapes shape;

    abstract public DataElements.Shapes getShape();
    abstract public AbstractShape clone();
    abstract public boolean isInside(Point point);
    abstract public Point getOffset(Point point);
}
