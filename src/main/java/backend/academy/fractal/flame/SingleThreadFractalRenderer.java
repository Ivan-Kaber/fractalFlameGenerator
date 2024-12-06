package backend.academy.fractal.flame;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Pixel;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rect;
import backend.academy.fractal.flame.model.Rgb;
import backend.academy.fractal.flame.transformations.Transformation;
import java.util.Random;

public class SingleThreadFractalRenderer {
    private static final Rect WORLD = new Rect(-1.0, -1.0, 1.0, 1.0);
    private static final Random RANDOM = new Random();
    private static final Integer MISSED_ITERATIONS = -20;
    private FractalImage fractalImage;

    public void generate(
        FractalImage image,
        int samples,
        int iterPerSamples,
        int countOfSymmetric,
        int countOfAffineCoefficients,
        Transformation... transformations
    ) {
        this.fractalImage = image;
        AffineTransformation[] affineTransformation = new AffineTransformation[countOfAffineCoefficients];
        for (int i = 0; i < affineTransformation.length; i++) {
            affineTransformation[i] = new AffineTransformation().getAffineTransformation();
        }

        for (int i = 0; i < samples; i++) {
            render(iterPerSamples, countOfSymmetric, affineTransformation, transformations);
        }
    }

    private void render(
        int iterPerSamples,
        int countOfSymmetric,
        AffineTransformation[] affineTransformation,
        Transformation[] transformations
    ) {
        Point point = getRandomPoint();

        for (int i = MISSED_ITERATIONS; i < iterPerSamples; i++) {
            int randomAffineIndex = RANDOM.nextInt(0, affineTransformation.length);
            point = affineTransformation[randomAffineIndex].applyTransformation(point);
            point = transformations[RANDOM.nextInt(0, transformations.length)].apply(point);

            if (i > 0) {
                double theta2 = 0;
                for (int j = 0; j < countOfSymmetric; theta2 += (Math.PI * 2 / countOfSymmetric), j++) {
                    point = rotate(point, theta2);

                    if (!WORLD.contains(point)) {
                        continue;
                    }

                    int width = fractalImage.width();
                    int height = fractalImage.height();
                    int x1 = width - (int) ((WORLD.width() - point.x()) / (2 * WORLD.width()) * width);
                    int y1 = height - (int) ((WORLD.height() - point.y()) / (2 * WORLD.height()) * height);
                    Point point1 = new Point(x1, y1);

                    if (point1.x() < width && point1.y() < height) {
                        Pixel pixel = setPixelColor(affineTransformation, point1, randomAffineIndex);
                        pixel.increaseHitCount();
                    }
                }
            }
        }
    }

    private Pixel setPixelColor(
        AffineTransformation[] affineTransformations,
        Point point1,
        int randomAffineTransformation
    ) {
        Pixel pixel = fractalImage.data()[(int) point1.x()][(int) point1.y()];
        Rgb rgb;
        if (pixel.hitCount() == 0) {
            rgb = affineTransformations[randomAffineTransformation].color();
        } else {
            rgb = pixel
                .rgb()
                .sum(affineTransformations[randomAffineTransformation].color())
                .divideByTwo();
        }
        pixel.rgb(rgb);
        return pixel;
    }

    private Point getRandomPoint() {
        double x = RANDOM.nextDouble(WORLD.x(), WORLD.width());
        double y = RANDOM.nextDouble(WORLD.y(), WORLD.height());
        return new Point(x, y);
    }

    public Point rotate(Point point, double theta) {
        double xRotated = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double yRotated = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }
}
