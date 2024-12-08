package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinusTransformationTest {

    @Test
    public void testApplyWithPositiveCoordinates() {
        SinusTransformation transformation = new SinusTransformation();
        Point inputPoint = new Point(1, 1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sin(1);
        double expectedY = Math.sin(1);
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithNegativeCoordinates() {
        SinusTransformation transformation = new SinusTransformation();
        Point inputPoint = new Point(-1, -1);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sin(-1);
        double expectedY = Math.sin(-1);
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithLargeCoordinates() {
        SinusTransformation transformation = new SinusTransformation();
        Point inputPoint = new Point(1000, 1000);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sin(1000);
        double expectedY = Math.sin(1000);
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }

    @Test
    public void testApplyWithZeroCoordinates() {
        SinusTransformation transformation = new SinusTransformation();
        Point inputPoint = new Point(0, 0);

        Point result = transformation.apply(inputPoint);

        double expectedX = Math.sin(0);
        double expectedY = Math.sin(0);
        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
