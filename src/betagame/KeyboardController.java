package betagame;
/*
keyboard controller which controlls the actor actions
press SPACE to roll forward(boost)
press UP to jump
press DOWN to stop
press Q to quit
press I to print position and linear velocity of the actor
*/
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    private static final float ROLLING_SPEED = 15f;
    private static final float JUMPING_SPEED = 15f;
    private static final float STOP_SPEED = 0f;
    
    private BallPlayer ball;
    private World world;
    private Booster roll;

    private Jumper jump;
    private Booster stopRoll;
    private boolean keyheld=false;
    private boolean jumping = false;
    
    public KeyboardController(BallPlayer ball) {
        this.ball = ball;
        this.world = ball.getWorld();
        this.roll = new Booster(ball, ROLLING_SPEED);
        this.jump = new Jumper(ball, JUMPING_SPEED);
        this.stopRoll = new Booster(ball, STOP_SPEED);
    } 
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();  
        //press SPACE TO ROLL
            if ( code ==KeyEvent.VK_SPACE && !keyheld && ball.getBoostCount()>0){ 
                    world.addStepListener(roll);
                    keyheld = true;
                    ball.updateBoostCount();
            }
            else {world.removeStepListener(roll);          
            }  
            
            //press UP to JUMP       
            if( code == KeyEvent.VK_UP && !keyheld &&  ball.getJumpCount()>0){ 
                
                    world.addStepListener(jump);
                    keyheld = true;
                
            }
            else {world.removeStepListener(jump);
            }
            if( e.getKeyCode() == KeyEvent.VK_DOWN){//press DOWN to STOP
                ball.setLinearVelocity(new Vec2(STOP_SPEED,STOP_SPEED));
            }
            if( e.getKeyCode() == KeyEvent.VK_I){ //press I to print velocity and position
                System.out.println("Linear Velocity: "+ball.getLinearVelocity()+" Position: "+ball.getPosition());  
            } 
            if( e.getKeyCode() == KeyEvent.VK_Q){ //press Q to quit
                System.exit(0);
            } 
    }
    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            world.removeStepListener(roll);
           // world.addStepListener(jump);
            keyheld = false;
            //jump.setJumping(true);
            
        
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            world.removeStepListener(jump);
            keyheld = false;
        }    
    }
    
    public void setBody(BallPlayer ball){
        this.ball = ball;
        this.world = ball.getWorld();
        jump.setBody(ball);
        roll.setBody(ball);
    }
    
}