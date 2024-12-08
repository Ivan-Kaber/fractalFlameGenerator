package backend.academy.fractal.flame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RgbTest {

    @Test
    void testSum() {
        Rgb rgb1 = new Rgb(100, 150, 200);
        Rgb rgb2 = new Rgb(50, 100, 150);

        Rgb result = rgb1.sum(rgb2);

        assertEquals(150, result.red());
        assertEquals(250, result.green());
        assertEquals(350, result.blue());
    }

    @Test
    void testSumWithNegativeValues() {
        Rgb rgb1 = new Rgb(-100, 50, 200);
        Rgb rgb2 = new Rgb(50, -100, -50);

        Rgb result = rgb1.sum(rgb2);

        assertEquals(-50, result.red());
        assertEquals(-50, result.green());
        assertEquals(150, result.blue());
    }

    @Test
    void testDivideByTwo() {
        Rgb rgb = new Rgb(100, 150, 200);

        Rgb result = rgb.divideByTwo();

        assertEquals(50, result.red());
        assertEquals(75, result.green());
        assertEquals(100, result.blue());
    }

    @Test
    void testDivideByTwoWithZeroValues() {
        Rgb rgb = new Rgb(0, 0, 0);

        Rgb result = rgb.divideByTwo();

        assertEquals(0, result.red());
        assertEquals(0, result.green());
        assertEquals(0, result.blue());
    }
}
