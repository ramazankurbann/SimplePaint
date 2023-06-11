package model.shapeCache;

import java.util.PriorityQueue;
import java.util.Queue;

public class ObjectIDGenerator {

    private static ObjectIDGenerator objectIDGenerator;
    private Integer objectID;
    private Queue<Integer> releasedObjectIDQueue;

    private ObjectIDGenerator(){
        releasedObjectIDQueue = new PriorityQueue<Integer>();
        objectID = 1;
    }

    public static ObjectIDGenerator instance(){
        if(objectIDGenerator == null){
            objectIDGenerator = new ObjectIDGenerator();
        }

        return objectIDGenerator;
    }

    public Integer getNextObjectID(){
        Integer resultObjectID = 0;

        if(releasedObjectIDQueue.isEmpty()){
            objectID += 1;
            resultObjectID = objectID;
        }
        else{
            resultObjectID = releasedObjectIDQueue.poll();
        }
        return resultObjectID;
    }

    public void releaseObjectID(Integer objectID){
        releasedObjectIDQueue.add(objectID);
    }
}
