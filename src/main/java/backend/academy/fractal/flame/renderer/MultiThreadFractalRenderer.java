package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import backend.academy.fractal.flame.transformations.Transformation;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressFBWarnings("PREDICTABLE_RANDOM")
public class MultiThreadFractalRenderer extends FractalRenderer {
    private static final Integer THREADS_AMOUNT = 8;

    @Override
    void renderSamples(
        int samples, int iterPerSamples, int countOfSymmetric,
        List<Transformation> transformations, AffineTransformation[] affineTransformation
    ) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREADS_AMOUNT)) {
            for (int i = 0; i < samples; i++) {
                executorService.execute(
                    () -> render(iterPerSamples, countOfSymmetric, affineTransformation, transformations));
            }
        }
    }

    @Override
    Point applyAffineTransformation(
        List<Transformation> transformations, Point point,
        AffineTransformation affineTransformation
    ) {
        point = affineTransformation.applyTransformation(point);
        point = transformations.get(ThreadLocalRandom.current().nextInt(0, transformations.size())).apply(point);
        return point;
    }

    @Override
    int getRandomAffineIndex(AffineTransformation[] affineTransformation) {
        return ThreadLocalRandom.current().nextInt(0, affineTransformation.length);
    }

    @Override
    Point getRandomPoint() {
        double x = ThreadLocalRandom.current().nextDouble(world.x(), world.width());
        double y = ThreadLocalRandom.current().nextDouble(world.y(), world.height());
        return new Point(x, y);
    }
}

