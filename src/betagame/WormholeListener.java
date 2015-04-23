package betagame;
/*
a collision listener with the wormhole which calls the changeLevel method
*/
import city.cs.engine.*;

public class WormholeListener implements CollisionListener{
    private BetaGame game;
    
    public WormholeListener(BetaGame game){
        this.game = game; 
    }

    @Override
    public void collide(CollisionEvent e) {
        BallPlayer ball = game.getActor();
        if (e.getOtherBody()== ball){
            System.out.println("LEVEL "+(game.getLevelNum()+1)+" COMPLETE Score: "+ball.getStarCount());
            ball.changed();
            ball.destroy();
            game.increaseLevelNum();
            game.changeLevel();
        }
    }
}
