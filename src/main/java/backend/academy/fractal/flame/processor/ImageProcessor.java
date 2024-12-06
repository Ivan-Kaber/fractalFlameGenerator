package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Point;
import java.util.function.Function;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage fractalImage);
}
