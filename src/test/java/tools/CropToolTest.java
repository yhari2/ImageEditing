package tools;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * By: Yasasvi Hari
 */
public class CropToolTest {
    private BufferedImage black;
    private BufferedImage white;
    private BufferedImage columns;

    private Tool crop = new CropTool();

    private void colorImage(BufferedImage image, int startX, int endX, int startY, int endY, Color color) {
        for(int i = startX; i < endX; i++) {
            for(int j = startY; j < endY; j++) {
                image.setRGB(i, j, color.getRGB());
            }
        }
    }

    private void init() {
        black = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        white = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        columns = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

        colorImage(black, 0, 10, 0, 10, Color.BLACK);
        colorImage(white, 0, 10, 0, 10, Color.WHITE);

        colorImage(columns, 0, 5, 0, 10, Color.BLACK);
        colorImage(columns, 5, 10, 0, 10, Color.WHITE);
    }

    private BufferedImage crop(BufferedImage image, int startX, int endX, int startY, int endY) {
        crop.onClick(image, startX, startY);
        return crop.onClick(image, endX, endY);
    }

    private boolean isRightColor(BufferedImage image, Color color) {
        int colorRGB = color.getRGB();

        int width = image.getWidth();
        int height = image.getHeight();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                int imageRGB = image.getRGB(i, j);

                if(imageRGB != colorRGB) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void testCropExecute() throws Exception {
        init();

        // black
        BufferedImage result1 = crop(black, 0,5, 0,5);
        Assert.assertTrue(isRightColor(result1, Color.BLACK));
        Assert.assertTrue(result1.getHeight() == 5);
        Assert.assertTrue(result1.getWidth() == 5);

        // black
        BufferedImage result2 = crop(black, 3,7,3,7);
        Assert.assertTrue(isRightColor(result2, Color.BLACK));
        Assert.assertTrue(result2.getHeight() == 4);
        Assert.assertTrue(result2.getWidth() == 4);

        // white
        BufferedImage result3 = crop(white, 0,5, 0,5);
        Assert.assertTrue(isRightColor(result3, Color.WHITE));
        Assert.assertTrue(result3.getHeight() == 5);
        Assert.assertTrue(result3.getWidth() == 5);

        // test should fail -- Out of Bounds
        BufferedImage result4 = crop(white, 1, 5, 6, 10);
        Assert.assertTrue(isRightColor(result4, Color.WHITE));
        Assert.assertTrue(result4.getHeight() == 10);
        Assert.assertTrue(result4.getWidth() == 10);

        // test should be fine
        BufferedImage result5 = crop(white, 1, 5, 6, 8);
        Assert.assertTrue(isRightColor(result5, Color.WHITE));
        Assert.assertTrue(result5.getHeight() == 2);
        Assert.assertTrue(result5.getWidth() == 4);

        // columns
        BufferedImage leftCol = crop(columns, 0, 5, 0, 9);
        Assert.assertTrue(isRightColor(leftCol, Color.BLACK));
        Assert.assertTrue(leftCol.getHeight() == 9);
        Assert.assertTrue(leftCol.getWidth() == 5);

        BufferedImage rightCol = crop(columns, 5, 9, 0, 9);
        Assert.assertTrue(isRightColor(rightCol, Color.WHITE));
        Assert.assertTrue(rightCol.getHeight() == 9);
        Assert.assertTrue(rightCol.getWidth() == 4);
    }
}