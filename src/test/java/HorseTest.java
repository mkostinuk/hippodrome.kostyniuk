import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


class HorseTest {

    @Test
    void test_is_null_first() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 33, 27));
    }

    @Test
    void test_is_null_first_message() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 67, 11), " Name cannot be null");

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void test_is_blank_first(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 15, 66));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void test_is_blank_first_message(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 15, 66), "Name cannot be blank.");

    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -10, -100})
    void test_is_minus_second(int speed) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Cucumber", speed, 67));
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -30, -500})
    void test_is_minus_second_message(int speed) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Adam", speed, 67), "Speed cannot be negative.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -10, -100})
    void test_is_minus_third(int distance) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Kirill", 50, distance));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, -50, -600})
    void test_is_minus_third_message(int distance) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("Kirill", 50, distance), "Distance cannot be negative.");
    }

    @Test
    void test_getName() {
        Horse horse = new Horse("Kontik", 32, 66);
        String expected = "Kontik";
        String actual = horse.getName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_getSpeed() {
        Horse horse = new Horse("Scramble", 82, 11);
        int expected = 82;
        double actual = horse.getSpeed();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_getDistance_withPar() {
        Horse horse = new Horse("Misha", 22, 10);
        int expected = 10;
        double actual = horse.getDistance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_getDistance_withoutPar() {
        Horse horse = new Horse("Stas", 22);
        double expected = 0;
        double actual = horse.getDistance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_move_par() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Angola", 3, 22);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.6, 0.3, 0, 2, 0.88})
    void test_move_rand(double rand) {

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("bebe", 22, 11);
            mockedStatic.when((() -> Horse.getRandomDouble(0.2, 0.9))).thenReturn(rand);
            horse.move();
            double expected = 11 + 22 * Horse.getRandomDouble(0.2, 0.9);
            double actual = horse.getDistance();
            Assertions.assertEquals(expected, actual);
        }
    }

}