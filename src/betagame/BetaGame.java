package betagame;
//author Petru Botnar
//main class of the game
import city.cs.engine.*;
import java.awt.*;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;
import org.jbox2d.common.Vec2;

public class BetaGame {
   
   private String playerName;
   private GameLevel world; 
   private MyView view;
   private MyView wideView;
   private int levelNum;
   private KeyboardController controller;
   private Container buttons;
   private Tracker tracker;
   private Tracker wideViewTracker;
   private ArrayList<GameLevel> levels;
   private ArrayList<Image> backgrounds;
   private final JFrame frame;
   private ControlPanel ctrlpanel;
   private GUI gui;
   private boolean gameover = false;

   
   public BetaGame() throws IOException{
       levels = new ArrayList();
       
       levels.add(new Level1(this));
       levels.add(new Level2(this));
       levels.add(new Level3(this));
       
       world = levels.get(levelNum);
       
       backgrounds = new ArrayList();
       backgrounds.add(new ImageIcon("data/bg1.png").getImage());
       backgrounds.add(new ImageIcon("data/bg2.jpg").getImage());
       backgrounds.add(new ImageIcon("data/bg3.jpg").getImage());
       
       view = new MyView(world,1000,700, getActor(),this);
       view.setBackgroundImage(backgrounds.get(levelNum));
     //  view.setGridResolution(1);
       wideView = new MyView(world, 1000, 100,getActor(),this);
       wideView.setZoom(3);
       wideView.setBackgroundImage(backgrounds.get(levelNum));
           
       frame = new JFrame("betaGame");
       gui = new GUI(this, frame,getActor());
       frame.add(gui);
       ctrlpanel = new ControlPanel(frame,view,world,getActor() ,this);
       buttons=ctrlpanel;
       
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationByPlatform(true);
       frame.setResizable(false);
       frame.pack();
       frame.setVisible(true);
       frame.requestFocus();
       tracker = new Tracker(view,world.getActor());
       wideViewTracker = new Tracker(wideView,world.getActor());
       world.addStepListener(wideViewTracker);
       world.addStepListener(tracker);
       controller = new KeyboardController(world.getActor());
       
       view.addMouseListener(new MouseFocus(frame));
       view.addMouseListener(new MouseControl(view));
      // view.setZoom(10);
     // JFrame debugView = new DebugViewer(world, 500, 500);
      
       world.start();
       
       world.addStepListener(new GameoverListener(this));
       
   }

    
   //method called to change level
   public void changeLevel() {
       int score = world.getActor().getStarCount();
       world.removeStepListener(tracker);
       world.removeStepListener(wideViewTracker);
       world.setPaused(true);
       
       
       if(levelNum==levels.size()){
           System.out.println("GameOver");
           try {
               gameOver();
           } catch (IOException ex) {
               Logger.getLogger(BetaGame.class.getName()).log(Level.SEVERE, null, ex);
           }
           System.exit(0);
       }
       else{
            world = levels.get(levelNum);
            
            view.setBackgroundImage(backgrounds.get(levelNum));
            wideView.setBackgroundImage(backgrounds.get(levelNum));
            
            view.setWorld(world);
            view.setBall(getActor());
            wideView.setBall(getActor());
            wideView.setWorld(world);
            wideView.setZoom(3);
            world.addStepListener(new Tracker(wideView,getActor()));
            world.addStepListener(new Tracker(view,getActor()));
            ctrlpanel.setWorld(world);
            controller.setBody(world.getActor());
            world.getActor().setStarCount(score);
            ctrlpanel.setBall(world.getActor());
            world.start();
            world.addStepListener(new GameoverListener(this));
            getActor().setBallImage(gui.getImageIndex());
            getActor().setBallPhysics(gui.getImageIndex());
       }
      
   }
   //method called when the game is over and a name input is prompt to the user
   public void gameOver() throws IOException{
       getWorld().setPaused(true);
       String name = JOptionPane.showInputDialog(frame,"What is your name?",null);
       HighScoreReadWrite hsWriter = new HighScoreReadWrite("data/highscores.txt");
       hsWriter.writeHighScore(name, getActor().getStarCount());
       System.out.println("highscore recorded");
       System.exit(0);
   }

    public GUI getGui() {
        return gui;
    }
   
   public ArrayList<GameLevel> getLevels() {
        return levels;
    }

    public KeyboardController getController() {
        return controller;
    }

   public BallPlayer getActor() {
        return world.getActor();
    }
   public World getWorld(){
       return world;
   }
   public MyView getView(){
       return view;
   }

    public void increaseLevelNum() {
        levelNum++;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
    
    public int getLevelNum() {
        return levelNum;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public MyView getWideView() {
        return wideView;
    }

    public Container getButtons() {
        return buttons;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
    public static void main(String[] args) throws IOException {
        BetaGame game = new BetaGame();
        
    }
    
}
