package backend.academy.fractal.flame.interaction;

import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleLogicTest {

    private ConsoleLogic consoleLogic;
    private PrintStream mockOut;
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        mockOut = mock(PrintStream.class);
        mockScanner = mock(Scanner.class);

        consoleLogic = new ConsoleLogic(mockOut, mockScanner);
    }

    @Test
    public void testSelectAffineCoefficients_valid() {
        when(mockScanner.nextLine()).thenReturn("5");

        consoleLogic.selectAffineCoefficients("5");

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectSize_validInput() {
        when(mockScanner.nextLine()).thenReturn("300");

        consoleLogic.selectSize("300", true);

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectGeneratorType_validInput() {
        when(mockScanner.nextLine()).thenReturn("1");

        consoleLogic.selectGeneratorType("1");

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectSamples_validInput() {
        when(mockScanner.nextLine()).thenReturn("10");

        consoleLogic.selectSamples("10");

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectIterPerSample_validInput() {
        when(mockScanner.nextLine()).thenReturn("15");

        consoleLogic.selectIterPerSample("15");

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectSymmetry_validInput() {
        when(mockScanner.nextLine()).thenReturn("3");

        consoleLogic.selectSymmetry("3");

        verify(mockOut, never()).print(anyString());
    }

    @Test
    public void testSelectGamma_validInput() {
        when(mockScanner.nextLine()).thenReturn("2.5");

        consoleLogic.selectGamma("2.5");

        verify(mockOut, never()).print(anyString());
    }
}
