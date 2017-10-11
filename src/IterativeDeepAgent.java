import java.util.LinkedList;

/*
* Iterative Deepening agent
*
* */
public class IterativeDeepAgent extends DepthSearchAgent {

    private final int maxDepth = 1000;
    private State initialState;
    private State achievedGoalState;

    public IterativeDeepAgent(LinkedList<Integer> people, int maxTime) {
        super(people, maxTime);
        initialState = new State(people, null, IS_LEFT, 0, 0);
    }

    /*
    *   run:    Run the iterative Deepening agent.
    *
    *   variables:
    *       hasFoundSolution:   becomes true if a solution is found.
    *       currentMaxDepth :   The maximum depth at this instance. Increases
    *                           when no solution is found until the maximum value(1000)
    *
    * */
    @Override
    public void run() {
        System.out.println("Iterative Deepening:");
        boolean hasFoundSolution = false;
        for (int currentMaxDepth = 1; currentMaxDepth < maxDepth; currentMaxDepth++) {
            statesSearched=0;
            if(getNextStates(initialState, 0, currentMaxDepth)) {
                hasFoundSolution = true;
                break;
            }

        }

        if(hasFoundSolution) {
            // found a solution
            for (String action : achievedGoalState.getActions()) {
                System.out.println(action);
            }
            System.out.println("Solved!");
            System.out.println("Number state searched: " + statesSearched);
        } else {
            // we didn't find a solution
            if (achievedGoalState == null) {
                System.out.println("No solution found.");
            } else {
                for (String action : achievedGoalState.getActions()) {
                    System.out.println(action);
                }
                System.out.println("Unsolved! More than minimum accepted time.");
                System.out.println("Number state searched: " + statesSearched);
            }
        }

        System.out.println("Iterative deepening agent finished.");
    }

    /*
    *   getNextStates:  Find all the nextStates given the state we want to find all the children
    *                   available for its next state.
    *
    *
    *   args:
    *       state           :   The state to find all its children.
    *       depth           :   The current depth of the state.
    *       currentMaxDepth :   The current maximum value of the depth.
    *
    *   Return:
    *           true if a solution is found and the minimum accepted time is achieved.
    *           false if no solution is found.
    *
    * */
    public boolean getNextStates(State state, int depth, int currentMaxDepth) {
        if (depth > currentMaxDepth) return false;
        for (State nextState : state.getNextAvailableStates()) {
            // print information
            ++statesSearched;

            if(nextState.isGoal()) {
                achievedGoalState = nextState;
                if(nextState.getTimeTaken() > maxTime) {
                    continue;
                }
                return true;
            }
            if(getNextStates(nextState, nextState.getDepth(), currentMaxDepth)) return true;
        }
        return false;
    }
}
