import java.util.*;

public class Person extends Creature {

    // distance from the edge to consider moving away from it
    private final int EDGE_DISTANCE = 3;

    // last known direction of the monster
    private int lastKnownDirection = -1;

    public Person(Model model, int row, int column) {
        super(model, row, column);
    }

    int decideMove() {
        // create a map of directions and their distances
        HashMap<Integer, Integer> distMap = new HashMap<>();

        boolean monsterFound = false;
        int monsterDirection = 0;
        int monsterDist = 0;
        int edgeCount = 0;

        // iterate through all directions
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; ++i) {
            distMap.put(i, this.distance(i));

            // check if monster is in the current direction
            if (this.look(i) == Model.MONSTER) {
                monsterFound = true;
                monsterDirection = i;
                monsterDist = this.distance(i);
                lastKnownDirection = monsterDirection;
            }

            // check if edge is in the current direction
            if (
                this.look(i) == Model.EDGE && this.distance(i) == EDGE_DISTANCE
            ) {
                edgeCount++;
            }
        }

        // if monster is found, move away from it
        if (monsterFound) {
            return handleMonster(distMap, monsterDirection, monsterDist);
        }
        // if edge is found, move away from it:w
        else if (edgeCount > 0) {
            return handleEdge(distMap);
        }

        // if no monster or edge is found, stay to avoid getting in line of sight of the monster
        return Model.STAY;
    }

    private int handleMonster(
        HashMap<Integer, Integer> distMap,
        int monsterDirection,
        int monsterDist
    ) {
        // check if monster is too close
        boolean panic = monsterDist < 3;

        // get opposite directions of the monster
        int[] oppositeDirections = getOppositeDirections(
            monsterDirection,
            panic
        );

        // if monster is too close, remove the direction it came from
        if (panic) {
            oppositeDirections = Arrays.stream(oppositeDirections)
                .filter(direction -> direction != lastKnownDirection)
                .toArray();
        }

        int maxDist = 0;
        int maxDirection = 0;

        // find the direction with the maximum distance from the monster
        for (int direction : oppositeDirections) {
            int currentDist = distMap.get(direction);
            if (currentDist > maxDist && look(direction) != Model.MONSTER) {
                maxDist = currentDist;
                maxDirection = direction;
            }
        }

        return maxDirection;
    }

    private int handleEdge(HashMap<Integer, Integer> distMap) {
        int maxDist = 0;
        int maxDirection = 0;

        // find the direction with the maximum distance from the edge
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; ++i) {
            int currDist = distMap.get(i);
            if (currDist > maxDist && look(i) != Model.MONSTER) {
                maxDist = currDist;
                maxDirection = i;
            }
        }

        return maxDirection;
    }

    private int[] getOppositeDirections(int direction, boolean panic) {
        switch (direction) {
            case Model.N:
                return panic
                    ? new int[] { Model.SW, Model.SE }
                    : new int[] {
                        Model.W,
                        Model.SW,
                        Model.SE,
                        Model.NW,
                        Model.NE,
                        Model.E,
                    };
            case Model.NE:
                return panic
                    ? new int[] { Model.W, Model.S }
                    : new int[] {
                        Model.NW,
                        Model.W,
                        Model.S,
                        Model.N,
                        Model.E,
                        Model.SE,
                    };
            case Model.E:
                return panic
                    ? new int[] { Model.NW, Model.SW }
                    : new int[] {
                        Model.N,
                        Model.NW,
                        Model.SW,
                        Model.NE,
                        Model.SE,
                        Model.S,
                    };
            case Model.SE:
                return panic
                    ? new int[] { Model.W, Model.N }
                    : new int[] {
                        Model.SW,
                        Model.W,
                        Model.N,
                        Model.S,
                        Model.E,
                        Model.NE,
                    };
            case Model.S:
                return panic
                    ? new int[] { Model.NW, Model.NE }
                    : new int[] {
                        Model.W,
                        Model.NW,
                        Model.NE,
                        Model.SW,
                        Model.SE,
                        Model.E,
                    };
            case Model.SW:
                return panic
                    ? new int[] { Model.N, Model.E }
                    : new int[] {
                        Model.NW,
                        Model.N,
                        Model.E,
                        Model.W,
                        Model.S,
                        Model.SE,
                    };
            case Model.W:
                return panic
                    ? new int[] { Model.SE, Model.NE }
                    : new int[] {
                        Model.N,
                        Model.NE,
                        Model.SE,
                        Model.NW,
                        Model.SW,
                        Model.S,
                    };
            case Model.NW:
                return panic
                    ? new int[] { Model.S, Model.E }
                    : new int[] {
                        Model.NE,
                        Model.E,
                        Model.S,
                        Model.N,
                        Model.W,
                        Model.SW,
                    };
        }
        return new int[] { 0 };
    }
}
