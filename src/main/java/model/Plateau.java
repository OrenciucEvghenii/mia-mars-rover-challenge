package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Plateau {
    private int x = 0;
    private int y = 0;

    public Plateau(int x, int y) {
        setX(x);
        setY(y);
    }

    // TODO: use validator instead of throwing exception manually
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Plateau size can't be negative");
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Plateau size can't be negative");
        }
        this.y = y;
    }
}
