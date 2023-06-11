package model;

import common.InterfaceManager;
import controller.ControllerManager;
import model.services.InterfacePaintService;
import model.services.PaintServiceBridge;

public class ModelManager implements InterfaceManager {

    private static ModelManager modelManager;
    PaintServiceBridge paintServiceBridge;

    public ModelManager() {
        paintServiceBridge = new PaintServiceBridge();
    }

    public static ModelManager instance(){
        if(modelManager == null){
            modelManager = new ModelManager();
        }

        return modelManager;
    }

    @Override
    public void initialize() {

    }

    public PaintServiceBridge getPaintServiceBridge()
    {
        return paintServiceBridge;
    }
}
