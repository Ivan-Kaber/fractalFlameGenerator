package backend.academy.fractal.flame.transformations;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TransformationName {
    DISK("Disk"), POLAR("Polar"), SINUS("Sinus"), HEART("Heart"), SPHERE("Sphere");

    private final String name;
    private final Integer id;

    TransformationName(String name) {
        this.id = ordinal() + 1;
        this.name = name;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%d) %s", c.id, c.name))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static TransformationName getById(int id) {
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
