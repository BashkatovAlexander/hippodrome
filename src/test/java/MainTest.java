import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {

    @Test
    @Timeout(22)
    @Disabled("Ручной запуск — проверка времени выполнения")
    void main_ShouldExecuteUnder22Seconds() throws Exception {
        Main.main(new String[]{});
    }
}