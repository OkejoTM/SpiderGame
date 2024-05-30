package Utils;

import Setting.Insect;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;

public class InsectUtils {

    public static Set<Class<? extends Insect>> getAllInsectSubclasses() {
        Reflections reflections = new Reflections("Setting");
        return reflections.getSubTypesOf(Insect.class);
    }

    public static double getProbabilityToAppear(Class<? extends Insect> insectClass) {
        try {
            Field field = insectClass.getDeclaredField("_probabilityOfAppearance");
            field.setAccessible(true);

            return (double) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getInitialHealth(Class<? extends Insect> insectClass) {
        return switch (insectClass.getSimpleName()) {
            case "Fly" -> 4;
            case "Mole" -> 2;
            case "Wasp" -> 5;
            case "GrassHopper" -> 6;
            default -> 0;
        };
    }

}
