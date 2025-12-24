package app.factory;
import java.util.ArrayList;
import java.util.List;

public enum AnimalType {
    CAT,
    DOG,
    DUCK;

    public static List<String> NAMES = collectNames();

    private static List<String> collectNames() {
        List<String> result = new ArrayList<>();
        for(AnimalType animalType: AnimalType.values()) {
            result.add(animalType.name());
        }
        return result;
    }

    public static boolean doesNotContains(String value) {
        if(value == null){
            return true;
        }
        return !NAMES.contains(value.toUpperCase().trim());
    }

    public static AnimalType fromString(String value) {
        if(value == null){
            return null;
        }
        return AnimalType.valueOf(value.toUpperCase().trim());
    }

}
