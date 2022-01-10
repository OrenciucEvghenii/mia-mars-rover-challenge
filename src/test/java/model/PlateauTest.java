package model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlateauTest {
    @Test
    void setNegativeXForPlateauSize_illegalArgumentExceptionExpected() {
        assertThrows(RuntimeException.class, () -> new Plateau().setX(-1));
    }

    @Test
    void setNegativeYForPlateauSize_illegalArgumentExceptionExpected() {
        assertThrows(RuntimeException.class, () -> new Plateau().setY(-1));
    }

    @Test
    void createZeroSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau();
        plateau.setX(0);
        plateau.setY(0);
        assertThat(plateau.getX(), equalTo(0));
        assertThat(plateau.getY(), equalTo(0));
    }

    @Test
    void createPositiveSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau();
        plateau.setX(1);
        plateau.setY(2);
        assertThat(plateau.getX(), equalTo(1));
        assertThat(plateau.getY(), equalTo(2));
    }
}