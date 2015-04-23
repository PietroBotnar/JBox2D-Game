package betagame;
//a collision listener that destroies specified bodies (star, boost and jump)
//and updates the score
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Pickup  implements CollisionListener {
    private BallPlayer ball;
    
    public Pickup(BallPlayer ball){
        this.ball = ball;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody()==ball){
            if(e.getReceivingBody().getClass().equals(Star.class)){
                ball.addStarPoint();
            }
            if(e.getReceivingBody().getClass().equals(Boost.class)){
                ball.addBoost();
            }
            if(e.getReceivingBody().getClass().equals(Jump.class)){
                ball.addJump();
            }
            
            e.getReceivingBody().destroy();
        
    }
    
}
}
