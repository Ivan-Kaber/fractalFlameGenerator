package backend.academy.fractal.flame.save;

import backend.academy.fractal.flame.model.FractalImage;
import backend.academy.fractal.flame.model.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ImageSaver {
    private static final String SAVE_PATH = "./src/main/resources/examples";
    private final static int RED_CONFIG_VALUE = 16;
    private final static int GREEN_CONFIG_VALUE = 8;

    public static void save(FractalImage image, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (Pixel[] pixel : image.data()) {
            for (Pixel value : pixel) {
                bufferedImage.setRGB((int) value.point().x(), (int) value.point().y(), parseRGB(value));
            }
        }

        try {
            File outputFile = Path.of(SAVE_PATH,
                "Fractal-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss-MM-dd-yyyy."))
                + format).toFile();
            ImageIO.write(bufferedImage, format.name(), outputFile);
        } catch (IOException e) {
            throw new RuntimeException("Error saving image to file ", e);
        }
    }

    private static int parseRGB(Pixel pixel) {
        return (pixel.rgb().red() << RED_CONFIG_VALUE)
               | (pixel.rgb().green() << GREEN_CONFIG_VALUE)
               | pixel.rgb().blue();
    }

}
