package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AffineTransformationTest {

    @Test
    public void testGetAffineTransformation() {
        AffineTransformation transformation = new AffineTransformation().getAffineTransformation();

        assertNotNull(transformation);
        assertNotNull(transformation.color());
        assertNotEquals(0, transformation.a(), 0.01);
        assertNotEquals(0, transformation.b(), 0.01);
        assertNotEquals(0, transformation.c(), 0.01);
        assertNotEquals(0, transformation.d(), 0.01);
        assertNotEquals(0, transformation.e(), 0.01);
        assertNotEquals(0, transformation.f(), 0.01);
    }

    @Test
    public void testApplyTransformation() {
        AffineTransformation transformation = new AffineTransformation();
        transformation.a(1);
        transformation.b(2);
        transformation.c(3);
        transformation.d(4);
        transformation.e(5);
        transformation.f(6);
        Point point = new Point(1, 1);

        Point transformedPoint = transformation.applyTransformation(point);

        assertEquals(6, transformedPoint.x(), 0.01);
        assertEquals(15, transformedPoint.y(), 0.01);
    }
}
