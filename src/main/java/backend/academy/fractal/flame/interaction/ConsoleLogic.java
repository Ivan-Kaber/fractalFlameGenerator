package backend.academy.fractal.flame.interaction;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.processor.LogGammaCorrection;
import backend.academy.fractal.flame.renderer.FractalRenderer;
import backend.academy.fractal.flame.renderer.MultiThreadFractalRenderer;
import backend.academy.fractal.flame.renderer.SingleThreadFractalRenderer;
import backend.academy.fractal.flame.save.ImageFormat;
import backend.academy.fractal.flame.save.ImageSaver;
import backend.academy.fractal.flame.transformations.Transformation;
import backend.academy.fractal.flame.transformations.TransformationFactory;
import backend.academy.fractal.flame.transformations.TransformationName;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

public class ConsoleLogic {
    private final PrintStream out;
    private final Scanner scanner;
    private Integer widthSize;
    private Integer heightSize;
    private FractalRenderer fractalRenderer;
    private Integer countOfAffineCoefficients;
    private Integer samples;
    private Integer iterPerSample;
    private Integer symmetry;
    private Double gamma;
    private final List<Transformation> transformations = new ArrayList<>();
    private ImageFormat imageFormat;
    private boolean isNeedTimer = false;

    public ConsoleLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public void selectSize(String size, boolean isWidth) {
        if (StringUtils.isNumeric(size) && Integer.parseInt(size) >= 0) {
            if (isWidth) {
                widthSize = Integer.parseInt(size);
            } else {
                heightSize = Integer.parseInt(size);
            }
        } else {
            out.print("""
                \nYou entered incorrect data, please try again, \
                enter an image dimension value in the range from 0 to 5000:\s""");
            selectSize(scanner.nextLine(), isWidth);
        }
    }

    public void selectGeneratorType(String number) {
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) >= 1 && Integer.parseInt(number) <= 2) {
            if (Integer.parseInt(number) == 1) {
                fractalRenderer = new SingleThreadFractalRenderer();
            } else {
                fractalRenderer = new MultiThreadFractalRenderer();
            }
        } else {
            out.print("""
                \nYou entered incorrect data, please try again, \
                enter a number from 1 to 2:\s""");
            selectGeneratorType(scanner.nextLine());
        }
    }

    public void selectAffineCoefficients(String number) {
        countOfAffineCoefficients = validateParameters(number);
    }

    public void selectSamples(String number) {
        samples = validateParameters(number);
    }

    public void selectIterPerSample(String number) {
        iterPerSample = validateParameters(number);
    }

    public void selectSymmetry(String number) {
        symmetry = validateParameters(number);
    }

    public void selectGamma(String number) {
        if (isParsableAsDouble(number) && Double.parseDouble(number) > 0) {
            gamma = Double.parseDouble(number);
        } else {
            out.print("""
                \nYou entered incorrect data, please try again:\s""");
            selectGamma(scanner.nextLine());
        }
    }

    public void selectTransformations(String number) {
        String[] transformationNameList = number.split(" ");
        for (String string : transformationNameList) {
            if (TransformationName.contains(string)) {
                addTransformation(string);
            } else {
                out.print("""
                    \nYou entered incorrect data, please try again:\s""");
                selectTransformations(scanner.nextLine());
            }
        }
    }

    public void selectImageFormat(String number) {
        if (ImageFormat.contains(number)) {
            imageFormat = ImageFormat.getById(Integer.parseInt(number));
        } else {
            out.print("""
                \nYou entered incorrect data, please try again:\s""");
            selectImageFormat(scanner.nextLine());
        }
    }

    public void generateImage() {
        if (isNeedTimer) {
            long timer = System.currentTimeMillis();
            FractalImage image = new FractalImage(widthSize, heightSize);
            fractalRenderer.generate(
                image,
                samples,
                iterPerSample,
                symmetry,
                countOfAffineCoefficients,
                transformations
            );
            new LogGammaCorrection().process(image, gamma);
            ImageSaver.save(
                image,
                imageFormat
            );
            System.out.println("Time: " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timer) + " sec");
        }
    }

    public void isNeedTimer(String answer) {
        if ("Y".equalsIgnoreCase(answer)) {
            isNeedTimer = true;
        } else if ("N".equalsIgnoreCase(answer)) {
            isNeedTimer = false;
        } else {
            out.print("""
                \nYou entered incorrect data, please try again:\s""");
            isNeedTimer(scanner.nextLine());
        }
    }

    private Integer validateParameters(String number) {
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) > 0) {
            return Integer.parseInt(number);
        } else {
            out.print("""
                \nYou entered incorrect data, please try again:\s""");
            validateParameters(scanner.nextLine());
        }
        return 0;
    }

    private void addTransformation(String id) {
        transformations.add(
            TransformationFactory.createTransformation(TransformationName.getById(Integer.parseInt(id))));
    }

    private static boolean isParsableAsDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

}
