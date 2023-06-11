package controller;

import common.InterfaceManager;
import controller.listeners.PaintPanelListener;
import view.ViewManager;

public class ControllerManager implements InterfaceManager
{
    private static ControllerManager controllerManager;

    private PaintControllerThread paintControllerThread;
    private PaintPanelListener paintPanelListener;

    public ControllerManager() {
        paintPanelListener = new PaintPanelListener();
        paintControllerThread = new PaintControllerThread();
    }

    public static ControllerManager instance(){
        if(controllerManager == null){
            controllerManager = new ControllerManager();
        }

        return controllerManager;
    }

    @Override
    public void initialize() {
        paintControllerThread.initialize();
        ViewManager.instance().setLinePanelListener(paintPanelListener);
    }

    public void run(){
        Thread t = new Thread(paintControllerThread);
        t.start();
    }

    public PaintPanelListener getPaintPanelListener()
    {
        return paintPanelListener;
    }
}
