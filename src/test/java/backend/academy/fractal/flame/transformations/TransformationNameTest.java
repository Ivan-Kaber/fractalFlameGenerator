package backend.academy.fractal.flame.transformations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransformationNameTest {

    @Test
    public void testGetByIdValid() {
        TransformationName result = TransformationName.getById(3);
        assertEquals(TransformationName.SINUS, result);
    }

    @Test
    public void testGetByIdInvalid() {
        TransformationName result = TransformationName.getById(6);
        assertNull(result);
    }

    @Test
    public void testContainsValid() {
        boolean result = TransformationName.contains("2");
        assertTrue(result);
    }

    @Test
    public void testContainsInvalid() {
        boolean result = TransformationName.contains("6");
        assertFalse(result);
    }
}
