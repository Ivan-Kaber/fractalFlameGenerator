package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import backend.academy.fractal.flame.transformations.Transformation;
import java.security.SecureRandom;

public class SingleThreadFractalRenderer extends FractalRenderer {
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    void renderSamples(
        int samples, int iterPerSamples, int countOfSymmetric,
        Transformation[] transformations, AffineTransformation[] affineTransformation
    ) {
        for (int i = 0; i < samples; i++) {
            render(iterPerSamples, countOfSymmetric, affineTransformation, transformations);
        }
    }

    @Override
    Point applyAffineTransformation(
        Transformation[] transformations, Point point,
        AffineTransformation affineTransformation
    ) {
        point = affineTransformation.applyTransformation(point);
        point = transformations[RANDOM.nextInt(0, transformations.length)].apply(point);
        return point;
    }

    @Override
    int getRandomAffineIndex(AffineTransformation[] affineTransformation) {
        return RANDOM.nextInt(0, affineTransformation.length);
    }

    @Override
    Point getRandomPoint() {
        double x = RANDOM.nextDouble(world.x(), world.width());
        double y = RANDOM.nextDouble(world.y(), world.height());
        return new Point(x, y);
    }
}
