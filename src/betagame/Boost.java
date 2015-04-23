package betagame;
//this class creates boost bodies to pick up
import city.cs.engine.*;
import java.awt.Color;
import org.jbox2d.common.Vec2;

public class Boost extends DynamicBody {
    private float boostRadius = 1.3f;
    
    public Boost(World world){
        super(world);
        Shape shape = new CircleShape(boostRadius);
        SolidFixture s = new SolidFixture(this,shape,0);
        this.setFillColor(Color.RED);
        this.setImage(new BodyImage("data/boost.png",2*boostRadius));
    }
}
