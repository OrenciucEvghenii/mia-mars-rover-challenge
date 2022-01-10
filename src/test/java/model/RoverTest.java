package model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RoverTest {
    @Test
    void createRoverWithNoArgumentsAndRotateLeft_expectedRoverOrientationWest() {
        Rover rover = new Rover();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.W));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateRight_expectedRoverOrientationEast() {
        Rover rover = new Rover();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.E));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateRightFourTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover();
        rover.rotateRight();
        rover.rotateRight();
        rover.rotateRight();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.N));
    }

    @Test
    void createRoverWithNoArgumentsAndRotateLeftFourTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.N));
    }

    @Test
    void createRoverWithWestOrientationAndRotateLeftTwoTimes_expectedRoverOrientationEast() {
        Rover rover = new Rover(0, 0, Orientation.W);
        rover.rotateLeft();
        rover.rotateLeft();
        assertThat(rover.getOrientation(), equalTo(Orientation.E));
    }

    @Test
    void createRoverWithSouthOrientationAndRotateRightTwoTimes_expectedRoverOrientationNorth() {
        Rover rover = new Rover(0, 0, Orientation.S);
        rover.rotateRight();
        rover.rotateRight();
        assertThat(rover.getOrientation(), equalTo(Orientation.N));
    }

    @Test
    void createRoverWithNoArgumentsMoveForward_expectedIncrementedYOrientationNorth() {
        Rover rover = new Rover();
        int y = rover.getY();
        rover.move();
        assertThat(rover.getY(), equalTo(y+1));
        assertThat(rover.getOrientation(), equalTo(Orientation.N));
    }

    @Test
    void createRoverWithNoArgumentsRotateRightMoveForward_expectedIncrementedXOrientationEast() {
        Rover rover = new Rover();
        int x = rover.getX();
        rover.rotateRight();
        rover.move();
        assertThat(rover.getX(), equalTo(x+1));
        assertThat(rover.getOrientation(), equalTo(Orientation.E));
    }

    @Test
    void createRoverWithNoArgumentsRotateLeftMoveForward_expectedDecrementedXOrientationWest() {
        Rover rover = new Rover();
        int x = rover.getX();
        rover.rotateLeft();
        rover.move();
        assertThat(rover.getX(), equalTo(x-1));
        assertThat(rover.getOrientation(), equalTo(Orientation.W));
    }

    @Test
    void createRoverWithNoArgumentsRotate180MoveForward_expectedDecrementedYOrientationSouth() {
        Rover rover = new Rover();
        int y = rover.getY();
        rover.rotateLeft();
        rover.rotateLeft();
        rover.move();
        assertThat(rover.getY(), equalTo(y-1));
        assertThat(rover.getOrientation(), equalTo(Orientation.S));
    }
}