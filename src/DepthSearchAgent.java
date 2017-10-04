import java.util.*;

public class DepthSearchAgent implements SearchAgent{

    private final static boolean IS_LEFT = true; // torch location at left side starting
    private Stack<State> states;
    private LinkedList<State> visitedStates;
    private int statesSearched;
    private int maxTime;

    public DepthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new Stack<State>();
        visitedStates = new LinkedList<>();

        states.add(new State(people, null, IS_LEFT, 0, 0));

        statesSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        State state = states.pop();
        visitedStates.add(state);
        if(!getNextStates(state, state.getDepth()))
            System.out.println("Depth-first Search finished. No solution found.");

        System.out.println("Finished with Depth-first Search.");
    }

    public boolean getNextStates(State state, int depth) {
        for (State nextState: state.getNextAvailableStates()) {
            if (isVisited(nextState)) {continue;}

            nextState.printInfo();
            statesSearched++;
            System.out.println("Number states searched: " + Integer.toString(statesSearched));

            // this will return goal if found, or else expand the next state
            if (nextState.isGoal()) {
                if (nextState.getTimeTaken() > maxTime) {
                    System.out.println("The search failed. " +
                            "Minimum accepted time exceeded. Time was: " +
                            Integer.toString(nextState.getTimeTaken()) + ".");
                    return false;
                }
                return true;
            }

            visitedStates.add(nextState);
            if(getNextStates(nextState, nextState.getDepth())) return true;
        }
        return false;
    }


    /*
    Check if this currentState has already been visited
    * */
    private boolean isVisited(State currentState) {
        for (State state : visitedStates){
            if(currentState.equals(state)) return true;
        }
        return false;
    }


}
