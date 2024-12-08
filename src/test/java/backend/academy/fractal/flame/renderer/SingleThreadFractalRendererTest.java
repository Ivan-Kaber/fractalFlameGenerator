package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleThreadFractalRendererTest {


    @Test
    public void testGetRandomPoint() {
        SingleThreadFractalRenderer renderer = new SingleThreadFractalRenderer();

        Point randomPoint = renderer.getRandomPoint();

        assertNotNull(randomPoint);
    }

    @Test
    public void testGetRandomAffineIndex() {
        MultiThreadFractalRenderer renderer = new MultiThreadFractalRenderer();
        AffineTransformation[] affineTransformations = new AffineTransformation[10];

        int randomAffineIndex = renderer.getRandomAffineIndex(affineTransformations);

        assertTrue(randomAffineIndex >= 0 && randomAffineIndex < affineTransformations.length);
    }
}
