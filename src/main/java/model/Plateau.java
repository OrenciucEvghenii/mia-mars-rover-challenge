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
    private List<Rover> rovers = new ArrayList<>();

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

        boolean isPositionOccupied = rovers.stream().anyMatch(rover -> rover.hasSamePosition(roverToAdd));
        if (isPositionOccupied) {
            String message = "Plateau position " + roverToAdd.getX() + "," + roverToAdd.getY() + " is already occupied";
            throw new PlateauPositionOccupiedException(message);
        }

        rovers.add(roverToAdd);
    }

    public int getRoversAmount() {
        return rovers.size();
    }
}
