package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolarTransformationTest {

    @Test
    public void testApplyWithPositiveCoordinates() {
        PolarTransformation transformation = new PolarTransformation();
        Point inputPoint = new Point(1, 1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.atan(1.0 / 1.0) / Math.PI;
        double expectedY = Math.sqrt(Math.pow(1, 2) + Math.pow(1, 2)) - 1;
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithNegativeCoordinates() {
        PolarTransformation transformation = new PolarTransformation();
        Point inputPoint = new Point(-1, -1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.atan(-1.0 / -1.0) / Math.PI;
        double expectedY = Math.sqrt(Math.pow(-1, 2) + Math.pow(-1, 2)) - 1;
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithLargeCoordinates() {
        PolarTransformation transformation = new PolarTransformation();
        Point inputPoint = new Point(1000, 1000);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.atan(1000.0 / 1000.0) / Math.PI;
        double expectedY = Math.sqrt(Math.pow(1000, 2) + Math.pow(1000, 2)) - 1;
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithPositiveXNegativeY() {
        PolarTransformation transformation = new PolarTransformation();
        Point inputPoint = new Point(1, -1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.atan(-1.0 / 1.0) / Math.PI;
        double expectedY = Math.sqrt(Math.pow(1, 2) + Math.pow(-1, 2)) - 1;
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
