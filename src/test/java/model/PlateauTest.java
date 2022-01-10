package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void addTwoRoversOnDifferentPositions_addedRoversAmountEqualsTwoExpected() {
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

    @Test
    void createNavigationSequenceFromStringWithInvalidCharacters_illegalArgumentExceptionExpected() {
        assertThrows(IllegalArgumentException.class, () -> NavigationMove.fromCharSequence("RLAR"));
    }

    @Test
    void createNavigationSequenceFromEmptyString_emptyNavigationSequenceExpected() {
        assertEquals(NavigationMove.fromCharSequence("").size(), 0);
    }

    @Test
    void createNavigationSequenceFromValidNavigationCharacters_validNavigationSequenceExpected() {
        String navigationCharacters = "RLRM";
        List<NavigationMove> navigationMoves = NavigationMove.fromCharSequence(navigationCharacters);
        assertEquals(navigationMoves.size(), navigationCharacters.length());
        assertEquals(navigationMoves.get(0), NavigationMove.R);
        assertEquals(navigationMoves.get(1), NavigationMove.L);
        assertEquals(navigationMoves.get(2), NavigationMove.R);
        assertEquals(navigationMoves.get(3), NavigationMove.M);
    }

    @Test
    void addRoversOnSeparateDefaultPlateausMoveInAllOrientations_illegalNavigationSequenceExceptionExpected() {
        assertThrows(IllegalNavigationSequenceException.class, () -> {
            Plateau plateau = new Plateau();
            Rover rover = new Rover();
            rover.setNavigationSequence(NavigationMove.fromCharSequence("M"));
            plateau.addRover(rover);
            plateau.navigateRovers();
        });

        assertThrows(IllegalNavigationSequenceException.class, () -> {
            Plateau plateau = new Plateau();
            Rover rover = new Rover();
            rover.setNavigationSequence(NavigationMove.fromCharSequence("RM"));
            plateau.addRover(rover);
            plateau.navigateRovers();
        });

        assertThrows(IllegalNavigationSequenceException.class, () -> {
            Plateau plateau = new Plateau();
            Rover rover = new Rover();
            rover.setNavigationSequence(NavigationMove.fromCharSequence("LM"));
            plateau.addRover(rover);
            plateau.navigateRovers();
        });

        assertThrows(IllegalNavigationSequenceException.class, () -> {
            Plateau plateau = new Plateau();
            Rover rover = new Rover();
            rover.setNavigationSequence(NavigationMove.fromCharSequence("RRM"));
            plateau.addRover(rover);
            plateau.navigateRovers();
        });
    }

    @Test
    void addTwoRoversOnPlateauAndMoveToSamePosition_roverCollisionExceptionExpected() {
        assertThrows(RoverCollisionException.class, () -> {
            Plateau plateau = new Plateau(5, 5);

            Rover rover1 = new Rover(0, 0, Orientation.NORTH);
            rover1.setNavigationSequence(NavigationMove.fromCharSequence("RMMLMM"));
            plateau.addRover(rover1);

            Rover rover2 = new Rover(0, 2, Orientation.EAST);
            rover2.setNavigationSequence(NavigationMove.fromCharSequence("MM"));
            plateau.addRover(rover2);

            plateau.navigateRovers();
        });
    }

    @Test
    void addTwoRoversOnPlateauAndNavigateThroughPlateau_successfulSurfaceScanExpected() throws OutOfPlateauBoundException, PlateauPositionOccupiedException, IllegalNavigationSequenceException, RoverCollisionException {
        Plateau plateau = new Plateau(5, 5);

        Rover rover1 = new Rover(1, 2, Orientation.NORTH);
        rover1.setNavigationSequence(NavigationMove.fromCharSequence("LMLMLMLMM"));
        plateau.addRover(rover1);

        Rover rover2 = new Rover(3, 3, Orientation.EAST);
        rover2.setNavigationSequence(NavigationMove.fromCharSequence("MMRMMRMRRM"));
        plateau.addRover(rover2);

        plateau.navigateRovers();

        assertEquals(rover1.getX(),1);
        assertEquals(rover1.getY(), 3);
        assertEquals(rover1.getOrientation(), Orientation.NORTH);

        assertEquals(rover2.getX(), 5);
        assertEquals(rover2.getY(), 1);
        assertEquals(rover2.getOrientation(), Orientation.EAST);
    }
}