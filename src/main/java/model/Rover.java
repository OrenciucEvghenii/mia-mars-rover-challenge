package model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Rover {
    private int x = 0;
    private int y = 0;
    private Orientation orientation = Orientation.N;
    private List<NavigationMove> navigationSequence;

    public Rover(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void rotateLeft() {
        switch (orientation) {
            case N -> orientation = Orientation.W;
            case W -> orientation = Orientation.S;
            case S -> orientation = Orientation.E;
            case E -> orientation = Orientation.N;
        }
    }

    public void rotateRight() {
        switch (orientation) {
            case N -> orientation = Orientation.E;
            case W -> orientation = Orientation.N;
            case S -> orientation = Orientation.W;
            case E -> orientation = Orientation.S;
        }
    }

    // Potentially dangerous method. Could lead to inconsistency with plateau data.
    public void move() {
        switch (orientation) {
            case N -> y++;
            case W -> x--;
            case S -> y--;
            case E -> x++;
        }
    }

    public boolean hasSamePosition(Rover rover) {
        return rover.x == x && rover.y == y;
    }

    public void setNavigationSequence(List<NavigationMove> navigationSequence) {
        this.navigationSequence = navigationSequence;
    }
}
