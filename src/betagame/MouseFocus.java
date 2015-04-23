package betagame;
//gives focus on the component clicked
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseFocus extends MouseAdapter{
     private Component target;
     
     public MouseFocus(Component target){
         this.target = target;
     }

    @Override
    public void mouseEntered(MouseEvent me) {
         target.requestFocus();
    }
}
