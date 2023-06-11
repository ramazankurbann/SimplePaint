package view;

import common.InterfaceManager;
import common.PaintConfiguration;
import view.mainPanels.ConfigurationPanel;
import view.mainPanels.PaintPanel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class ViewManager extends JFrame implements InterfaceManager {

    private static ViewManager viewManager;
    private ConfigurationPanel configurationPanel;
    private PaintPanel paintPanel;

    private ViewManager() {
        configurationPanel = new ConfigurationPanel();
        paintPanel = new PaintPanel();
    }

    public static ViewManager instance(){
        if(viewManager == null){
            viewManager = new ViewManager();
        }

        return viewManager;
    }

    public synchronized void initialize(){
        setSize(1024, 768);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        configurationPanel.initialize();
        paintPanel.initialize();

        add(configurationPanel);
        add(paintPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setLinePanelListener(MouseInputListener listener){
        paintPanel.addMouseListener(listener);
        paintPanel.addMouseMotionListener(listener);
    }

    public PaintConfiguration getPaintConfiguration(){
        return configurationPanel.getPaintConfiguration();
    }

    public PaintPanel getPaintPanel()
    {
        return paintPanel;
    }

}
