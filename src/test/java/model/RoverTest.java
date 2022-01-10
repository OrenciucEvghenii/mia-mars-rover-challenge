package model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    void setNegativeXForRoverPosition_illegalArgumentExceptionExpected() {
        assertThrows(RuntimeException.class, () -> new Rover().setX(-1));
    }

    @Test
    void setNegativeYForRoverPosition_illegalArgumentExceptionExpected() {
        assertThrows(RuntimeException.class, () -> new Rover().setY(-1));
    }

    @Test
    void createZeroPositionRover_validRoverObjectExpected() {
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        assertThat(rover.getX(), equalTo(0));
        assertThat(rover.getY(), equalTo(0));
    }

    @Test
    void createPositivePositionRover_validRoverObjectExpected() {
        Rover rover = new Rover();
        rover.setX(1);
        rover.setY(2);
        assertThat(rover.getX(), equalTo(1));
        assertThat(rover.getY(), equalTo(2));
    }
}