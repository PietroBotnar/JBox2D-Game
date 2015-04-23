package betagame;
/*
a game level 
*/
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel{
    private BallPlayer ball;
    private Star starPoint;
    private Jump jumpPoint;
    private Boost boostPoint; 
    
    
    public Level2(BetaGame game){
        super.populate(game);
        ball = this.getActor();
        
        {//ground
            Shape ground_shape = new BoxShape(430,1);
            Body ground_body = new StaticBody(this,ground_shape);
            SolidFixture ground_fixture = new SolidFixture(ground_body,ground_shape);
            ground_body.setPosition(new Vec2(420,0));           
            ground_fixture.setFriction(20);
        }
        {//floors
            Shape shape1 = new BoxShape(20,0.5f);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(60,10));
            body1.setAngleDegrees(3);
            Body body2 = new StaticBody(this,shape1);
            body2.setPosition(new Vec2(110,20));
            body2.setAngleDegrees(-3);
            Body body3 = new StaticBody(this,shape1);
            body3.setPosition(new Vec2(160,30));
            body3.setAngleDegrees(2);
            Shape shape2 = new BoxShape(10,0.5f);
            Body body4 = new StaticBody(this,shape2);
            body4.setPosition(new Vec2(210,35));
            Body body5 = new StaticBody(this,shape1);
            body5.setPosition(new Vec2(260,40));
            Body body6 = new StaticBody(this,shape2);
            body6.setPosition(new Vec2(300,35));
            body6.setAngleDegrees(10);
            Body body7 = new StaticBody(this,shape1);
            body7.setPosition(new Vec2(350,20));
            Body body8 = new StaticBody(this,shape1);
            body8.setPosition(new Vec2(434,25));
            body8.setAngleDegrees(45);
            Body body9 = new StaticBody(this, shape2);
            body9.setPosition(new Vec2(458,39));
            Body body10 = new StaticBody(this,shape1);
            body10.setPosition(new Vec2(478,60));
            body10.setAngleDegrees(45);
            Body body11 = new StaticBody(this, shape2);
            body11.setPosition(new Vec2(502,74));
            Shape shape3 = new BoxShape(40,0.5f);
            Body body12 = new StaticBody(this,shape3);
            body12.setPosition(new Vec2(555,81));
            Body body13 = new StaticBody(this,shape2);
            body13.setPosition(new Vec2(495,40));
            body13.setAngleDegrees(5);
            Body body14 = new StaticBody(this,shape2);
            body14.setPosition(new Vec2(550,45));
            Body body15 = new StaticBody(this,shape2);
            body15.setPosition(new Vec2(680,10));
            Body body16 = new StaticBody(this,shape2);
            body16.setPosition(new Vec2(710,20));
            Body body17 = new StaticBody(this,shape2);
            body17.setPosition(new Vec2(740,30));
            Body body18 = new StaticBody(this,shape2);
            body18.setPosition(new Vec2(777,42));
            body18.setAngleDegrees(5);
            Body body19 = new StaticBody(this,shape2);
            body19.setPosition(new Vec2(820,47));
        }
        {//obstacles
            Shape shape1 = new BoxShape(0.5f,10);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(370,5));
            
            Shape shape2 = new BoxShape(1,6);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(60,6));
            
            Shape shape3 = new BoxShape(0.5f,5);
            Body body3 = new StaticBody(this,shape3);
            body3.setPosition(new Vec2(315,20));
            
            Shape shape4 = new BoxShape(0.5f,40);
            Body body4 = new StaticBody(this,shape4);
            body4.setPosition(new Vec2(850.5f,39));
            
            Body body5 = new StaticBody(this,shape2);
            body5.setPosition(new Vec2(420,5));
        }
        {//polygons
            Shape shape1 = new PolygonShape(0,0, 20,0, 10,10);
            Body body1 = new StaticBody(this, shape1);
            body1.setPosition(new Vec2(520,30));
            Body body2 = new StaticBody(this, shape1);
            body2.setPosition(new Vec2(550, 20));
            body2.setAngleDegrees(40);
            Body body3 = new StaticBody(this, shape1);
            body3.setPosition(new Vec2(570, 15));
            body3.setAngleDegrees(-40);
            
        }
        {//tower
            for(int i=0; i<5; i++){
                for(int j=0; j<10; j++){
                    Shape shape1 = new BoxShape(1,1);
                    Body body1 = new DynamicBody(this,shape1);
                    body1.setPosition(new Vec2(210+2*i,36+2*j));
                    Fixture fix1 = new SolidFixture(body1,shape1,10);
                    }
            }//pyramid
            for(int y=0; y<20;y++){
                for(int x=0;x<20-y;x++){
                    Shape shape = new BoxShape(0.5f,0.5f);
                    Body box = new DynamicBody(this, shape);
                    box.setPosition(new Vec2(620+(x)+(y*0.5f),0.75f+(y)));
                    Fixture fix1 = new SolidFixture(box,shape,10);
                }
            }
        }
        
        {//stars
            for(int i=0; i<41;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(20+20*i,50));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            } 
            for(int i=0; i<27;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(516+3*i,85));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            }  
        }
        {//jumps
            for(int i=0;i<9;i++){
                jumpPoint = new Jump(this);
                jumpPoint.setPosition(new Vec2(10+100*i,50));
                jumpPoint.addCollisionListener(new Pickup(super.getActor()));
            }
        }
        {//boosts
            for(int i=0;i<10;i++){
                boostPoint = new Boost(this);
                boostPoint.setPosition(new Vec2(30+90*i,50));
                boostPoint.addCollisionListener(new Pickup(super.getActor()));
            }
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, 10);
    }

    @Override
    public Vec2 wormholePosition() {
        return new Vec2(840, 47);
    }

    @Override
    public int boostCount() {
        return 15;
    }

    @Override
    public int jumpCount() {
        return 15; 
   }


}
