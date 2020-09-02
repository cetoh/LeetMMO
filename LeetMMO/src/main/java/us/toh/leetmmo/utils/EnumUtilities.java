package us.toh.leetmmo.utils;

public class EnumUtilities {
    /**
     * This is a utility method to check if a given string is in specific enum usage is as such:
     * .isInEnum(string, Enum.class)
     * @param value
     * @param enumClass
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) { return true; }
        }
        return false;
    }
}
