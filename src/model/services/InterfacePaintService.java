package model.services;

import common.PaintConfiguration;
import common.shapes.AbstractShape;

import java.awt.*;

public interface InterfacePaintService {
    public AbstractShape createShape(PaintConfiguration paintConfiguration, Point previousPoint, Point currentPoint);
    public void writeShape(AbstractShape shape);
    public void deleteShape(AbstractShape shape);
    public void changePositionOf(AbstractShape shape, Point offsetPoint, Point newPoint);
    void paint(Graphics graphics, AbstractShape shape);
}
