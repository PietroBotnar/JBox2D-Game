package betagame;
/*
create star bodies to pick up
*/
import city.cs.engine.*;
import java.awt.Color;
import org.jbox2d.common.Vec2;

public class Star extends DynamicBody {
    private float starRadius;
    
    public Star(World world, float radius){ 
        super(world);
        this.starRadius = radius;
        Shape star_shape1 = new PolygonShape(0,starRadius,starRadius,-starRadius/2,-starRadius,-starRadius/2);
        Shape star_shape2 = new PolygonShape(0,-starRadius,-starRadius,+starRadius/2,starRadius,starRadius/2);
        SolidFixture s1 = new SolidFixture(this,star_shape1,0);
        SolidFixture s2 = new SolidFixture(this,star_shape2,0);
        s1.setFriction(100);
        s2.setFriction(100);
        this.setFillColor(Color.yellow);
        
    }

    public int getStarRadius() {
        return (int)starRadius;
    }
    public boolean isStar(){
        return true;
    }
}
