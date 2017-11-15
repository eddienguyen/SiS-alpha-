package smithitsmiths.HUD;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

import java.awt.*;

public class ScoreManager extends GameObject {
    public String scoreString;
    public String highScoreString;

    public float scoreCount;
    public float highScoreCount;

    public float pointsPerSecond;

    public boolean increasing;
    FrameCounter frameCounter = new FrameCounter(24);

    public ScoreManager(){
        //test
        scoreCount = 0;
        pointsPerSecond = 1   ;
        highScoreCount = 50;
        increasing = true;
        scoreString = "Meters: 0000";
    }

    @Override
    public float run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (frameCounter.run()){
            if (increasing){
                scoreCount += pointsPerSecond ;
            }
            frameCounter.reset();
        }

        if (scoreCount >= highScoreCount){
            highScoreCount = scoreCount;
        }
        scoreString = "Meters: " + Math.round(scoreCount);
        highScoreString = "Highest: " + Math.round(highScoreCount);
//        System.out.println(scoreString);
//        System.out.println(highScoreString);
        return 0;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.drawString(scoreString,100,100);
    }
}