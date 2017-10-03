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

    public State(LinkedList<Integer> leftPeople, LinkedList<Integer> rightPeople,
                 boolean isLeft, int timeTaken) {
        leftSide = leftPeople;
        rightSide = rightPeople;
        this.isLeft = isLeft;
        this.timeTaken = timeTaken;

    }

    /*
    *
        getNextAvailableStates return all the people that are able to move
        which means that the torch location is the same location as them
    * */
    public LinkedList<State> getNextAvailableStates() {
        LinkedList<Integer> currentSide;
        LinkedList<Integer> otherSide;
        if(isLeft) {
            currentSide = new LinkedList<>(leftSide);
            otherSide = new LinkedList<>(rightSide);
        } else {
            currentSide = new LinkedList<>(rightSide);
            otherSide = new LinkedList<>(leftSide);
        }

        for (int index=0; index< currentSide.size(); index++) {
            LinkedList<Integer> newCurrentSide = new LinkedList<>(currentSide);
            LinkedList<Integer> newOtherSide = new LinkedList<>(otherSide);
            int firstPeopleMoved = newCurrentSide.remove(index);
            newOtherSide.add(firstPeopleMoved);
            State state = (isLeft) ? new State(newCurrentSide, newOtherSide, !isLeft,timeTaken+firstPeopleMoved) :
                    new State(newOtherSide, newCurrentSide, !isLeft, timeTaken+firstPeopleMoved);

            //TODO add second people to the next side of the bridge
            for (int second=0; second < newCurrentSide.size(); second++) {

            }

        }

    }

    public LinkedList<Integer> getLeftSide() { return leftSide; }
    public LinkedList<Integer> getRightSide() { return rightSide; }
    public boolean isGoal() { return (leftSide == null || leftSide.size() == 0); }
    public boolean isLeft() { return isLeft; }
    public int getTimeTaken() { return timeTaken; }

    public void printInfo() {
        System.out.println("left side:");
        for (int person : leftSide) System.out.print(Integer.toString(person) + " ");
        System.out.println("\nright side:");
        for(int person : rightSide) System.out.print(Integer.toString(person) + " ");
        System.out.println("\ntotal crossing time: " + Integer.toString(timeTaken));
    }

}
