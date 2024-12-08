package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.*;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import backend.academy.fractal.flame.transformations.Transformation;

public abstract class FractalRenderer {
    private static final Integer MISSED_ITERATIONS = -20;
    private FractalImage fractalImage;
    static final Rect world = new Rect(-1.0, -1.0, 1.0, 1.0);

    public void generate(
        FractalImage image, int samples, int iterPerSamples, int countOfSymmetric,
        int countOfAffineCoefficients, Transformation... transformations
    ) {
        this.fractalImage = image;
        AffineTransformation[] affineTransformation = initializeAffineTransformations(countOfAffineCoefficients);

        renderSamples(samples, iterPerSamples, countOfSymmetric, transformations, affineTransformation);
    }

    void render(
        int iterPerSamples, int countOfSymmetric,
        AffineTransformation[] affineTransformation, Transformation[] transformations
    ) {
        Point point = getRandomPoint();

        for (int i = MISSED_ITERATIONS; i < iterPerSamples; i++) {
            int randomAffineIndex = getRandomAffineIndex(affineTransformation);
            point = applyAffineTransformation(transformations, point, affineTransformation[randomAffineIndex]);

            makeNonlinearTransformation(i, countOfSymmetric, point, affineTransformation, randomAffineIndex);
        }
    }

    private static AffineTransformation[] initializeAffineTransformations(int countOfAffineCoefficients) {
        AffineTransformation[] affineTransformation = new AffineTransformation[countOfAffineCoefficients];
        for (int i = 0; i < affineTransformation.length; i++) {
            affineTransformation[i] = new AffineTransformation().getAffineTransformation();
        }
        return affineTransformation;
    }

    private void makeNonlinearTransformation(
        int i, int countOfSymmetric, Point point,
        AffineTransformation[] affineTransformation, int randomAffineIndex
    ) {
        if (i > 0) {
            double theta2 = 0;
            for (int j = 0; j < countOfSymmetric; theta2 += (Math.PI * 2 / countOfSymmetric), j++) {
                point = rotate(point, theta2);

                if (!world.contains(point)) {
                    continue;
                }

                int width = fractalImage.width();
                int height = fractalImage.height();
                int x1 = width - (int) ((world.width() - point.x()) / (2 * world.width()) * width);
                int y1 = height - (int) ((world.height() - point.y()) / (2 * world.height()) * height);
                Point point1 = new Point(x1, y1);

                if (point1.x() < width && point1.y() < height) {
                    Pixel pixel = setPixelColor(affineTransformation, point1, randomAffineIndex);
                    pixel.increaseHitCount();
                }
            }
        }
    }

    private Pixel setPixelColor(
        AffineTransformation[] affineTransformations,
        Point point1, int randomAffineTransformation
    ) {
        Pixel pixel = fractalImage.data()[(int) point1.x()][(int) point1.y()];
        Rgb rgb;
        if (pixel.hitCount() == 0) {
            rgb = affineTransformations[randomAffineTransformation].color();
        } else {
            rgb = pixel.rgb()
                .sum(affineTransformations[randomAffineTransformation].color())
                .divideByTwo();
        }
        pixel.rgb(rgb);
        return pixel;
    }

    private Point rotate(Point point, double theta) {
        double xRotated = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double yRotated = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }

    abstract void renderSamples(
        int samples, int iterPerSamples, int countOfSymmetric,
        Transformation[] transformations, AffineTransformation[] affineTransformation
    );

    abstract Point applyAffineTransformation(
        Transformation[] transformations, Point point,
        AffineTransformation affineTransformation
    );

    abstract int getRandomAffineIndex(AffineTransformation[] affineTransformation);

    abstract Point getRandomPoint();
}
