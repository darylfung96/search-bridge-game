import sun.awt.image.ImageWatched;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


/*
*   This class is a BreadhSearchAgent.
*   Purpose: Search the states from the initial state to the goal state
*            using Breadth-first Search.
*
*   args:
*       people: The total number of people in the game.
* */
public class BreadthSearchAgent implements SearchAgent{
    /* CONSTANT VARIABLES */
    public final static boolean INITIAL_TORCH_LEFT = true;
    private Deque<State> states;
    private int stateSearched;
    int maxTime; // maximum Time allowed

    public BreadthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new ArrayDeque<State>();
        states.add(new State(people, null, INITIAL_TORCH_LEFT, 0));
        stateSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        for (int index=0; index < 1000000; index++) {
            getNextStates();
        }
    }

    /*
    * Get the next available states for all the states in the queue
    *
    * The first for loop deal with single person crossing the other side of the bridge
    *
    * The second for loop deal with both people crossing the other side of the bridge and the
    * slower person speed is considered.
    *
    * */
    public boolean getNextStates() {
        State currentState = states.removeFirst();

        LinkedList<Integer> currentSide;
        LinkedList<Integer> otherSide;
        if (currentState.isLeft()) {
            currentSide = currentState.getLeftSide();
            otherSide = currentState.getRightSide();
        } else {
            currentSide = currentState.getRightSide();
            otherSide = currentState.getLeftSide();
        }

        // add one person to the other side of the bridge
        for(int index=0; index < currentSide.size(); index++) {
            LinkedList<Integer> newCurrentSide = new LinkedList<>(currentSide);
            LinkedList<Integer> newOtherSide = (otherSide != null) ? new LinkedList<>(otherSide) : new LinkedList<>();
            // remove the people from current side and move them to the other side
            // peopleMoved is the speed of the person
            int peopleMoved = newCurrentSide.remove(index);
            newOtherSide.add(peopleMoved);

            State newState = (currentState.isLeft()) ? new State(newCurrentSide, newOtherSide, !currentState.isLeft(), currentState.getTimeTaken()+peopleMoved) :
                    new State(newOtherSide, newCurrentSide, !currentState.isLeft(), currentState.getTimeTaken()+peopleMoved);
            states.addLast(newState);
            newState.printInfo();
            System.out.println("Number state searched: " + Integer.toString(++stateSearched) + "\n");
            if(newState.isGoal())
                return true;


            // add two person to the other side of the bridge
            for (int second=index+1; second < currentSide.size()-1; second++) {
                LinkedList<Integer> newSecondOtherSide = new LinkedList<>(newOtherSide);
                LinkedList<Integer> newSecondCurrentSide = new LinkedList<>(newCurrentSide);
                int secondPeopleMoved = newSecondCurrentSide.remove(second);
                newSecondOtherSide.add(secondPeopleMoved);

                // if both people crossed, they move at the slower speed
                int slowerSpeed = (peopleMoved > secondPeopleMoved) ? peopleMoved : secondPeopleMoved;

                newState = (currentState.isLeft()) ? new State(newSecondCurrentSide, newSecondOtherSide, !currentState.isLeft(), currentState.getTimeTaken()+slowerSpeed) :
                        new State(newSecondOtherSide, newSecondCurrentSide, !currentState.isLeft(), currentState.getTimeTaken()+slowerSpeed);
                states.addLast(newState);
                newState.printInfo();
                System.out.println("Number state searched: " + Integer.toString(++stateSearched) +"\n");
                if(newState.isGoal())
                    return true;
            }
        }
        return false;

    }

    /*
        processState

        Purpose: This function process a state and generates all the available
        next states and add them to the queue.
    * */
    private void processState(State state) {


    }

    /*

    * */
    private void addToStates() {

    }

}
