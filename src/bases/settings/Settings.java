package bases.settings;

import java.awt.*;

public class Settings {
    private int windowWidth, windowHeight;
    private int gamePlayWidth, gamePlayHeight;

    private Insets windowInsets;

    public static final Settings instance = new Settings();

    private Settings(){
        this(1024,768,384,768);
    }

    private Settings(int windowWidth, int windowHeight, int gamePlayWidth, int gamePlayHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.gamePlayWidth = gamePlayWidth;
        this.gamePlayHeight = gamePlayHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getGamePlayWidth() {
        return gamePlayWidth;
    }

    public int getGamePlayHeight() {
        return gamePlayHeight;
    }

    public Insets getWindowInsets() {
        return windowInsets;
    }

    public void setWindowInsets(Insets windowInsets) {
        this.windowInsets = windowInsets;
    }
}
