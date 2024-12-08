package backend.academy.fractal.flame.interaction;

import backend.academy.fractal.flame.save.ImageFormat;
import backend.academy.fractal.flame.transformations.TransformationName;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface {
    private final Scanner scanner;
    private final PrintStream out;
    private final ConsoleLogic consoleLogic;

    public ConsoleInterface(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
        consoleLogic = new ConsoleLogic(out, scanner);
    }

    public void processUserInteraction() {
        selectSize();
        selectGeneratorType();
        selectGeneratorParameters();
        selectTransformations();
        selectImageFormat();
        isNeedTimer();
        waiting();
        consoleLogic.generateImage();
        successfulExit();
    }

    private void waiting() {
        out.print("\nYour image is generating...\n");
    }

    private void successfulExit() {
        out.print("\nCheck src/main/resources/examples to find your image!");
    }

    private void selectSize() {
        out.printf("""
            Welcome to Fractal Flame!%n\
            Let`s enter a data for the image! Do not use extremely large values %n\
            to avoid memory overflow and increased execution time. %n\
            Enter a width - a number in the range from 0 to 5000:\s""");
        consoleLogic.selectSize(scanner.nextLine(), true);

        out.print("\nEnter a height - a number in the range from 0 to 5000: ");
        consoleLogic.selectSize(scanner.nextLine(), false);
    }

    private void selectGeneratorType() {
        out.print("""
            \nIf you would like to use SingleThread Generator, enter 1, \
            if you would like to use MultiThread Generator, enter 2:\s""");
        consoleLogic.selectGeneratorType(scanner.nextLine());
    }

    private void selectGeneratorParameters() {
        out.print("\nAffine Coefficients(>0): ");
        consoleLogic.selectAffineCoefficients(scanner.nextLine());

        out.print("\nSamples(>0): ");
        consoleLogic.selectSamples(scanner.nextLine());

        out.print("\nIterations Per Sample(>0): ");
        consoleLogic.selectIterPerSample(scanner.nextLine());

        out.print("\nSymmetry(>0): ");
        consoleLogic.selectSymmetry(scanner.nextLine());

        out.print("\nGamma(>0.0): ");
        consoleLogic.selectGamma(scanner.nextLine());
    }

    private void selectTransformations() {
        out.printf("""
            %n%s%n\
            Enter the number or numbers of desired transformations with whitespace, %n\
            like '2 4 5':\s""", TransformationName.getOrderView());
        consoleLogic.selectTransformations(scanner.nextLine());
    }

    private void selectImageFormat() {
        out.printf("""
            %nEnter the desired save format: %n\
            %s%n\
            Enter one of the numbers:\s""", ImageFormat.getOrderView());
        consoleLogic.selectImageFormat(scanner.nextLine());
    }

    private void isNeedTimer() {
        out.print("""
            \nDo you want to measure the execution time of the program? (Y/N):\s""");
        consoleLogic.isNeedTimer(scanner.nextLine());
    }
}
