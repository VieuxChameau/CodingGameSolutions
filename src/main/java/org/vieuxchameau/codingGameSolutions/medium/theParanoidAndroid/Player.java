package org.vieuxchameau.codingGameSolutions.medium.theParanoidAndroid;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        final Map<Integer, Integer> targetPositions = new HashMap<>(nbElevators + 1);
        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor
            targetPositions.put(elevatorFloor, elevatorPos);
        }

        targetPositions.put(exitFloor, exitPos);

        leadDroid(in, targetPositions);
    }

    private static void leadDroid(Scanner in, Map<Integer, Integer> targetPositions) {
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT
            Integer targetPosition = targetPositions.get(cloneFloor);
            if (targetPosition != null) {
                if (clonePos < targetPosition && "LEFT".equals(direction)) {
                    droidBlock();
                } else if (clonePos > targetPosition && "RIGHT".equals(direction)) {
                    droidBlock();
                } else {
                    droidWait();
                }
                targetPositions.remove(cloneFloor);
            } else {
                droidWait();
            }
        }
    }

    private static void droidWait() {
        System.out.println("WAIT");
    }

    private static void droidBlock() {
        System.out.println("BLOCK");
    }
}
