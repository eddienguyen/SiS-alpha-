import bases.GameObject;
import bases.inputs.InputManager;
import bases.maps.Map;
import bases.settings.Settings;
import smithitsmiths.Player;
import smithitsmiths.enemy.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends Frame{
    private BufferedImage greyBackground;
    private BufferedImage backBufferedImage;
    private Graphics2D backGraphics;
    private long lastTimeUpdate;
    private long currentTime;

    InputManager inputManager = InputManager.instance;

    public GameWindow(){
        pack();                                     //?
        setupGameLoop();
        setupWindow();
        setupLevel();

    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;

    }

    private void setupWindow() {
        this.setSize(Settings.instance.getWindowWidth(),Settings.instance.getWindowHeight());

        this.setTitle("Smith-it Smith");
        this.setVisible(true);

        this.backBufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backGraphics = (Graphics2D) this.backBufferedImage.getGraphics();

        this.greyBackground = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D backgroundGraphics = (Graphics2D) this.greyBackground.getGraphics();
        backgroundGraphics.setColor(Color.DARK_GRAY);
        backgroundGraphics.fillRect(0,0,this.getWidth(),this.getHeight());
        backgroundGraphics.drawString("test",10,10);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}                 //unused method

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
        //Add player
        Player player = new Player();
        player.getPosition().set(100,50);
        Enemy enemy = new Enemy();
        enemy.getPosition().set(600,50);
        GameObject.add(player);
        GameObject.add(enemy);

        Map map = Map.load("assets/maps/map_layer1.json");

        map.generate();
        GameObject.add(map);
        map.move();

    }

    public void loop(){
        while (true){
            if (lastTimeUpdate == -1){
                lastTimeUpdate = System.nanoTime();
            }

            currentTime = System.nanoTime();

            if (currentTime - lastTimeUpdate > 17000000){              //60FPS
                run();
                render();
                //changeSceneIfNeeded
                lastTimeUpdate = currentTime;

            }
        }
    }

    private void run(){
        //runAll gameObjects
        GameObject.runAll();

        //runAllActions gameObjects
        GameObject.runAllActions();
    }

    private void render() {
        backGraphics.drawImage(greyBackground,0, 0, null);
        GameObject.renderAll(backGraphics);
        getGraphics().drawImage(backBufferedImage,0,0,null);
    }

}
