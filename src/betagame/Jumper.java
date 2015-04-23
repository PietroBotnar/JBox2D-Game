package betagame;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 *this class makes the actor jump if not already jumping
 */
public class Jumper implements StepListener {
        private BallPlayer body;
        private float speed;   
        private boolean jumping = false;
        
        Jumper(BallPlayer body, float speed){
            this.body = body;
            this.speed = speed;
        }

    @Override
    public void preStep(StepEvent e) {
        if(Math.abs(body.getLinearVelocity().y) < 0.5f && !jumping){
            body.setLinearVelocity(new Vec2(body.getLinearVelocity().x,speed));
            body.updateJumpCount();
            //jumping = true;
        }
    }

    @Override
    public void postStep(StepEvent e) {
        
    }  
    public void setBody(BallPlayer ball){
        this.body = ball;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
