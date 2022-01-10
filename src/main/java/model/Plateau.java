package model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Plateau {
    private int x = 0;
    private int y = 0;
    private final List<Rover> rovers = new ArrayList<>();

    public Plateau(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Plateau size can't be negative");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Method adds rover on a plateau. Multiple rovers on same position are not allowed.
     *
     * @param roverToAdd - rover to add
     * @throws OutOfPlateauBoundException - thrown, if rover position is out of plateau bounds.
     * @throws PlateauPositionOccupiedException - thrown, if position, you are trying to add a rover on, is already occupied.
     */
    public void addRover(Rover roverToAdd) throws OutOfPlateauBoundException, PlateauPositionOccupiedException {
        if (roverToAdd.getX() < 0
                || roverToAdd.getY() < 0
                || roverToAdd.getX() > x
                || roverToAdd.getY() > y) {
            throw new OutOfPlateauBoundException("Rover can't be placed outside of plateau");
        }

        if (isPositionOccupied(roverToAdd)) {
            String message = "Plateau position " + roverToAdd.getX() + "," + roverToAdd.getY() + " is already occupied";
            throw new PlateauPositionOccupiedException(message);
        }

        rovers.add(roverToAdd);
    }

    /**
     * Method starts navigating added rovers sequentially through the plateau. Navigation sequence is read from
     * rover's navigation sequence.
     *
     * @throws IllegalNavigationSequenceException - thrown, if because of navigation sequence rover falls down from plateau
     * @throws RoverCollisionException - thrown, if navigation sequence leads to rovers' collision
     */
    public void navigateRovers() throws IllegalNavigationSequenceException, RoverCollisionException {
        for (Rover rover : rovers) {
            for (NavigationMove move : rover.getNavigationSequence()) {
                switch (move) {
                    case L -> rover.rotateLeft();
                    case R -> rover.rotateRight();
                    case M -> {
                        if (willFallDownBecauseOfNextMove(rover)) {
                            throw new IllegalNavigationSequenceException("Rover fall down from plateau");
                        }

                        if (isNextMovePositionOccupied(rover)) {
                            String message = "Rover on position " + rover.getX() + "," + rover.getY() + " can't move. Position occupied";
                            throw new RoverCollisionException(message);
                        }

                        rover.move();
                    }
                }
            }
        }
    }

    private boolean isNextMovePositionOccupied(Rover rover) {
        switch (rover.getOrientation()) {
            case N -> {
                return isPositionOccupied(new Rover(rover.getX(), rover.getY()+1, Orientation.N));
            }
            case W -> {
                return isPositionOccupied(new Rover(rover.getX()-1, rover.getY(), Orientation.N));
            }
            case S -> {
                return isPositionOccupied(new Rover(rover.getX(), rover.getY()-1, Orientation.N));
            }
            case E -> {
                return isPositionOccupied(new Rover(rover.getX()+1, rover.getY(), Orientation.N));
            }
        }

        return false;
    }

    private boolean willFallDownBecauseOfNextMove(Rover rover) {
        switch (rover.getOrientation()) {
            case N -> {
                return rover.getY() == y;
            }
            case W -> {
                return rover.getX() == 0;
            }
            case S -> {
                return rover.getY() == 0;
            }
            case E -> {
                return rover.getX() == x;
            }
        }

        return false;
    }

    private boolean isPositionOccupied(Rover roverToAdd) {
        return rovers.stream().anyMatch(rover -> rover.hasSamePosition(roverToAdd));
    }

    public int getRoversAmount() {
        return rovers.size();
    }
}
