package model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum NavigationMove {
    L("L"),
    R("R"),
    M("M");

    public final String label;

    NavigationMove(String label) {
        this.label = label;
    }

    public static List<NavigationMove> fromCharSequence(String charSequence) {
        List<NavigationMove> navigationMoves = new ArrayList<>();

        if (charSequence == null || charSequence.isEmpty()) {
            return navigationMoves;
        }

        for(String label : charSequence.split("")) {
            if (!label.matches("[LRM]")) {
                throw new IllegalArgumentException("Char sequence can contain only 'L', 'R' and 'M' characters!");
            }

            navigationMoves.add(NavigationMove.valueOf(label));
        }

        return navigationMoves;
    }
}
