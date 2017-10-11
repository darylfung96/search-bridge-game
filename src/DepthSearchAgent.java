import java.util.*;

/*
* Depth Search Agent
* */
public class DepthSearchAgent implements SearchAgent{

    final static boolean IS_LEFT = true; // torch location at left side starting
    Stack<State> states;
    private LinkedList<State> visitedStates;
    int statesSearched;
    int maxTime;
    private State achievedGoalState;

    public DepthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new Stack<State>();
        visitedStates = new LinkedList<>();

        states.add(new State(people, null, IS_LEFT, 0, 0));

        statesSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        System.out.println("DFS:");
        State state = states.pop();
        if(getNextStates(state, state.getDepth())) {
            for ( String action : state.getActions()) {
                System.out.println(action);
            }
            System.out.println("Solved!");
            System.out.println("Number of states searched: " + statesSearched);
        }

        System.out.println("Finished with Depth-first Search.");
    }

    public boolean getNextStates(State state, int depth) {
        visitedStates.add(state);
        for (State nextState: state.getNextAvailableStates()) {
            if (isVisited(nextState)) {continue;}

            statesSearched++;

            // this will return goal if found, or else expand the next state
            if (nextState.isGoal()) {
                if (nextState.getTimeTaken() > maxTime) {
                    for ( String action : state.getActions()) {
                        System.out.println(action);
                    }
                    System.out.println("Unsolved! More than minimum accepted time.");
                    System.out.println("Number of states searched: " + statesSearched);
                    continue;
                }
                achievedGoalState = nextState;
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
