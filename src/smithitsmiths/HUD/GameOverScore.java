package smithitsmiths.HUD;

import bases.GameObject;
import smithitsmiths.players.Hammer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameOverScore extends GameObject{
    Font customFont;
    public String highScoreString;
    public GameOverScore(){

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")).deriveFont(26f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")));
        } catch (IOException | FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }
        highScoreString = String.valueOf(this.getHighScore());

    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(Color.WHITE);
        g2d.setFont(customFont);
//        g2d.drawString(scoreString, 200, 700);
        g2d.drawString(highScoreString, 530, 515);


    }

    public float getHighScore() {
        //format : score
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highScore.dat");
            reader = new BufferedReader(readFile);
            return Float.parseFloat(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
