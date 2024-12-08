package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import backend.academy.fractal.flame.transformations.Transformation;
import java.security.SecureRandom;
import java.util.List;

public class SingleThreadFractalRenderer extends FractalRenderer {
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    void renderSamples(
        int samples, int iterPerSamples, int countOfSymmetric,
        List<Transformation> transformations, AffineTransformation[] affineTransformation
    ) {
        for (int i = 0; i < samples; i++) {
            render(iterPerSamples, countOfSymmetric, affineTransformation, transformations);
        }
    }

    @Override
    Point applyAffineTransformation(
        List<Transformation> transformations, Point point,
        AffineTransformation affineTransformation
    ) {
        Point newPoint = affineTransformation.applyTransformation(point);
        newPoint = transformations.get(RANDOM.nextInt(0, transformations.size())).apply(newPoint);
        return newPoint;
    }

    @Override
    int getRandomAffineIndex(AffineTransformation[] affineTransformation) {
        return RANDOM.nextInt(0, affineTransformation.length);
    }

    @Override
    Point getRandomPoint() {
        double x = RANDOM.nextDouble(WORLD.x(), WORLD.width());
        double y = RANDOM.nextDouble(WORLD.y(), WORLD.height());
        return new Point(x, y);
    }
}
