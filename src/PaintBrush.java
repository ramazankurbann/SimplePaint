import controller.ControllerManager;
import model.ModelManager;
import view.ViewManager;

public class PaintBrush
{
    public static void main(String[] args)
    {
        ViewManager.instance().initialize();
        ModelManager.instance().initialize();
        ControllerManager.instance().initialize();

        ControllerManager.instance().run();
    }
}