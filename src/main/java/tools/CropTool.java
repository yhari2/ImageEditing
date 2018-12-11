package tools;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
 * By: Yasasvi Hari
 *
 *
 * TODO: IMPLEMENT ME
 */
public class CropTool implements Tool {
    @Override
    public void onCreate() {
    }

    @Override
    public String getName() {
        return "CropTool";
    }

    @Override
    public String getHotKeyShortcut() {
        return "C";
    }

    @Override
    public BufferedImage onClick(BufferedImage image, int x, int y) {
        return null;
    }

    @Override
    public BufferedImage onKey(BufferedImage image, KeyEvent e) {
        return null;
    }
}
