import bases.GameObject;
import bases.inputs.InputManager;
import bases.maps.Map;
import bases.scenes.Scene;
import bases.scenes.SceneManager;
import bases.settings.Settings;
import smithitsmiths.maps.MapLevel1;
import smithitsmiths.maps.MapLevelN;
import smithitsmiths.maps.MapSpawner;
import smithitsmiths.players.Player;
import smithitsmiths.enemy.Enemy;
import smithitsmiths.scenes.GameOverScene;
import smithitsmiths.scenes.GamePlayScene;
import smithitsmiths.scenes.GameTutorialScene;
import tklibs.AudioUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends Frame {

    private BufferedImage backBufferedImage;
    private Graphics2D backGraphics;
    private long lastTimeUpdate;
    private long currentTime;

    InputManager inputManager = InputManager.instance;

    public GameWindow() {
        pack();                                     //?
        setupGameLoop();
        setupWindow();
        setupLevel();

    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;

    }

    private void setupWindow() {
        this.setSize(Settings.instance.getWindowWidth(), Settings.instance.getWindowHeight());

        this.setTitle("Smith-it Smith");
        this.setVisible(true);

        this.backBufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backGraphics = (Graphics2D) this.backBufferedImage.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }                 //unused method

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });

        Settings.instance.setWindowInsets(this.getInsets());
    }


    private void setupLevel() {

        //create gamePlayScene
        SceneManager.changeScene(new GameTutorialScene());

    }

    public void loop() {
        while (true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }

            currentTime = System.nanoTime();

            if (currentTime - lastTimeUpdate > 17000000) {              //60FPS
                run();
                render();
                //changeSceneIfNeeded
                lastTimeUpdate = currentTime;

            }
        }
    }

    private void run() {
        //runAll gameObjects
        GameObject.runAll();

        //runAllActions gameObjects
        GameObject.runAllActions();

        //test
        if (InputManager.spaceReleased) {
            if (SceneManager.getCurrentScene().getClass().equals(GameTutorialScene.class)) {
                SceneManager.changeScene(new GamePlayScene());
            }
        }


        //out of for (gameObjects)
        SceneManager.changeSceneIfNeeded();
    }

    private void render() {

        /*
        Can uncomment this if we want to change the whole screen to new screen (when gameover)
        when comment: we can make the gameover scene looks like WASTED (GTA style)

        getGraphics().setColor(Color.BLACK);
        getGraphics().fillRect(0,0,1024, 768);

        call repaint() and add this method:


        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            g.drawImage(backBufferedImage, 0, 0, null);
        }

         */

        getGraphics().drawImage(backBufferedImage, 0, 0, null);
        GameObject.renderAll(backGraphics);
    }


}
