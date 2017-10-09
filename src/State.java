import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/*
*   State: Stores all the information of the current state everything is in.
*   for example:    If there are 4 people in the game.
*                   It will store these 4 people and their location in the game,
*                   either left or right side of the bridge.
*   args:
*           leftSide: All the people on the left side of the bridge.
*           rightSide: All the people on the right side of the bridge.
*           states: All the next states available in the queue.
*           torchLocation: The current torch position.
*           timeTaken: The total time taken to reach this state.
*
*           If torch position is on the left side, only players on the left side
*           is able to take an action to move to the other side of the bridge.
*
* */
public class State {

    private LinkedList<Integer> leftSide;
    private LinkedList<Integer> rightSide;
    private boolean isLeft; // this refers to the torch, if it is left this is true
    private int timeTaken;
    private int depth;

    public State(LinkedList<Integer> leftPeople, LinkedList<Integer> rightPeople,
                 boolean isLeft, int timeTaken, int depth) {
        leftSide = leftPeople;
        rightSide = rightPeople;
        this.isLeft = isLeft;
        this.timeTaken = timeTaken;
        this.depth = depth;
    }

    /*
    *
        getNextAvailableStates return all the people that are able to move
        which means that the torch location is the same location as them

        example: If the torch location is on the left side, whoever is on the left side
                 is available to move.

        return: newStates
                The list of all the next available states.
    * */
    public LinkedList<State> getNextAvailableStates() {
        LinkedList<Integer> currentSide = new LinkedList<>();
        LinkedList<Integer> otherSide = new LinkedList<>();
        LinkedList<State> newStates = new LinkedList<>();
        if(isLeft) {
            if(leftSide != null) currentSide = new LinkedList<>(leftSide);
            if(rightSide != null) otherSide = new LinkedList<>(rightSide);
        } else {
            if (rightSide != null) currentSide = new LinkedList<>(rightSide);
            if (leftSide != null) otherSide = new LinkedList<>(leftSide);
        }

        // add one people to the other side of the bridge
        for (int index=0; index< currentSide.size(); index++) {
            LinkedList<Integer> newCurrentSide = new LinkedList<>(currentSide);
            LinkedList<Integer> newOtherSide = new LinkedList<>(otherSide);
            int firstPeopleMoved = newCurrentSide.remove(index);
            newOtherSide.add(firstPeopleMoved);
            State state = (isLeft) ? new State(newCurrentSide, newOtherSide, !isLeft,timeTaken+firstPeopleMoved, depth+1) :
                    new State(newOtherSide, newCurrentSide, !isLeft, timeTaken+firstPeopleMoved, depth+1);
            newStates.add(state);
            // add two people to the other side of the bridge
            for (int second=0; second < newCurrentSide.size(); second++) {
                LinkedList<Integer> newSecondCurrentSide = new LinkedList<>(newCurrentSide);
                LinkedList<Integer> newSecondOtherSide = new LinkedList<>(newOtherSide);
                int secondPeopleMoved = newSecondCurrentSide.remove(second);
                newSecondOtherSide.add(secondPeopleMoved);
                int slowerSpeed = (firstPeopleMoved > secondPeopleMoved) ? firstPeopleMoved : secondPeopleMoved;
                state = (isLeft) ? new State(newSecondCurrentSide, newSecondOtherSide, !isLeft, timeTaken+slowerSpeed, depth+1) :
                        new State(newSecondOtherSide, newSecondCurrentSide, !isLeft, timeTaken+slowerSpeed, depth+1);
                newStates.add(state);
            }

        }


        return newStates;

    }

    // getters
    public LinkedList<Integer> getLeftSide() { return leftSide; }
    public LinkedList<Integer> getRightSide() { return rightSide; }
    public boolean isGoal() { return (leftSide == null || leftSide.size() == 0); }
    public boolean isLeft() { return isLeft; }
    public int getTimeTaken() { return timeTaken; }
    public int getDepth() { return depth; }

    /* equal function */
    /*
    *   Equal State comparison function
    *
    *   We will define our own equal function here since for example:
    *   if
    *   left side: 1 2 3 4
    *   compare to
    *   left side: 2 1 3 4
    *
    *   They are both equal states but the built-in equal function is
    *   not able to find out that they are both equal.
    *
    *   Here the algorithm works as:
    *   We get the number of people on the side of the bridge * the speed of everyone on that side
    *
    *   example:
    *   1 2 3 4 => 2 * (1 + 2 + 3 + 4) = 20
    *   getSideInfo(String side) is a helper function that gets this information.
    *
    * */
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != getClass()) return false;

        State state = (State)obj;

        // if both side equals then we say this state is equivalent to the other state
        if(state.getSideInfo("left") == getSideInfo("left"))
            if(state.getSideInfo("right") == getSideInfo("right"))
                return true;

        return false;
    }
    private int getSideInfo(String side) {
        LinkedList<Integer> currentSide = (side.equals("left")) ? leftSide : rightSide;
        if(currentSide == null) return 0;

        int total = 0;
        final int EPSILON = 2;
        for (int index=0; index < currentSide.size(); index++) {
            total += currentSide.get(index) * Math.pow(EPSILON, index);
        }
        return total;
    }




    // print information
    public void printInfo() {
        printLeft();
        printRight();
        System.out.println("current depth: " + Integer.toString(depth));
        System.out.println("total crossing time: " + Integer.toString(timeTaken));
    }
    /*
    Private print helper functions
    * */
    private void printLeft() {
        System.out.println("left side:");
        if(leftSide != null) {
            for (int person : leftSide) System.out.print(Integer.toString(person) + " ");
            System.out.println();
        }
    }
    private void printRight() {
        System.out.println("right side:");
        if(rightSide != null) {
            for (int person : rightSide) System.out.print(Integer.toString(person) + " ");
            System.out.println();
        }
    }


}
