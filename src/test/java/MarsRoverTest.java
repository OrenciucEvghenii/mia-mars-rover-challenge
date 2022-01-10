import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Plateau;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class MarsRoverTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void setNegativeXForPlateauSize_illegalArgumentExceptionExpected() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Plateau(-1, 0);
        });
    }

    @Test
    void setNegativeYForPlateauSize_illegalArgumentExceptionExpected() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Plateau(0, -1);
        });
    }

    @Test
    void createZeroSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau(0, 0);
        assertThat(plateau.getX(), equalTo(0));
        assertThat(plateau.getY(), equalTo(0));
    }

    @Test
    void createPositiveSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau(1, 2);
        assertThat(plateau.getX(), equalTo(1));
        assertThat(plateau.getY(), equalTo(2));
    }

    @AfterEach
    void tearDown() {
    }
}