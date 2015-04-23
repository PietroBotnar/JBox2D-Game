package betagame;
//class creates jump bodies to pick up
import city.cs.engine.*;
import java.awt.Color;
import org.jbox2d.common.Vec2;

public class Jump extends DynamicBody{
    private float jumpRadius = 1.2f;
    
    public Jump(World world){
        super(world);
        Shape shape = new CircleShape(jumpRadius);
        SolidFixture s = new SolidFixture(this,shape,0);
        this.setFillColor(Color.GREEN);
        this.setImage(new BodyImage("data/jump.png",2*jumpRadius));
    }
}
