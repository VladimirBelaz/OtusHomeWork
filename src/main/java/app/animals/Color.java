package app.animals;

import java.util.ArrayList;
import java.util.List;

public enum Color {

    RED("красный"),
    ORANGE("оранжевый"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    BLUE("синий"),
    INDIGO("фиолетовый"),
    VIOLET("пурпурный"),
    BLACK("черный"),
    WHITE("белый"),
    BROWN("коричневый"),
    GRAY("серый");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> NAMES = collectNames();
    public static List<String> RUSSIAN_NAMES = collectRussianNames();

    private static List<String> collectNames() {
        List<String> result = new ArrayList<>();
        for(Color color: Color.values()) {
            result.add(color.name());
        }
        return result;
    }

    private static List<String> collectRussianNames() {
        List<String> result = new ArrayList<>();
        for(Color color: Color.values()) {
            result.add(color.getValue());
        }
        return result;
    }
}
