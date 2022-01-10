package model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlateauTest {
    @Test
    void setNegativeXForPlateauSize_illegalArgumentExceptionExpected() {
        assertThrows(IllegalArgumentException.class, () -> new Plateau(-1, 0));
    }

    @Test
    void setNegativeYForPlateauSize_illegalArgumentExceptionExpected() {
        assertThrows(IllegalArgumentException.class, () -> new Plateau(0, -1));
    }

    @Test
    void createZeroSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau();
        assertThat(plateau.getX(), equalTo(0));
        assertThat(plateau.getY(), equalTo(0));
    }

    @Test
    void createPositiveSizePlateau_validPlateauObjectExpected() {
        Plateau plateau = new Plateau(1,2);
        assertThat(plateau.getX(), equalTo(1));
        assertThat(plateau.getY(), equalTo(2));
    }

    @Test
    void addRoverOutsideOfPlateau_outOfPlateauBoundExceptionExpected() {
        assertThrows(OutOfPlateauBoundException.class, () -> {
            Plateau plateau = new Plateau();
            plateau.addRover(new Rover(1, 1, Orientation.NORTH));
        });

        assertThrows(OutOfPlateauBoundException.class, () -> {
            Plateau plateau = new Plateau();
            plateau.addRover(new Rover(-1, -1, Orientation.NORTH));
        });

        assertThrows(OutOfPlateauBoundException.class, () -> {
            Plateau plateau = new Plateau(2, 3);
            plateau.addRover(new Rover(3, 4, Orientation.NORTH));
        });
    }

    @Test
    void addTwoRoversOnSamePlateauPosition_plateauPositionOccupiedExceptionExpected() {
        assertThrows(PlateauPositionOccupiedException.class, () -> {
            Plateau plateau = new Plateau();
            plateau.addRover(new Rover());
            plateau.addRover(new Rover());
        });

        assertThrows(PlateauPositionOccupiedException.class, () -> {
            Plateau plateau = new Plateau(3,3);
            plateau.addRover(new Rover(1, 1, Orientation.NORTH));
            plateau.addRover(new Rover(1, 1, Orientation.WEST));
        });
    }

    @Test
    void addTwoRoversOnDifferentPositions_expectedAddedRoversAmountEqualsTwo() {
        Plateau plateau = new Plateau(3,3);

        try {
            plateau.addRover(new Rover(1, 1, Orientation.NORTH));
            plateau.addRover(new Rover(1, 2, Orientation.WEST));
            plateau.addRover(new Rover(2, 1, Orientation.EAST));
        } catch (OutOfPlateauBoundException | PlateauPositionOccupiedException e) {
            e.printStackTrace();
        }

        assertThat(plateau.getRoversAmount(), equalTo(3));
    }
}