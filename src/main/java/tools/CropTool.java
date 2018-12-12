package tools;

import javafx.util.Pair;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
 * By: Yasasvi Hari
 *
 * This crop tool is designed to take in an image, an x-coordinate, and a y-coordinate. The tool works in two clicks.
 * The first click stores the selected pixel as the top-left pixel, and the second click represents the bottom right in
 * the box. The second click's coordinates have to be strictly greater than the first click's coordinates, and also
 * they have to be on image. Otherwise, we simply reset the value to null. When that happens, the next coordinates
 * become topLeft. Images passed in should not be null.
 */
public class CropTool implements Tool {
    private Pair<Integer, Integer> topLeft;


    /**
     *
     * Unnecessary.
     */
    @Override
    public void onCreate() {
    }

    @Override
    public String getName() {
        return "Crop Tool";
    }

    @Override
    public String getHotKeyShortcut() {
        return "C";
    }


    private boolean isInBounds(BufferedImage image, int x, int y) {
        int w = image.getWidth();
        int h = image.getHeight();
        return 0 <= x && x < w
                && 0 <= y &&  y < h;
    }


    private BufferedImage cropImage(BufferedImage image, int startX, int endX, int startY, int endY) {
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, BufferedImage.TYPE_INT_ARGB);

        for(int i = startX; i < endX; i++) {
            for(int j = startY; j < endY; j++) {
                int newImageX = i - startX;
                int newImageY = j - startY;

                result.setRGB(newImageX, newImageY, image.getRGB(i, j));
            }
        }

        return result;
    }

    /**
     *
     * @param image = the input image, should not be null
     * @param x = The x coordinate of the selection (0 <= x && y < image.size())
     * @param y = The y coordinate of the selection (0 <= y && y < image.size())
     * @return a new image that is cropped
     */
    @Override
    public BufferedImage onClick(BufferedImage image, int x, int y) {
        if(topLeft == null) {
            topLeft = new Pair<>(x, y);
            return image;
        } else {
            int topLeftX = topLeft.getKey();
            int topLeftY = topLeft.getValue();

            boolean isInBounds = isInBounds(image, x, y);
            if(topLeftX < x && topLeftY < y && isInBounds) {
                BufferedImage croppedImage = cropImage(image, topLeftX, x, topLeftY, y);
                topLeft = null;
                return croppedImage;
            } else {
                // anything else, we reset the tool
                topLeft = null;
                return image;
            }
        }
    }

    /**
     *
     * We don't have any key bindings for the CropTool. We do not change the image at all.
     *
     * @param image = non-null input image
     * @param e = KeyEvent
     * @return the same image
     */
    @Override
    public BufferedImage onKey(BufferedImage image, KeyEvent e) {
        return image;
    }
}
