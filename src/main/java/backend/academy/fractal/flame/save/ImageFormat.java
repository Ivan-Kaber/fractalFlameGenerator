package backend.academy.fractal.flame.save;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ImageFormat {
    JPEG("jpg"), BMP("bmp"), PNG("png");

    private final Integer id;
    private final String extension;

    ImageFormat(String extension) {
        this.id = ordinal() + 1;
        this.extension = extension;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%d) %s", c.id, c.extension))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static ImageFormat getById(int id) {
        return Arrays.stream(values())
            .filter(c -> c.id == id)
            .findFirst()
            .orElse(null);
    }

    public static boolean contains(String id) {
        return Arrays.stream(values())
            .anyMatch(c -> String.valueOf(c.id).equals(id));
    }
}
