package betagame;
// this class creates instances of the game actor

import city.cs.engine.*;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jbox2d.common.Vec2;

public class BallPlayer extends DynamicBody{
    private static final float ballRadius = 2f;
    private float ballRestitution=0.3f;
    private float ballFriction = 5f;
    private float ballOuterDensity = 30f;
    private float ballInnerDensity = 30f;
   
    private int starCount;
    private int boostCount;
    private int jumpCount;
    
    private int imageNumber;
    
    private List<ChangeListener> listeners;
    private List<String> imagesLinks;
    
    public BallPlayer(World world){
        super(world);
        float density = ballOuterDensity / 8;
        float x = ballRadius/10;
        
        float y1 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x,2.0));
        float y2 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*2,2.0));
        float y3 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*3,2.0));
        float y4 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*4,2.0));
        float y5 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*5,2.0));
        float y6 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*6,2.0));
        float y7 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*7,2.0));
        float y8 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*8,2.0));
        float y9 = (float)Math.sqrt(Math.pow(ballRadius,2.0)-Math.pow(x*9,2.0));
        
        Shape shape = new PolygonShape(0,ballRadius,x,y1,x*2,y2,x*3,y3,x*4,y4,x*5,y5);
        SolidFixture f = new SolidFixture(this,shape,0); 
        f.setFriction(ballFriction);
        f.setRestitution(ballRestitution);
        f.setDensity(density);
        
        Shape shape2 = new PolygonShape(x*5,y5,x*6,y6,x*7,y7,x*8,y8,x*9,y9,ballRadius,0);
        SolidFixture f2 = new SolidFixture(this,shape2,0);
        f2.setFriction(ballFriction);
        f2.setRestitution(ballRestitution);
        f2.setDensity(density);
        
        Shape shape3 = new PolygonShape(0,-ballRadius,x,-y1,x*2,-y2,x*3,-y3,x*4,-y4,x*5,-y5);
        SolidFixture f3 = new SolidFixture(this,shape3,0);  
        f3.setFriction(ballFriction);
        f3.setRestitution(ballRestitution);
        f3.setDensity(density);
        
        Shape shape4 = new PolygonShape(x*5,-y5,x*6,-y6,x*7,-y7,x*8,-y8,x*9,-y9,ballRadius,0);
        SolidFixture f4 = new SolidFixture(this,shape4,0);
        f4.setFriction(ballFriction);
        f4.setRestitution(ballRestitution);
        f4.setDensity(density);
        
        Shape shape5 = new PolygonShape(0,-ballRadius,-x,-y1,-x*2,-y2,-x*3,-y3,-x*4,-y4,-x*5,-y5);
        SolidFixture f5 = new SolidFixture(this,shape5,0);
        f5.setFriction(ballFriction);
        f5.setRestitution(ballRestitution);
        f5.setDensity(density);
        
        Shape shape6 = new PolygonShape(-x*5,-y5,-x*6,-y6,-x*7,-y7,-x*8,-y8,-x*9,-y9,-ballRadius,0);
        SolidFixture f6 = new SolidFixture(this,shape6,0);
        f6.setFriction(ballFriction);
        f6.setRestitution(ballRestitution);
        f6.setDensity(density);

        Shape shape7 = new PolygonShape(0,ballRadius,-x,y1,-x*2,y2,-x*3,y3,-x*4,y4,-x*5,y5);
        SolidFixture f7 = new SolidFixture(this,shape7,0); 
        f7.setFriction(ballFriction);
        f7.setRestitution(ballRestitution);
        f7.setDensity(density);
        
        Shape shape8 = new PolygonShape(-x*5,y5,-x*6,y6,-x*7,y7,-x*8,y8,-x*9,y9,-ballRadius,0);
        SolidFixture f8 = new SolidFixture(this,shape8,0);
        f8.setFriction(ballFriction);
        f8.setRestitution(ballRestitution);
        f8.setDensity(density);
        
        
       Shape circle = new CircleShape(ballRadius-0.5f,new Vec2(0,0));
       SolidFixture fixture = new SolidFixture(this,circle,ballInnerDensity);
       
       this.setFillColor(Color.black);
       
       imagesLinks = new LinkedList<String>();
       this.imagesLinks.add("data/ball.png");
       this.imagesLinks.add("data/ball1.png");
       this.imagesLinks.add("data/ball2.png");
       
       setBallImage(imageNumber);
       this.listeners = new LinkedList<ChangeListener>();  
       
    }
    public void setBallPhysics(int x){
        if(x==1){
           ballRestitution=10f;
           ballOuterDensity=10;
       }
       if(x==2){
           ballRestitution=0.6f;
           ballOuterDensity=50;
           ballInnerDensity=50;
       }
       
    }
    //sets the ball image
    //param image number
    public void setBallImage(int imageNumber){
        this.imageNumber = imageNumber;
        this.setImage(new BodyImage(imagesLinks.get(imageNumber),2*ballRadius));
    }
    public List<String> getImagesLinks() {
        return imagesLinks;
    }

    public float getBallRestitution() {
        return ballRestitution;
    }


    public void addStarPoint(){
        starCount ++;
        changed();
    }
    public void addBoost(){
        boostCount ++;
        changed();
    }
    public void addJump(){
        jumpCount ++;
        changed();
    }

    public int getStarCount() {
        return starCount;
    }

    public int getBoostCount() {
        return boostCount;
    }

    public int getJumpCount() {
        return jumpCount;
    }
    
    public void updateBoostCount() {
         boostCount--;
         changed();
         
    }
    
    public void updateJumpCount() {
         jumpCount--;
         changed();
    }
    
    public void setBoostCount(int x){
        boostCount = x;
        changed();
    }
    public void setJumpCount(int x){
        jumpCount = x;
        changed();
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
        changed();
    }
/**
     * Register a listener to be notifed whenever the model changes.
     * @param listener the listener to add
     */
    public void addChangeListener(ChangeListener l) {
        listeners.add(l);
    }
    
    /**
     * Unregister a change listener.
     * Does nothing if the listener is not registered.
     * @param listener the listener to remove
     */
    public void removeChangeListener(ChangeListener l) {
        listeners.remove(l);
    }
    
    /**
     * Notify all listeners that the model has changed.
     * This method should be called by any method of a child class that
     * modifies the child data.
     */
    protected void changed() {
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener l : listeners) {
            l.stateChanged(e);
        }
        
    }
}