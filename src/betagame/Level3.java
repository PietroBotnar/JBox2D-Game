package betagame;
/*
a game level 
*/
import org.jbox2d.common.Vec2;
import city.cs.engine.*;

public class Level3 extends GameLevel{
    private BallPlayer ball;
    private Star starPoint;
    private Jump jumpPoint;
    private Boost boostPoint;
    
    public Level3(BetaGame game) {
        super.populate(game);
        ball = this.getActor();
        
        {//ground
            Shape ground_shape = new BoxShape(400,1);
            Body ground_body = new StaticBody(this,ground_shape);
            SolidFixture ground_fixture = new SolidFixture(ground_body,ground_shape);
            ground_body.setPosition(new Vec2(390,0));           
            ground_fixture.setFriction(20);
        }
        {//polygons
            Shape shape1 = new PolygonShape(0,0, 20,0, 10,10);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(20,0));
            
            Shape shape2 = new PolygonShape(0,0, 10,0, 10,20, 0,20, 0,0);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(50,0));
            
            
        }
        {//floors
            Shape shape1 = new BoxShape(15,0.5f);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(85,30));
            Shape shape2 = new BoxShape(10,0.5f);
            Body body2 = new StaticBody(this,shape1);
            body2.setPosition(new Vec2(120,20));
            for(int x=0; x<3; x++){
                Shape shape = new BoxShape(5,0.5f);
                Body body = new StaticBody(this,shape);
                body.setPosition(new Vec2(210,10+x*10));
            }
            Body body3 = new StaticBody(this,shape2);
            body3.setPosition(new Vec2(350,12));
            body3.setAngleDegrees(10);
            Body body4 = new StaticBody(this,shape2);
            body4.setPosition(new Vec2(380,20));
            body4.setAngleDegrees(-10);
            Body body5 = new StaticBody(this,shape2);
            body5.setPosition(new Vec2(410,12));
            body5.setAngleDegrees(10);
            
            Body body7 = new StaticBody(this,shape1);
            body7.setPosition(new Vec2(500,24));
            body7.setAngleDegrees(45);
            
                
            
            Shape shape3 = new BoxShape(3,0.5f);
            Body body8 = new StaticBody(this,shape3);
            body8.setPosition(new Vec2(550,10));
            Body body9 = new StaticBody(this,shape3);
            body9.setPosition(new Vec2(560,15));
            Body body10 = new StaticBody(this,shape3);
            body10.setPosition(new Vec2(570,20));
            Body body11 = new StaticBody(this,shape3);
            body11.setPosition(new Vec2(580,25));
            Body body12 = new StaticBody(this,shape3);
            body12.setPosition(new Vec2(590,30));
            
            Body body13 = new StaticBody(this,shape1);
            body13.setPosition(new Vec2(620,40));
            body13.setAngleDegrees(-5);
            Shape shape4 = new BoxShape(20,0.5f);
            Body body14 = new StaticBody(this,shape4);
            body14.setPosition(new Vec2(675,45));
            Body body15 = new StaticBody(this,shape3);
            body15.setPosition(new Vec2(750,20));
            Body body16 = new StaticBody(this,shape3);
            body16.setPosition(new Vec2(740,10));
            Body body17 = new StaticBody(this,shape3);
            body17.setPosition(new Vec2(770,30));
        }
        {//obstacles
            Shape shape1 = new BoxShape(10,15);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(232,15));
            for(int x=0; x<4; x++){
                Shape shape2 = new BoxShape(1.5f,1.5f);
                Body body2 = new DynamicBody(this,shape2);
                body2.setPosition(new Vec2(487.5f,1.5f+x*3));
                Fixture f = new SolidFixture(body2,shape2,2);
            }
            Shape shape2 = new BoxShape(1,5);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(550,5));
            Shape shape3 = new BoxShape(1,10);
            Body body3 = new StaticBody(this,shape3);
            body3.setPosition(new Vec2(570,10));
            Shape shape4 = new BoxShape(1,15);
            Body body4 = new StaticBody(this,shape4);
            body4.setPosition(new Vec2(590,15));
            Shape shape5 = new BoxShape(0.5f,40);
            Body body5 = new StaticBody(this,shape5);
            body5.setPosition(new Vec2(790.5f,39));
        }
        {
            Shape shape1 = new BoxShape(20,0.5f);
            Body body1 = new StaticBody(this,shape1);
            body1.setPosition(new Vec2(545,45)); 
            Shape shape2 = new BoxShape(0.5f,10);
            Body body2 = new StaticBody(this,shape2);
            body2.setPosition(new Vec2(564.5f,55));
            Shape shape3 = new BoxShape(5,0.5f);
            Body body3 = new StaticBody(this,shape3);
            body3.setPosition(new Vec2(552,55));
            Body body4 = new StaticBody(this,shape2);
            body4.setPosition(new Vec2(547.5f,65));
            Body body5 = new StaticBody(this,shape1);
            body5.setPosition(new Vec2(580,65));
        }
        { //pyramid
            for(int y=0; y<20;y++){
                for(int x=0;x<20-y;x++){
                    Shape shape = new BoxShape(0.5f,0.5f);
                    Body box = new DynamicBody(this, shape);
                    box.setPosition(new Vec2(280+(x)+(y*0.5f),0.75f+(y)));
                    Fixture fix1 = new SolidFixture(box,shape,10);
                }
            }
            
        }
        {//stars
            for(int i=0; i<35;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(10+15*i,50));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            } 
            for(int i=0; i<5;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(550+10*i,35));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            }
            for(int i=0; i<14;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(561+3*i,70));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            }
            for(int i=0; i<12;i++){
                starPoint = new Star(this,1);
                starPoint.setPosition(new Vec2(605+10*i,50));
                starPoint.addCollisionListener(new Pickup(super.getActor()));
            }
           
        }
        {//jumps
            for(int i=0;i<15;i++){
                jumpPoint = new Jump(this);
                jumpPoint.setPosition(new Vec2(100+40*i,40));
                jumpPoint.addCollisionListener(new Pickup(super.getActor()));
            }
            for(int i=0;i<3;i++){
                jumpPoint = new Jump(this);
                jumpPoint.setPosition(new Vec2(530+10*i,47));
                jumpPoint.addCollisionListener(new Pickup(super.getActor()));
            }
        }
        {//boosts
            for(int i=0;i<10;i++){
                boostPoint = new Boost(this);
                boostPoint.setPosition(new Vec2(50+70*i,40));
                boostPoint.addCollisionListener(new Pickup(super.getActor()));
            }
            for(int i=0;i<3;i++){
                boostPoint = new Boost(this);
                boostPoint.setPosition(new Vec2(535+10*i,47));
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
        return new Vec2(770, 37);
    }

    @Override
    public int boostCount() {
        return 15;
    }

    @Override
    public int jumpCount() {
        return 10;
    }

}
