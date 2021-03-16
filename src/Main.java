import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                System.out.println(unpackingString(reader.readLine()));
            } catch (IllegalArgumentException e) {
                System.out.println("Строка не валидна");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String unpackingString(String str) {
        Pattern searchLine = Pattern.compile("(\\d+)\\[([a-zA-Z]+)\\]");
        Pattern valid = Pattern.compile("^[a-zA-Z0-9\\[\\]]+$");
        Matcher matcher;

        if (!valid.matcher(str).find()) {
            throw new IllegalArgumentException();
        }
        while ((matcher = searchLine.matcher(str)).find()) {
            str = matcher.replaceAll(m -> m.group(2).repeat(Integer.parseInt(m.group(1))));
        }
        return str;
    }
}

/*
        1. searchString - Шаблон для поиска подстроки/
        2. valid - шаблон для проверки валидности строки.
                (Не идеальный. Провереят только допустимые символы,
                но не их последовательность.)
        3. Оператор if проверяет полученную методом строку на валидность.
        4. В цикле присваеваем матчеру строку (str) и ищем подстроки по шаблону searchLine.
        При нахождении перезаписываем найденную часть на содержимое group(2) group(1) раз.
        Полученную строку перезаписываем в матчер и продолжаем поиск.
 */