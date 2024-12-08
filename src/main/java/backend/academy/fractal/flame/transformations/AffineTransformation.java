package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rgb;
import java.security.SecureRandom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AffineTransformation {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Integer MAX_RGB_VALUE = 255;
    private static final Double BORDERS = 1.5;

    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private Rgb color;

    public AffineTransformation getAffineTransformation() {
        generateRandomCoefficients();
        generateRandomColors();
        return this;
    }

    public Point applyTransformation(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;
        return new Point(newX, newY);
    }

    private void generateRandomCoefficients() {
        do {
            a = getRandomValue(BORDERS);
            b = getRandomValue(BORDERS);
            c = getRandomValue(BORDERS + 2);
            d = getRandomValue(BORDERS);
            e = getRandomValue(BORDERS);
            f = getRandomValue(BORDERS + 2);
        } while (!isValidTransformation());
    }

    private void generateRandomColors() {
        color = new Rgb(RANDOM.nextInt(MAX_RGB_VALUE + 1),
            RANDOM.nextInt(MAX_RGB_VALUE + 1),
            RANDOM.nextInt(MAX_RGB_VALUE + 1));
    }

    private boolean isValidTransformation() {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1)
               && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
               && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2) < 1 + Math.pow(a * e - b * d, 2));
    }

    private double getRandomValue(double boundaries) {
        return boundaries * RANDOM.nextDouble() * Math.pow(-1, RANDOM.nextInt(0, 2));
    }
}
