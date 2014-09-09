package mx.jtails.homelike.util;

import android.graphics.Color;

/**
 * Created by GrzegorzFeathers on 9/5/14.
 */
public class HomelikeUtils {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static int colorWithAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
