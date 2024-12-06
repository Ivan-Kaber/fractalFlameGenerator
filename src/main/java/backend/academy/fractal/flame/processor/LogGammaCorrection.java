package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Pixel;
import backend.academy.fractal.flame.model.Rgb;

public class LogGammaCorrection implements ImageProcessor {
    private static final Double GAMMA_CONST = 2.0;
    private static final Integer MAX_RGB_VALUE = 255;

    @Override
    public void process(FractalImage fractalImage) {
        int max = 0;
        for (int row = 0; row < fractalImage.width(); row++) {
            for (int col = 0; col < fractalImage.height(); col++) {
                if (fractalImage.data()[row][col].hitCount() != 0) {
                    max = Math.max(max, fractalImage.data()[row][col].hitCount());
                }
            }
        }
        double maxLog = Math.log10(max);
        for (int row = 0; row < fractalImage.width(); row++) {
            for (int col = 0; col < fractalImage.height(); col++) {
                double gamma = Math.log10(fractalImage.data()[row][col].hitCount()) / maxLog;
                gamma += gamma * GAMMA_CONST;
                Pixel pixel = fractalImage.data()[row][col];
                Rgb rgb = new Rgb(
                    Math.min((int) (pixel.rgb().red() * gamma), MAX_RGB_VALUE),
                    Math.min((int) (pixel.rgb().green() * gamma), MAX_RGB_VALUE),
                    Math.min((int) (pixel.rgb().blue() * gamma), MAX_RGB_VALUE)
                );
                fractalImage.data()[row][col].rgb(rgb);
            }
        }
    }
}
