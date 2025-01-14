package backend.academy.fractal.flame.processor;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Pixel;
import backend.academy.fractal.flame.model.Point;
import backend.academy.fractal.flame.model.Rgb;
import backend.academy.fractal.flame.processor.LogGammaCorrection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogGammaCorrectionTest {

    @Test
    public void testProcess_withZeroHitCount() {
        FractalImage image = new FractalImage(1, 1);
        Pixel pixel = new Pixel(new Point(0, 0), new Rgb(0, 0, 0), 0);
        image.data()[0][0] = pixel;
        LogGammaCorrection logGammaCorrection = new LogGammaCorrection();

        logGammaCorrection.process(image, 1.0);

        assertEquals(0, pixel.rgb().red());
        assertEquals(0, pixel.rgb().green());
        assertEquals(0, pixel.rgb().blue());
    }

    @Test
    public void testProcess_withNonZeroHitCount() {
        FractalImage image = new FractalImage(1, 1);
        Pixel pixel = new Pixel(new Point(100, 100), new Rgb(100, 100, 100), 100);
        image.data()[0][0] = pixel;
        LogGammaCorrection logGammaCorrection = new LogGammaCorrection();

        logGammaCorrection.process(image, 1.0);

        assertNotEquals(0, pixel.rgb().red());
        assertNotEquals(0, pixel.rgb().green());
        assertNotEquals(0, pixel.rgb().blue());
    }

    @Test
    public void testProcess_withMaxRgbValue() {
        FractalImage image = new FractalImage(1, 1);
        Pixel pixel = new Pixel(new Point(255, 255), new Rgb(255, 255, 255), 255);
        image.data()[0][0] = pixel;
        LogGammaCorrection logGammaCorrection = new LogGammaCorrection();

        logGammaCorrection.process(image, 1.0);

        assertEquals(255, pixel.rgb().red());
        assertEquals(255, pixel.rgb().green());
        assertEquals(255, pixel.rgb().blue());
    }
}
