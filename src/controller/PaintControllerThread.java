package controller;

import common.PaintConfiguration;
import controller.processors.PaintProcessorBridge;
import model.ModelManager;
import view.ViewManager;

import javax.swing.*;

public class PaintControllerThread implements Runnable{

    PaintProcessorBridge paintProcessorBridge;
    PaintConfiguration paintConfiguration;
    JPanel paintPanel;

    public PaintControllerThread() {
        paintProcessorBridge = new PaintProcessorBridge(ModelManager.instance().getPaintServiceBridge());
    }

    public void initialize(){
        paintConfiguration = ViewManager.instance().getPaintConfiguration();
        paintPanel = ViewManager.instance().getPaintPanel();
    }

    @Override
    public void run() {
        while (true) {
            paintProcessorBridge.process(paintPanel, paintConfiguration);
            paintProcessorBridge.update(paintPanel.getGraphics());
            try {
                Thread.sleep(10);
            }
            catch (Exception e){
            }
        }
    }
}
