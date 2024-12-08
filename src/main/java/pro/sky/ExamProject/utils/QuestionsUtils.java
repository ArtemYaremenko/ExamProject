package pro.sky.ExamProject.utils;

import org.apache.commons.lang3.StringUtils;
import pro.sky.ExamProject.exception.InvalidStringException;

import java.util.Random;

public class QuestionsUtils {

    public static final Random RANDOM = new Random();

    public static void checkString(String string) {
        if (StringUtils.isBlank(string)) {
            throw new InvalidStringException("Строка пуста!");
        }
    }
}
