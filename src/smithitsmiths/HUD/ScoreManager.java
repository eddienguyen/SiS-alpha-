package smithitsmiths.HUD;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Hammer;
import smithitsmiths.players.Player;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScoreManager extends GameObject {
    public String scoreString;
    public String highScoreString;

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
        highScoreCount = 50;
        increasing = true;
        scoreString = "Meters: 0000";
        hammer = player.hammer;
        woodHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_wood.png");
        rockHammer = SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_rock.png");
        ironHammer =  SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_iron.png");;
        goldHammer =  SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_gold.png");;
        diamondHammer =  SpriteUtils.loadImage("assets/images/hammer/GUI_hammer_diamond.png");

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/iCielSoupofJustice.ttf")));
        } catch (IOException |FontFormatException e) {
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

        if (scoreCount >= highScoreCount) {
            highScoreCount = scoreCount;
        }
        scoreString = "Meters: " + Math.round(scoreCount * MapSpawner.getCurrentSpeed());
        highScoreString = "Highest: " + Math.round(highScoreCount);
        return 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(customFont);
        g2d.drawString(scoreString, 200, 700);
        g2d.fillOval(50, 650, 80, 80);
        int currentHammerDamage = (int) Hammer.getCurrentHammerDamage();
        switch (currentHammerDamage) {
            case Hammer.WOODDAMAGE:
                g2d.drawImage(woodHammer,50,650,null);
                break;
            case Hammer.ROCKDAMAGE:
                g2d.drawImage(rockHammer,50,650,null);
                break;
            case Hammer.IRONDAMAGE:
                g2d.drawImage(ironHammer,50,650,null);
                break;
            case Hammer.GOLDDAMAGE:
                g2d.drawImage(goldHammer,50,650,null);
                break;
            case Hammer.DIAMONDDAMAGE:
                g2d.drawImage(diamondHammer,50,650,null);
                break;
        }
    }
}
