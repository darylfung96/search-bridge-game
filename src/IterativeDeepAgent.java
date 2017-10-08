import java.util.LinkedList;

public class IterativeDeepAgent extends DepthSearchAgent {

    private final int maxDepth = 1000;
    private State initialState;

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
        boolean hasFoundSolution = false;
        for (int currentMaxDepth = 1; currentMaxDepth < maxDepth; currentMaxDepth++) {
            statesSearched=0;
            if(getNextStates(initialState, 0, currentMaxDepth)) {
                hasFoundSolution = true;
                break;
            }

        }

        if(hasFoundSolution) {
            System.out.println("Search succeded.");
            // found a solution
        } else {
            System.out.println("No solution found.");
            // we didn't find a solution
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
            nextState.printInfo();
            ++statesSearched;
            System.out.println("Number states searched: " + Integer.toString(statesSearched));

            if(nextState.isGoal()) {
                if(nextState.getTimeTaken() > maxTime) {
                    System.out.println("found goal but accepted time failed to achieve. Time: " +
                    Integer.toString(nextState.getTimeTaken()) + ".");
                    continue;
                }
                return true;
            }
            if(getNextStates(nextState, nextState.getDepth(), currentMaxDepth)) return true;
        }
        return false;
    }
}
