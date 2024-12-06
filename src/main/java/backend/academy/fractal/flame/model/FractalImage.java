package backend.academy.fractal.flame.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FractalImage {
    private Pixel[][] data;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;

        data = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new Pixel(new Point(i, j));
            }
        }
    }
}
