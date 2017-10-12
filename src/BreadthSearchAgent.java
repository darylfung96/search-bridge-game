import sun.awt.image.ImageWatched;

import java.util.*;


/*
*   This class is a BreadhSearchAgent.
*   Purpose: Search the states from the initial state to the goal state
*            using Breadth-first Search.
*
*   args:
*       people: The total number of people in the game.
*       maxTime: The maximum accepted time for the people to cross to the other side
*                of the bridge. If this time is exceeded, the search is considered as failed.
*
*   Purpose:
*       Use a breadth-first search algorithm to search for the states and put them into
*       a queue. Breadth-first Search is a more memory consuming algorithm but it can
*       find the goal state in a better solution.
* */
public class BreadthSearchAgent implements SearchAgent{
    /* CONSTANT VARIABLES */
    public final static boolean IS_LEFT = true; // torch location starting at left side
    private Deque<State> states;
    private int stateSearched;
    int maxTime; // maximum Time allowed
    private State achievedGoalState;

    public BreadthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new ArrayDeque<State>();
        states.add(new State(people, null, IS_LEFT, 0, 0));
        stateSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        System.out.println("BFS:");
        getNextStates();
        if(achievedGoalState != null) {
            for (String action : achievedGoalState.getActions()) {
                System.out.println(action);
            }
            System.out.println("Solved!");
            System.out.println("Number state searched: " + stateSearched);
        }
        System.out.println("Finished with Breadth-first Search.");

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
        while (!states.isEmpty()) {
            State currentState = states.removeFirst();
            //currentState.printInfo();
            stateSearched++;
            states.addAll(currentState.getNextAvailableStates());
            if (isStateGoal(currentState)) return true;
        }
        return false;
    }


    private boolean isStateGoal(State state) {
        if(state.isGoal()){
            if(state.getTimeTaken() > maxTime) {
                for ( String action : state.getActions()) {
                    System.out.println(action);
                }
                System.out.println("Unsolved! More than minimum accepted time.");
                System.out.println("Number state searched: " + stateSearched);
                return false;
            } else {
                achievedGoalState = state;
                return true;
            }

        }

        // not goal
        return false;
    }


}
