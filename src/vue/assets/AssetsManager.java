package vue.assets;

import java.net.URL;

public class AssetsManager {
    public static URL getURL (String path) {
        return AssetsManager.class.getResource(path);
    }
}
