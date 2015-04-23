package betagame;
//mouse controller that prints coordinates whitin the game world when pressed
import java.awt.event.MouseAdapter;
import city.cs.engine.*;
import java.awt.event.MouseEvent;
import org.jbox2d.common.Vec2;

public class MouseControl extends MouseAdapter{
    private WorldView view;
    
    public MouseControl(WorldView view){
        this.view = view;
    }
    public void mousePressed(MouseEvent e){
       
        System.out.println("mouse position :"+view.viewToWorld(view.getMousePosition()));
    }
    
}
