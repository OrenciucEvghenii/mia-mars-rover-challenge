package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Plateau {
    private int x = 0;
    private int y = 0;

    public Plateau(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Plateau size can't be negative");
        }
        this.x = x;
        this.y = y;
    }
}
