package model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RoverTest {
    @Test
    void createRoverWithNoArgumentsAndRotateLeft_expectedRoverOrientationWest() {
        Rover rover = new Rover();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.WEST));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateRight_expectedRoverOrientationEast() {
        Rover rover = new Rover();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.EAST));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateRightFourTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover();
        rover.rotateRight();
        rover.rotateRight();
        rover.rotateRight();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.NORTH));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateLeftFourTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.NORTH));
    }

    @Test
    void createRoverWithWestOrientationAndRotateLeftTwoTimes_expectedRoverOrientationEast() {
        Rover rover = new Rover(0, 0, Orientation.WEST);
        rover.rotateLeft();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.EAST));
    }

    @Test
    void createRoverWithSouthOrientationAndRotateRightTwoTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover(0, 0, Orientation.SOUTH);
        rover.rotateRight();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.NORTH));
    }

    @Test
    void createRoverWithNoArgumentsMoveForward_expectedIncrementedYOrientationNorth() {
        Rover rover = new Rover();
        int y = rover.getY();
        rover.move();
        assertThat(rover.getY(), equalTo(y+1));
    }

    @Test
    void createRoverWithNoArgumentsRotateRightMoveForward_expectedIncrementedXOrientationEast() {
        Rover rover = new Rover();
        int x = rover.getX();
        rover.rotateRight();
        rover.move();
        assertThat(rover.getX(), equalTo(x+1));
        assertThat(rover.getOrientation(), equalTo(Orientation.EAST));
    }

    @Test
    void createRoverWithNoArgumentsRotateLeftMoveForward_expectedDecrementedXOrientationWest() {
        Rover rover = new Rover();
        int x = rover.getX();
        rover.rotateLeft();
        rover.move();
        assertThat(rover.getX(), equalTo(x-1));
        assertThat(rover.getOrientation(), equalTo(Orientation.WEST));
    }

    @Test
    void createRoverWithNoArgumentsRotate180MoveForward_expectedDecrementedYOrientationSouth() {
        Rover rover = new Rover();
        int y = rover.getY();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.move();
        assertThat(rover.getY(), equalTo(y-1));
        assertThat(rover.getOrientation(), equalTo(Orientation.SOUTH));
    }
}