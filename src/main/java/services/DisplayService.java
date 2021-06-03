package services;

import javafx.scene.effect.ColorAdjust;

public class DisplayService {

    public static ColorAdjust colorAdjust = new ColorAdjust();

    public static ColorAdjust getColorAdjust() {
        return colorAdjust;
    }

    public static void setBrightness(double value) {
        if (value > 100) value = 100;
        else if (value < -100) value = -100;
        colorAdjust.setBrightness(value / 1000);
    }

    public static int getBrightness() {
        return (int) (1000 * colorAdjust.getBrightness());
    }

    public static String getSettings() {
        return  "[Display]\n" +
                "Brightness=" + getBrightness() + "\n" +
                "; =[-100,100]\n";
    }

}
