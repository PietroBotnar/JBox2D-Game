package betagame;
/*
A step listener which calls the gameOver
*/
import city.cs.engine.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GameoverListener implements StepListener{
    private BetaGame game;
    private BallPlayer ball;
    
    public GameoverListener(BetaGame game){
        this.game = game;
        ball = game.getActor();
    }

    @Override
    public void preStep(StepEvent e) {
        
    }

    @Override
    public void postStep(StepEvent e) {
        if(ball.getBoostCount()==0 && ball.getJumpCount()==0 && ball.getLinearVelocity().abs().x < 0.5 && ball.getLinearVelocity().abs().y < 0.5){
            game.setGameover(true);
                  try {
                      game.gameOver();
                  } catch (IOException ex) {
                      Logger.getLogger(BetaGame.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
    }

    
}
