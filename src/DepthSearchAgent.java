import java.util.*;

/*
* Depth Search Agent
* */
public class DepthSearchAgent implements SearchAgent{

    final static boolean IS_LEFT = true; // torch location at left side starting
    Stack<State> states;
    int statesSearched;
    int maxTime;
    private State achievedGoalState;

    public DepthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new Stack<>();
        states.add(new State(people, null, IS_LEFT, 0, 0));
        statesSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        System.out.println("DFS:");
        State state = states.pop();
        if(getNextStates(state)) {
            for ( String action : achievedGoalState.getActions()) {
                System.out.println(action);
            }
            System.out.println("Solved!");
            System.out.println("Number of states searched: " + statesSearched);
        } else {
            for ( String action : achievedGoalState.getActions()) {
                System.out.println(action);
            }
            System.out.println("Unsolved! More than minimum accepted time.");
            System.out.println("Number of states searched: " + statesSearched);
        }

        System.out.println("Finished with Depth-first Search.");
    }

    public boolean getNextStates(State state) {
        for (State nextState: state.getNextAvailableStates()) {
            if (isVisited(nextState)) {continue;}
            statesSearched++;

            // this will return goal if found, or else expand the next state
            if (nextState.isGoal()) {
                achievedGoalState = nextState;
                if (nextState.getTimeTaken() > maxTime) {
                    continue;
                }
                return true;
            }
            if(getNextStates(nextState)) return true;
        }
        return false;
    }


    /*
    * Check if this currentState has already been visited
    *
    * Techniques:
    *               We check all the states that has been generated that reaches
    *               to this state and see if anyone of them are the same as this state.
    *               If anyone of them is the same, that means that this state has already been
    *               visited before so we return true.
    *
    * return:
    *           Return true if this state has been visited before.
    *
    * */
    private boolean isVisited(State currentState) {
        for (State state : currentState.getParents()){
            if(currentState.equals(state)) return true;
        }
        return false;
    }


}
