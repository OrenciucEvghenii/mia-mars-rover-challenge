package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Rover {
    private int x = 0;
    private int y = 0;
    private Orientation orientation = Orientation.N;

    public Rover(int x, int y, Orientation orientation) {
        setX(x);
        setY(y);
        this.orientation = orientation;
    }

    // TODO: use validator instead of throwing exception manually
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Rover position can't be negative");
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Rover position can't be negative");
        }
        this.y = y;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
