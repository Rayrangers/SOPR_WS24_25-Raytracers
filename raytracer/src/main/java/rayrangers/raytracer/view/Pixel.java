package rayrangers.raytracer.view;

import java.awt.Color;

public class Pixel {

    private Color color;

    public Pixel(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
