package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage fractalImage, Double enteredGamma);
}
