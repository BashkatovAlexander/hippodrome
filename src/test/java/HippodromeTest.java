import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void constructor_ShouldThrowException_WhenListIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenListIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ShouldReturnSameList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move_ShouldCallMoveOnEachHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(h -> verify(h).move());
    }

    @Test
    void getWinner_ShouldReturnHorseWithMaxDistance() {
        Horse h1 = new Horse("A", 2.0, 3.0);
        Horse h2 = new Horse("B", 2.0, 4.0);
        Horse h3 = new Horse("C", 2.0, 2.0);
        Hippodrome hippodrome = new Hippodrome(List.of(h1, h2, h3));
        assertEquals(h2, hippodrome.getWinner());
    }
}
