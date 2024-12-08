package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiskTransformationTest {

    @Test
    public void testApply() {
        DiskTransformation transformation = new DiskTransformation();
        Point point = new Point(1, 1);

        Point transformedPoint = transformation.apply(point);

        assertNotNull(transformedPoint);
        assertNotEquals(0, transformedPoint.x(), 0.01);
        assertNotEquals(0, transformedPoint.y(), 0.01);
    }


    @Test
    public void testApply_NullPoint() {
        DiskTransformation transformation = new DiskTransformation();
        Point point = null;

        assertThrows(NullPointerException.class, () -> transformation.apply(point));
    }

}
