/*package backend.academy.fractal.flame.model;

import backend.academy.fractal.flame.FractalRenderer;
import backend.academy.fractal.flame.transformations.Transformation;
import lombok.Setter;

@Setter
public class FractalImage {
    private Pixel[][] histogram;
    private int width;
    private int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel[][] getHistogram() {
        return histogram;
    }
}*/

package backend.academy.fractal.flame.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;

@Getter
@Setter
public class FractalImage {
    private Pixel[][] data;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
