package com.mia;

import com.mia.model.IllegalNavigationSequenceException;
import com.mia.model.NavigationMove;
import com.mia.model.Orientation;
import com.mia.model.OutOfPlateauBoundException;
import com.mia.model.Plateau;
import com.mia.model.PlateauPositionOccupiedException;
import com.mia.model.Rover;
import com.mia.model.RoverCollisionException;

import java.util.List;
import java.util.Scanner;

public class MarsRover {
    public static void main(String[] args) throws RoverCollisionException, IllegalNavigationSequenceException, OutOfPlateauBoundException, PlateauPositionOccupiedException {
        Scanner stdin = new Scanner(System.in);
        String plateauDimension = stdin.nextLine();
        String[] plateauDimensionSplit = plateauDimension.split(" ");

        int plateauX = Integer.parseInt(plateauDimensionSplit[0]);
        int plateauY = Integer.parseInt(plateauDimensionSplit[1]);

        Plateau plateau = new Plateau(plateauX, plateauY);

        while (stdin.hasNextLine()) {
            String roverPositionInput = stdin.nextLine();
            String navigationSequenceInput = stdin.nextLine();

            String[] roverPositionSplit = roverPositionInput.split(" ");
            int x = Integer.parseInt(roverPositionSplit[0]);
            int y = Integer.parseInt(roverPositionSplit[1]);
            Orientation orientation = Orientation.valueOf(roverPositionSplit[2]);
            List<NavigationMove> navigationSequence = NavigationMove.fromCharSequence(navigationSequenceInput);

            Rover rover = new Rover(x, y, orientation);
            rover.setNavigationSequence(navigationSequence);

            plateau.addRover(rover);
        }

        plateau.navigateRovers();

        System.out.println(plateau.generateRoversPositionReport());
    }
}
