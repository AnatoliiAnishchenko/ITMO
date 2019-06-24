package client;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class ColorManager {
    private static ArrayList<Color> color;

    private static final int CNT = 50;

    ColorManager() {
        color = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < CNT; i++) {
            color.add(new Color(random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)));
        }
    }

    static Color getColor(int index) {
        return color.get(index % CNT);
    }
}
