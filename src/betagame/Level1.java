package betagame;
/*
a game level 
*/
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {
    private Star starPoint;
    private Jump jumpPoint;
    private Boost boostPoint;
    private Wormhole wormHole;
    
    public Level1(BetaGame game){
        super.populate(game);
        
        {//ground
            Shape ground_shape = new BoxShape(500,1);
            Body ground_body = new StaticBody(this,ground_shape);
            SolidFixture ground_fixture = new SolidFixture(ground_body,ground_shape);
            ground_body.setPosition(new Vec2(490,0));           
            ground_fixture.setFriction(20);
        }
        {//obstacles
            Shape shape1 = new BoxShape(1,2);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(30,2));
            
            Shape shape2 = new BoxShape(1,6);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(60,6));
            
            Shape shape3 = new BoxShape(0.5f,5);
            Body body3 = new StaticBody(this,shape3);
            body3.setPosition(new Vec2(315,20));
            
            Shape shape4 = new BoxShape(0.5f,20);
            Body body4 = new StaticBody(this,shape4);
            body4.setPosition(new Vec2(940,30));
            
            Shape shape5 = new BoxShape(0.5f,40);
            Body body5 = new StaticBody(this,shape5);
            body5.setPosition(new Vec2(990.5f,39f));
        }
        {//floors
            Shape shape1 = new BoxShape(2,0.5f);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(70,10));
            
            Shape shape2 = new BoxShape(4,0.5f);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(120,10));
            
            Body body3 = new StaticBody(this,shape2);
            body3.setPosition(new Vec2(140,20));
            
            Body body4 = new StaticBody(this,shape2);
            body4.setPosition(new Vec2(217,25));
            
            Shape shape3 = new BoxShape(10,0.5f);
            Body body5 = new StaticBody(this,shape3);
            body5.setPosition(new Vec2(305,15));
            
            Body body6 = new StaticBody(this,shape3);
            body6.setPosition(new Vec2(320,25));
            
            Body body7 = new StaticBody(this,shape3);
            body7.setPosition(new Vec2(340,20));
            
            Body body8 = new StaticBody(this,shape2);
            body8.setPosition(new Vec2(652.5f,22));
            
            Body body9 = new StaticBody(this,shape3);
            body9.setPosition(new Vec2(700,15));
            
            Body body10 = new StaticBody(this,shape3);
            body10.setPosition(new Vec2(730,25));
            
            Body body11 = new StaticBody(this,shape3);
            body11.setPosition(new Vec2(760,20));
            
            Body body12 = new StaticBody(this,shape3);
            body12.setPosition(new Vec2(780,10));
            
            Body body13 = new StaticBody(this,shape3);
            body13.setPosition(new Vec2(810,20));
            
            Body body14 = new StaticBody(this,shape3);
            body14.setPosition(new Vec2(830,30));
            
            Body body15 = new StaticBody(this,shape3);
            body15.setPosition(new Vec2(860,30));
        }
        {//ramps
            Shape shape1 = new PolygonShape(0,0,50,0,50,15,25,15);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(170,0));
            
            Shape shape2 = new PolygonShape(0,0,35,0,0,20);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(240,0));
            
            Shape shape3 = new PolygonShape(0,0,15,0,15,5);
            Body body3 = new StaticBody(this,shape3);
            body3.setPosition(new Vec2(275,0));
            
            Shape shape4 = new PolygonShape(0,0,25,0,10,12,0,12);
            Body body4 = new StaticBody(this,shape4);
            body4.setPosition(new Vec2(550,0));
            
            Shape shape5 = new PolygonShape(0,0,40,0,35,35,5,35);
            Body body5 = new StaticBody(this,shape5);
            body5.setPosition(new Vec2(890,6));
        }
        {//towers
            for(int i=0; i<5; i++){
                for(int j=0; j<10; j++){
                    Shape shape1 = new BoxShape(1,1);
                    Body body1 = new DynamicBody(this,shape1);
                    body1.setPosition(new Vec2(580+2*i,1+2*j));
                    Fixture fix1 = new SolidFixture(body1,shape1,10);
                    }
            }
           Shape shape1 = new BoxShape(1,5);
           Body body1 = new DynamicBody(this,shape1);
           body1.setPosition(new Vec2(640,2.5f));
           Fixture fix1 = new SolidFixture(body1,shape1,20);
           
           Body body2 = new DynamicBody(this,shape1);
           body2.setPosition(new Vec2(645,2.5f));
           Fixture fix2 = new SolidFixture(body2,shape1,20);
           
           Body body3 = new DynamicBody(this,shape1);
           body3.setPosition(new Vec2(660,2.5f));
           Fixture fix3 = new SolidFixture(body3,shape1,20);
           
           Body body4 = new DynamicBody(this,shape1);
           body4.setPosition(new Vec2(665,2.5f));
           Fixture fix4 = new SolidFixture(body4,shape1,20);
           
           Shape shape2 = new BoxShape(20,0.5f);
           Body body = new DynamicBody(this,shape2);
           body.setPosition(new Vec2(652.5f,5.25f));
           Fixture fix = new SolidFixture(body,shape2,5);
           
        }
        {//stars
            for(int i=0; i<45;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(20+20*i,40));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            }  
        }
        {//jumps
            for(int i=0;i<10;i++){
                jumpPoint = new Jump(this);
                jumpPoint.setPosition(new Vec2(40+100*i,40));
                jumpPoint.addCollisionListener(new Pickup(super.getActor()));
            }
        }
        {//boosts
            for(int i=0;i<10;i++){
                boostPoint = new Boost(this);
                boostPoint.setPosition(new Vec2(30+100*i,40));
                boostPoint.addCollisionListener(new Pickup(super.getActor()));
            }
        }
        
    }
 

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,10);
    }

    @Override
    public Vec2 wormholePosition() {
        return new Vec2(984,7);
    }

    @Override
    public int boostCount() {
        return 20;
    }

    @Override
    public int jumpCount() {
        return 20;
    }

    
}
