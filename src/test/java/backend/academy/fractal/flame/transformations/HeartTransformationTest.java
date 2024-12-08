package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeartTransformationTest {

    @Test
    public void testApplyWithPositiveCoordinates() {
        HeartTransformation transformation = new HeartTransformation();
        Point inputPoint = new Point(1, 1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sqrt(Math.pow(1, 2) + Math.pow(1, 2)) *
                           Math.sin(Math.sqrt(Math.pow(1, 2) + Math.pow(1, 2)) * Math.atan(1.0 / 1.0));
        double expectedY = -Math.sqrt(Math.pow(1, 2) + Math.pow(1, 2)) *
                           Math.cos(Math.sqrt(Math.pow(1, 2) + Math.pow(1, 2)) * Math.atan(1.0 / 1.0));

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithNegativeCoordinates() {
        HeartTransformation transformation = new HeartTransformation();
        Point inputPoint = new Point(-1, -1); // Пример с точкой (-1, -1)

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sqrt(Math.pow(-1, 2) + Math.pow(-1, 2)) *
                           Math.sin(Math.sqrt(Math.pow(-1, 2) + Math.pow(-1, 2)) * Math.atan(-1.0 / -1.0));
        double expectedY = -Math.sqrt(Math.pow(-1, 2) + Math.pow(-1, 2)) *
                           Math.cos(Math.sqrt(Math.pow(-1, 2) + Math.pow(-1, 2)) * Math.atan(-1.0 / -1.0));

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithLargeCoordinates() {
        HeartTransformation transformation = new HeartTransformation();
        Point inputPoint = new Point(1000, 1000); // Точка с большими координатами

        Point result = transformation.apply(inputPoint);

        double sqrt = Math.sqrt(Math.pow(1000, 2) + Math.pow(1000, 2));
        double expectedX = sqrt * Math.sin(sqrt * Math.atan(1000.0 / 1000.0));
        double expectedY = -sqrt * Math.cos(sqrt * Math.atan(1000.0 / 1000.0));

        assertEquals(expectedX, result.x(), 1e-6); // Погрешность 1e-6 для вещественных чисел
        assertEquals(expectedY, result.y(), 1e-6); // Погрешность 1e-6 для вещественных чисел
    }
}
