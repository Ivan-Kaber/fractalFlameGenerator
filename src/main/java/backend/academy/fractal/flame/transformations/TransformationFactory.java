package backend.academy.fractal.flame.transformations;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TransformationFactory {
    public static Transformation createTransformation(TransformationName transformationName) {
        return switch (transformationName) {
            case DISK -> new DiskTransformation();
            case POLAR -> new PolarTransformation();
            case HEART -> new HeartTransformation();
            case SINUS -> new SinusTransformation();
            case SPHERE -> new SphereTransformation();
        };
    }
}
