package smithitsmiths.HUD;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.scenes.SceneManager;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Hammer;
import smithitsmiths.players.Player;
import smithitsmiths.scenes.GameOverScene;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ScoreManager extends GameObject {
    public String scoreString;
    public String highScoreString = "";

    public float scoreCount;
    public float highScoreCount;

    public float pointsPerSecond;

    public Hammer hammer;

    public boolean increasing;
    FrameCounter frameCounter = new FrameCounter(24);

    BufferedImage woodHammer;
    BufferedImage rockHammer;
    BufferedImage ironHammer;
    BufferedImage goldHammer;
    BufferedImage diamondHammer;
    Font customFont;

    public ScoreManager(Player player) {
        scoreCount = 0;
        pointsPerSecond = 1;
        increasing = true;
        scoreString = "Meters: 0000";
        highScoreCount = this.getHighScore();
        highScoreString = "Highest: " + highScoreCount;
        hammer = player.hammer;
        woodHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_wood.png");
        rockHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_rock.png");
        ironHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_iron.png");
        ;
        goldHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_gold.png");
        ;
        diamondHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_diamond.png");

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")));
        } catch (IOException | FontFormatException e) {
            //Handle exception
            e.printStackTrace();
        }
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (frameCounter.run()) {
            if (increasing) {
                scoreCount += pointsPerSecond;
            }
            frameCounter.reset();
        }
        checkHighScore();

        scoreString = "Meters: " + Math.round(scoreCount * MapSpawner.getCurrentSpeed());
        return 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(customFont);
        g2d.drawString(scoreString, 200, 700);
        g2d.drawString(highScoreString, 200, 720);
        g2d.fillOval(50, 650, 80, 80);
        int currentHammerDamage = (int) Hammer.getCurrentHammerDamage();
        switch (currentHammerDamage) {
            case Hammer.WOODDAMAGE:
                g2d.drawImage(woodHammer, 50, 650, null);
                break;
            case Hammer.ROCKDAMAGE:
                g2d.drawImage(rockHammer, 50, 650, null);
                break;
            case Hammer.IRONDAMAGE:
                g2d.drawImage(ironHammer, 50, 650, null);
                break;
            case Hammer.GOLDDAMAGE:
                g2d.drawImage(goldHammer, 50, 650, null);
                break;
            case Hammer.DIAMONDDAMAGE:
                g2d.drawImage(diamondHammer, 50, 650, null);
                break;
        }
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

    public void checkHighScore() {

        if ((Math.round(scoreCount * MapSpawner.getCurrentSpeed())) >= highScoreCount) {
            highScoreCount = Math.round(scoreCount * MapSpawner.getCurrentSpeed());

            File scoreFile = new File("highScore.dat");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            FileWriter writeFile = null;
            BufferedWriter writer = null;

            try {
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(String.valueOf(highScoreCount));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
