package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.transformations.Transformation;

public interface FractalRenderer {
    void generate(
        FractalImage image,
        int samples,
        int iterPerSamples,
        int countOfSymmetric,
        int countOfAffineCoefficients,
        Transformation... transformations
    );
}
