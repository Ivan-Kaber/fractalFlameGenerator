package backend.academy.fractal.flame;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rgb;
import java.util.Random;

public class AffineTransformation {
    private static final Random RANDOM = new Random();
    private static final Integer MAX_RGB_VALUE = 255;

    private static double a;
    private static double b;
    private static double c;
    private static double d;
    private static double e;
    private static double f;
    private static Rgb color;

    private static void generateRandomCoefficients() {
        do {
            a = getRandomValue(1.5);
            b = getRandomValue(1.5);
            c = getRandomValue(3.5);
            d = getRandomValue(1.5);
            e = getRandomValue(1.5);
            f = getRandomValue(3.5);
        } while(!isValidTransformation());
    }

    private void generateRandomColors() {
        color = new Rgb(RANDOM.nextInt(MAX_RGB_VALUE + 1),
                        RANDOM.nextInt(MAX_RGB_VALUE + 1),
                        RANDOM.nextInt(MAX_RGB_VALUE + 1));
    }

    private static boolean isValidTransformation() {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1) &&
               (Math.pow(b, 2) + Math.pow(e, 2) < 1) &&
               (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow(a * e - b * d, 2));
    }

    private static double getRandomValue(double boundaries) {
        return boundaries * RANDOM.nextDouble() * Math.pow(-1, RANDOM.nextInt(0, 2));
    }

    private static Point applyTransformation(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;
        return new Point(newX, newY);
    }
}
