package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTransformationTest {

    @Test
    public void testApplyWithPositiveCoordinates() {
        SphereTransformation transformation = new SphereTransformation();
        Point inputPoint = new Point(1, 1);

        Point result = transformation.apply(inputPoint);

        double expectedX = 1 / (Math.pow(1, 2) + Math.pow(1, 2));
        double expectedY = 1 / (Math.pow(1, 2) + Math.pow(1, 2));
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithNegativeCoordinates() {
        SphereTransformation transformation = new SphereTransformation();
        Point inputPoint = new Point(-1, -1);

        Point result = transformation.apply(inputPoint);

        double expectedX = -1 / (Math.pow(-1, 2) + Math.pow(-1, 2));
        double expectedY = -1 / (Math.pow(-1, 2) + Math.pow(-1, 2));
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithLargeCoordinates() {
        SphereTransformation transformation = new SphereTransformation();
        Point inputPoint = new Point(1000, 1000);

        Point result = transformation.apply(inputPoint);

        double expectedX = 1000 / (Math.pow(1000, 2) + Math.pow(1000, 2));
        double expectedY = 1000 / (Math.pow(1000, 2) + Math.pow(1000, 2));
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithZeroCoordinates() {
        SphereTransformation transformation = new SphereTransformation();
        Point inputPoint = new Point(0, 0);

        Point result = transformation.apply(inputPoint);

        double expectedX = 0 / (Math.pow(0, 2) + Math.pow(0, 2));
        double expectedY = 0 / (Math.pow(0, 2) + Math.pow(0, 2));
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
