package smithitsmiths.maps;

import bases.GameObject;
import smithitsmiths.Platform;

public interface Map {
    final int WIDTH = 1500;
    public void init();
    public void deinit();
    public Platform getLastPlatform();
    public void setEachPlatformSpeed(float eachPlatformSpeed);
}
