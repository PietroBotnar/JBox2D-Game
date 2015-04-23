package betagame;
//this class makes the body move forward

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Booster implements StepListener {
        private BallPlayer body;
        private float speed;       
        private float a;
        
        Booster(BallPlayer body, float speed) {
        this.body = body;
        this.speed = speed;
        
    }

        
    @Override
    public void preStep(StepEvent e) {
        body.setLinearVelocity(new Vec2(speed,body.getLinearVelocity().y));
       // body.applyImpulse(new Vec2(speed,0));
       // body.applyForce(new Vec2(speed,0));
    }

    @Override
    public void postStep(StepEvent e) {
        
    }
    public void setBody(BallPlayer ball){
        this.body = ball;
    }    
}
