package yandex.praktikum.Samokat.Tests;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class TestData {

    public static Stream<Arguments> newOrder() {
        return Stream.of(
                Arguments.of("Аполлинарий", "Дырка", "ул.Кирпичные Выемки, д.14", 1, "81234567890", "31.12.2026", "black", "С перламутровыми пуговицами"),
                Arguments.of("Иннокентий", "Забейворота", "Бабьегородский переулок, д.41", 2, "81234567890", "31.12.2026", "grey", "+5 к скорости"),
                Arguments.of("Роман", "Бутылка", "ул.Трудовой Пчелы, д.41", 3, "81234567890", "31.12.2026", "both", "Не могу определиться с цветом"),
                Arguments.of("Владилена", "Козюлькина", "Улица Жужа, д.22", 4, "81234567890", "31.12.2026", "none", "Лень писать комментарий")
        );
    }
}
