package controller.processors;

import model.services.InterfacePaintService;
import model.shapeCache.ShapeCacheService;
import common.shapes.AbstractShape;

import java.awt.*;

public abstract class AbstractPaintProcessor implements InterfacePaintProcessor{

    private InterfacePaintService paintService;

    AbstractPaintProcessor(InterfacePaintService paintService){
        this.paintService = paintService;
    }

    public void update(Graphics paintPanelGraphics){
        AbstractShape[] shapes = ShapeCacheService.instance().getAllShapes();

        for(int i = 0; i < shapes.length; i++){
            paintService.paint(paintPanelGraphics, shapes[i]);
        }
    }

    protected boolean pointTolerance(Point pointOne, Point pointTwo){
        return Math.abs(pointOne.getX() + pointOne.getY() - pointTwo.getX() - pointTwo.getY()) > 3;
    }

    InterfacePaintService getPaintService(){
        return paintService;
    }
}
