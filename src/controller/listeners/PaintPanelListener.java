package controller.listeners;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class PaintPanelListener implements MouseInputListener
{
    private boolean paintPermission;

    public PaintPanelListener() {
        paintPermission = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintPermission = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        paintPermission = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        paintPermission = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public boolean getPaintPermission() {
        return paintPermission;
    }
}
