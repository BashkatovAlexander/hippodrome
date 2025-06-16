import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    @Test
    void constructor_ShouldThrowException_WhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.0, 3.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructor_ShouldThrowException_WhenNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.0, 3.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1.0, 2.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_ShouldThrowException_WhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 1.0, -2.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ShouldReturnCorrectName() {
        Horse horse = new Horse("Spirit", 2.5, 1.0);
        assertEquals("Spirit", horse.getName());
    }

    @Test
    void getSpeed_ShouldReturnCorrectSpeed() {
        Horse horse = new Horse("Spirit", 2.5, 1.0);
        assertEquals(2.5, horse.getSpeed());
    }

    @Test
    void getDistance_ShouldReturnCorrectDistance() {
        Horse horse = new Horse("Spirit", 2.5, 1.5);
        assertEquals(1.5, horse.getDistance());
    }

    @Test
    void getDistance_ShouldReturnZero_WhenUsingTwoParamConstructor() {
        Horse horse = new Horse("Spirit", 2.5);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move_ShouldUseGetRandomDouble() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 2.0, 3.0);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void move_ShouldCalculateDistanceCorrectly() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("Test", 2.0, 3.0);
            horse.move();
            assertEquals(4.0, horse.getDistance());
        }
    }
}