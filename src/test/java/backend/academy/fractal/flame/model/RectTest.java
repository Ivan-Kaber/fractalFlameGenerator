package backend.academy.fractal.flame.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.*;

class RectTest {

    private static final int RECT_X = 0;
    private static final int RECT_Y = 0;
    private static final int RECT_WIDTH = 10;
    private static final int RECT_HEIGHT = 10;

    @ParameterizedTest
    @CsvSource({
        "5, 5, true",  // Точка внутри прямоугольника
        "0, 5, true",  // Точка на левой границе
        "10, 5, true", // Точка на правой границе
        "5, 0, true",  // Точка на верхней границе
        "5, 10, true", // Точка на нижней границе
        "-1, 5, false", // Точка вне прямоугольника (слева)
        "11, 5, false", // Точка вне прямоугольника (справа)
        "5, -1, false", // Точка вне прямоугольника (сверху)
        "5, 11, false"  // Точка вне прямоугольника (снизу)
    })
    public void testContainsWithVariousPoints(int pointX, int pointY, boolean expectedResult) {
        Rect rectangle = new Rect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
        Point point = new Point(pointX, pointY);
        assertEquals(expectedResult, rectangle.contains(point),
            String.format("Expected %s for point (%d, %d) in rectangle (%d, %d, %d, %d)", expectedResult, pointX, pointY, RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT));
    }
}
