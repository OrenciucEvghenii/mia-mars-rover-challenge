package model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Rover {
    private int x = 0;
    private int y = 0;
    private Orientation orientation = Orientation.NORTH;
    private List<NavigationMove> navigationSequence;

    public Rover(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void rotateLeft() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.WEST;
            case WEST -> orientation = Orientation.SOUTH;
            case SOUTH -> orientation = Orientation.EAST;
            case EAST -> orientation = Orientation.NORTH;
        }
    }

    public void rotateRight() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.EAST;
            case WEST -> orientation = Orientation.NORTH;
            case SOUTH -> orientation = Orientation.WEST;
            case EAST -> orientation = Orientation.SOUTH;
        }
    }

    // Potentially dangerous method. Could lead to inconsistency with plateau data.
    public void move() {
        switch (orientation) {
            case NORTH -> y++;
            case WEST -> x--;
            case SOUTH -> y--;
            case EAST -> x++;
        }
    }

    public boolean hasSamePosition(Rover rover) {
        return rover.x == x && rover.y == y;
    }

    public void setNavigationSequence(List<NavigationMove> navigationSequence) {
        this.navigationSequence = navigationSequence;
    }
}
