import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


class HippodromeTest {
    @Test
    void test_is_null_par() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void test_is_null_par_message() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null), "Horses cannot be null.");
    }

    @Test
    void test_is_empty_par() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void test_is_empty_par_message() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()), "Horses cannot be empty.");
    }

    @Test
    void test_getHorses() {
        List<Horse> expected = new ArrayList<>(
                List.of(new Horse("Arctic", 10, 20),
                        new Horse("Joy", 20, 30),
                        new Horse("Lana", 30, 40),
                        new Horse("Boombox", 40, 50),
                        new Horse("Valentin", 50, 60),
                        new Horse("Grey", 60, 70),
                        new Horse("Sousage", 70, 80),
                        new Horse("Misha", 80, 90),
                        new Horse("Grisha", 20, 11),
                        new Horse("Eminem", 22, 15)));
        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void test_getWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(new Horse("name", 11, 21),
                new Horse("nAme", 22, 32), new Horse("nick", 33, 43)));
        String expected="nick";
        String actual=hippodrome.getWinner().getName();
        Assertions.assertEquals(expected, actual);
    }
}