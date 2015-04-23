package betagame;

import city.cs.engine.*;
import java.awt.Image;
import java.io.IOException;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private BallPlayer ball;
    private BetaGame game;
    private boolean gameover;
    
    public BallPlayer getActor() {
        return ball;
    }
    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(BetaGame game) {
        ball = new BallPlayer(this);
        ball.setPosition(startPosition());
        ball.setBoostCount(boostCount());
        ball.setJumpCount(jumpCount());
        Wormhole hole = new Wormhole(this);
        hole.setPosition(wormholePosition());
        hole.addCollisionListener(new WormholeListener(game));
        
    }

    public void gameOver(BallPlayer ball) throws IOException{
        if(ball.getBoostCount()==0 && ball.getJumpCount()==0 && ball.getLinearVelocity().abs().x<0.5f){
           game.gameOver();
       }
    };

    
    /** The initial position of the actor. */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door. */
    public abstract Vec2 wormholePosition();
    
    /** The initial number of boosts */
    public abstract int boostCount();
    
     /** The initial number of jumps */
    public abstract int jumpCount();

}
