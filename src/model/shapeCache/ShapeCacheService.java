package model.shapeCache;

import common.DataElements;
import common.shapes.AbstractShape;

import java.awt.*;
import java.util.*;

public class ShapeCacheService {
    private static ShapeCacheService shapeCacheService;
    private HashMap<Integer, AbstractShape> lineShapeMap;
    private HashMap<Integer, AbstractShape> ovalShapeMap;
    private HashMap<Integer, AbstractShape> rectangleShapeMap;
    private HashMap<Integer, AbstractShape>[] shapeMapList;
    private LinkedList<AbstractShape> shapeList;

    public static ShapeCacheService instance(){
        if(shapeCacheService == null){
            shapeCacheService = new ShapeCacheService();
        }

        return shapeCacheService;
    }

    private ShapeCacheService(){
        shapeMapList = new HashMap[DataElements.Shapes.values().length-1];
        shapeMapList[DataElements.Shapes.rectangle.getValue()] = new HashMap<Integer, AbstractShape>();
        shapeMapList[DataElements.Shapes.oval.getValue()] = new HashMap<Integer, AbstractShape>();
        shapeMapList[DataElements.Shapes.freeLine.getValue()] = new HashMap<Integer, AbstractShape>();
        //shapeList = new LinkedList<AbstractShape>();
    }

    public void write(AbstractShape abstractShape){
        if(abstractShape.getShape() != DataElements.Shapes.none){
            shapeMapList[abstractShape.getShape().getValue()].put(abstractShape.hashCode(), abstractShape);
            //shapeList.addLast(abstractShape);
        }
    }

    public void delete(AbstractShape abstractShape){
        if(abstractShape.getShape() != DataElements.Shapes.none) {
            shapeMapList[abstractShape.getShape().getValue()].remove(abstractShape.hashCode());
            //shapeList.remove(abstractShape);
        }
    }

    public AbstractShape[] getAllShapes(){
        ArrayList<AbstractShape> abstractShapeList = new ArrayList<AbstractShape>();

        for(int i = 0; i < DataElements.Shapes.values().length-1; i++){
            Iterator<Map.Entry<Integer, AbstractShape>> iterator = shapeMapList[i].entrySet().iterator();

            for (int j = 0; iterator.hasNext(); j++){
                Map.Entry<Integer, AbstractShape> shape = iterator.next();
                abstractShapeList.add(shape.getValue());
            }
        }
        AbstractShape[] resultShapes = new AbstractShape[abstractShapeList.size()];

        return abstractShapeList.toArray(resultShapes);

        /*AbstractShape[] resultShapes = new AbstractShape[shapeList.size()];
        return shapeList.toArray(resultShapes);*/
    }

    public AbstractShape[] getAllShapesOf(DataElements.Shapes shapeType){

        AbstractShape[] resultAbstractShape = new AbstractShape[shapeMapList[shapeType.getValue()].size()];

        Iterator<Map.Entry<Integer, AbstractShape>> iterator = shapeMapList[shapeType.getValue()].entrySet().iterator();

        for (int i = 0; iterator.hasNext(); i++){
            Map.Entry<Integer, AbstractShape> shape = iterator.next();
            resultAbstractShape[i] = shape.getValue();
        }

        return resultAbstractShape;
    }

    public AbstractShape getShapeWithFilter(DataElements.Shapes shapeType, Point point){

        AbstractShape resultAbstractShape = null;

        Iterator<Map.Entry<Integer, AbstractShape>> iterator = shapeMapList[shapeType.getValue()].entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, AbstractShape> shape = iterator.next();

            if (shape.getValue().isInside(point)){
                resultAbstractShape = shape.getValue();
            }
        }

        return resultAbstractShape;
    }

    public AbstractShape getAnyShapeWithFilter(Point point){
        AbstractShape resultAbstractShape = null;

        for(int i = 0; i < DataElements.Shapes.values().length-1; i++){
            Iterator<Map.Entry<Integer, AbstractShape>> iterator = shapeMapList[i].entrySet().iterator();

            for (int j = 0; iterator.hasNext(); j++){
                Map.Entry<Integer, AbstractShape> shape = iterator.next();
                if(shape.getValue().isInside(point)){
                    resultAbstractShape = shape.getValue().clone();
                }
            }
        }

        return resultAbstractShape;
    }
}
