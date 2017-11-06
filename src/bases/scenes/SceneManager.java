package bases.scenes;

public class SceneManager {
    static Scene currentScene;
    static Scene nextScene;

    public static void changeScene(Scene newScene) {
        //(maybe) still inside gameLoop, so: change when gameLoop is done
        nextScene = newScene;

    }

    public static void changeSceneIfNeeded() {
        //outside gameLoop
        //1. check if changeScene is needed to made
        if (nextScene != null){
            if (currentScene != null)

                currentScene.deinit();
            nextScene.init();
            currentScene = nextScene;

            //handle the changeScene happen twice:
            nextScene = null;
        }
        //2.if changeScene is needed to made => deinit - init

    }
}
