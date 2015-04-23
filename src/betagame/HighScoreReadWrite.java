package betagame;
/*
this highscore handler writes and reads to/from an external file
*/

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScoreReadWrite {

    private String fileName;
    private ArrayList<ScoreLine> highscoreList;

    /**
     * Initialise a new HighScoreReadWrite
     * @param fileName the name of the high-score file
     */
    public HighScoreReadWrite(String fileName) {
        this.fileName = fileName;
    }

    /**
     * append a new high score entry to the end of the high score file (create
     * a new file if it doesn't exist).
     * @param name the user's name
     * @param score the score
     * @throws IOException 
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
     /**
     * Read the high-score data from the high-score file and
     * create ScoreLine objects of name and score pairs
     * add the ScoreLines to a list
     * 
     */
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        highscoreList = new ArrayList<ScoreLine>();
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                ScoreLine scoreline = new ScoreLine(name,score);
                
                highscoreList.add(scoreline);
                
                line = reader.readLine();
            }
            
            sortHighScores();
            
            
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }    
         finally {
            
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        
    }
    /*
    a method to sort the list of highscores in descending order
    */
    public void sortHighScores(){
        if(!highscoreList.isEmpty()){
            Collections.sort(highscoreList, new Comparator<ScoreLine>() {

            @Override
            public int compare(ScoreLine t, ScoreLine t1) {
                int score1 = t.getScore();
                int score2 = t1.getScore();
                return score2-score1;
          
            }
        });
            
        }
    }

    public ArrayList<ScoreLine> getHighscoreList() {
        return highscoreList;
    }


}
//create ScoreLines of name and score pairs
class ScoreLine{
    private String name;
    private int score;
    
    public ScoreLine(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public String getStringScore(){
        return Integer.toString(score);
    }
    @Override
    public String toString() {
        return name + "," + score;
    }
    
}