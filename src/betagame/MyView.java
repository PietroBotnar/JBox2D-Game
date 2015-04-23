package betagame;
//a view of the game
import city.cs.engine.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;



public class MyView extends UserView {
    private Image bg;
    private BallPlayer ball;
    private World world;
    private BetaGame game;

    public MyView(World world, int width, int height, BallPlayer ball, BetaGame game) {
        super(world, width, height);
        this.ball = ball;
        this.game = game;
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,100));
        if(game.isGameover()){
            g.drawString("GAME OVER", this.getWidth()/2-250,this.getHeight()/2);
        }
        
    }
     
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(bg,0,0,this);
    }
    
    public void setBackgroundImage(Image img){
        this.bg = img;
    }
    public void setBall(BallPlayer ball) {
        this.ball = ball;
    }
}
