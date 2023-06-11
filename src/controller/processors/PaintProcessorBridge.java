package controller.processors;

import common.DataElements;
import common.PaintConfiguration;
import model.services.InterfacePaintService;

import javax.swing.*;

public class PaintProcessorBridge extends AbstractPaintProcessor
{
    InterfacePaintProcessor[] paintProcessors;

    public PaintProcessorBridge(InterfacePaintService paintService){
        super(paintService);
        paintProcessors = new InterfacePaintProcessor[DataElements.Tools.values().length-1];

        DiscretePaintProcessor discretePaintProcessor = new DiscretePaintProcessor(paintService);
        paintProcessors[DataElements.Tools.rectangle.getValue()] = discretePaintProcessor;
        paintProcessors[DataElements.Tools.oval.getValue()] = discretePaintProcessor;
        paintProcessors[DataElements.Tools.freeLine.getValue()] = new ContinuousPaintProcessor(paintService);
        paintProcessors[DataElements.Tools.drag.getValue()] = new DragPaintProcessor(paintService);
    }

    @Override
    public void process(JPanel paintPanel, PaintConfiguration paintConfiguration)
    {
        if(isValidPaintRequest(paintConfiguration)){
            paintProcessors[paintConfiguration.tool.getValue()].process(paintPanel, paintConfiguration);
        }
    }

    private boolean isValidPaintRequest(PaintConfiguration paintConfiguration){
        return (paintConfiguration.tool != DataElements.Tools.none);
    }
}
