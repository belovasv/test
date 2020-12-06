import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task_08_Jenkins {

    int a = 2;
    int b =3;

    @Test
    void checkSum() {
        assertEquals(6,a*b,"Error");
    }

    @Test
    void checkDif() {
        assertEquals(1,b-a,"Error");
    }
}
