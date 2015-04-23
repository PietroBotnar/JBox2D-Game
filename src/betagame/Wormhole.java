package betagame;

import city.cs.engine.*;
import java.awt.Color;

/**
 *creates wormhole bodies
 */
public class Wormhole extends StaticBody{
    
    public Wormhole(World world){
        super(world);
        Shape shape = new BoxShape(3,6);
        Fixture fixture = new SolidFixture(this,shape,1);
        this.setFillColor(Color.black);
        this.setImage(new BodyImage("data/wormhole.gif",12));
    }
}
