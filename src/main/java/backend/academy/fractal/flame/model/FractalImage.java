package backend.academy.fractal.flame.model;

import java.util.Arrays;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] pixels = new Pixel[width * height];
        Arrays.fill(pixels, new Pixel(new Rgb(0, 0, 0), 0));
        return new FractalImage(pixels, width, height);
    }

    private boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    private Pixel pixel(int x, int y) {
        return data[x + y * width];
    }
}
