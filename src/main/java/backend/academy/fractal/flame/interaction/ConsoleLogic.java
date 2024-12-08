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
    private static final String INVALID_DATA = "\nYou entered incorrect data, please try again: ";

    public ConsoleLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public void selectSize(String size, boolean isWidth) {
        if (StringUtils.isNumeric(size) && Integer.parseInt(size) > 0) {
            if (isWidth) {
                widthSize = Integer.valueOf(size);
            } else {
                heightSize = Integer.valueOf(size);
            }
        } else {
            out.print("""
                \nYou entered incorrect data, please try again, \
                enter an image dimension value in the range from 1 to 5000:\s""");
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
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) > 0) {
            countOfAffineCoefficients = Integer.valueOf(number);
        } else {
            out.print(INVALID_DATA);
            selectAffineCoefficients(scanner.nextLine());
        }
    }

    public void selectSamples(String number) {
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) > 0) {
            samples = Integer.valueOf(number);
        } else {
            out.print(INVALID_DATA);
            selectSamples(scanner.nextLine());
        }
    }

    public void selectIterPerSample(String number) {
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) > 0) {
            iterPerSample = Integer.valueOf(number);
        } else {
            out.print(INVALID_DATA);
            selectIterPerSample(scanner.nextLine());
        }
    }

    public void selectSymmetry(String number) {
        if (StringUtils.isNumeric(number) && Integer.parseInt(number) > 0) {
            symmetry = Integer.valueOf(number);
        } else {
            out.print(INVALID_DATA);
            selectSymmetry(scanner.nextLine());
        }
    }

    public void selectGamma(String number) {
        if (isParsableAsDouble(number) && Double.parseDouble(number) > 0) {
            gamma = Double.valueOf(number);
        } else {
            out.print(INVALID_DATA);
            selectGamma(scanner.nextLine());
        }
    }

    public void selectTransformations(String number) {
        String[] transformationNameList = number.split(" ");
        for (String string : transformationNameList) {
            if (TransformationName.contains(string)) {
                addTransformation(string);
            } else {
                out.print(INVALID_DATA);
                selectTransformations(scanner.nextLine());
            }
        }
    }

    public void selectImageFormat(String number) {
        if (ImageFormat.contains(number)) {
            imageFormat = ImageFormat.getById(Integer.parseInt(number));
        } else {
            out.print(INVALID_DATA);
            selectImageFormat(scanner.nextLine());
        }
    }

    public void generateImage() {
        if (isNeedTimer) {
            long timer = System.currentTimeMillis();
            createFractalImage();
            out.println("Time: " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timer) + " sec");
        } else {
            createFractalImage();
        }
    }

    public void isNeedTimer(String answer) {
        if ("Y".equalsIgnoreCase(answer)) {
            isNeedTimer = true;
        } else if ("N".equalsIgnoreCase(answer)) {
            isNeedTimer = false;
        } else {
            out.print(INVALID_DATA);
            isNeedTimer(scanner.nextLine());
        }
    }

    private void createFractalImage() {
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
