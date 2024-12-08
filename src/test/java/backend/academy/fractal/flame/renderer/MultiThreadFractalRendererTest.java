package backend.academy.fractal.flame.renderer;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.transformations.AffineTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadFractalRendererTest {

    @Test
    public void testRenderSamples() {
        MultiThreadFractalRenderer renderer = new MultiThreadFractalRenderer();
        FractalImage image = new FractalImage(10, 10);

        renderer.renderSamples(10, 10, 10, null, null);

        assertNotNull(image.data());
    }

    @Test
    public void testGetRandomAffineIndex() {
        MultiThreadFractalRenderer renderer = new MultiThreadFractalRenderer();
        AffineTransformation[] affineTransformations = new AffineTransformation[10];

        int randomAffineIndex = renderer.getRandomAffineIndex(affineTransformations);

        assertTrue(randomAffineIndex >= 0 && randomAffineIndex < affineTransformations.length);
    }

    @Test
    public void testGetRandomPoint() {
        MultiThreadFractalRenderer renderer = new MultiThreadFractalRenderer();

        Point randomPoint = renderer.getRandomPoint();

        assertNotNull(randomPoint);
    }
}
