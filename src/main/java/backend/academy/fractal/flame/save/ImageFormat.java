package backend.academy.fractal.flame.save;

public enum ImageFormat {
    JPEG("jpg"), BMP("bmp"), PNG("png");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }
}
