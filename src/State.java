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

    private LinkedList<Person> leftSide;
    private LinkedList<Person> rightSide;
    private String torchLocation;
    private int timeTaken;

    public State(LinkedList<Person> leftPeople, LinkedList<Person> rightPeople,
                 String torchLocation, int timeTaken) {
        leftSide = leftPeople;
        rightSide = rightPeople;
        this.torchLocation = torchLocation;
        this.timeTaken = timeTaken;
    }

    /*
    *
        getNextAvailableStates return all the people that are able to move
        which means that the torch location is the same location as them
    * */
    public LinkedList<Person> getNextAvailableStates() {
        LinkedList<Person> availablePeople = (torchLocation.equals("left")) ? leftSide : rightSide;

        for (Person person : availablePeople) {
            
        }

    }


    public boolean isGoal() {
        return leftSide == null
    }
    public String getTorchLocation() { return torchLocation; }
    public int getTimeTaken() { return timeTaken; }


}
