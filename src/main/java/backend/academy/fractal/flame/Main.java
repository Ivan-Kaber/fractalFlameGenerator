package backend.academy.fractal.flame;

import backend.academy.fractal.flame.interaction.ConsoleInterface;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Main {
    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface consoleInterface = new ConsoleInterface(scanner, out);
        consoleInterface.processUserInteraction();
    }
}
