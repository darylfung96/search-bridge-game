import java.util.*;

public class DepthSearchAgent implements SearchAgent{

    private final static boolean IS_LEFT = true; // torch location at left side starting
    private Stack<State> states;
    private Set<State> visitedStates;
    private int statesSearched;
    private int maxTime;

    public DepthSearchAgent(LinkedList<Integer> people, int maxTime) {
        states = new Stack<State>();
        visitedStates = new HashSet<State>();

        states.add(new State(people, null, IS_LEFT, 0));
        statesSearched = 0;
        this.maxTime = maxTime;
    }


    @Override
    public void run() {
        State state = states.pop();
        visitedStates.add(state);
        getNextStates(state);

        System.out.println("Finished with Depth-first Search");
    }

    public void getNextStates(State state) {
        for (State nextState: state.getNextAvailableStates()) {
            if (visitedStates.contains(nextState)) continue;

            visitedStates.add(nextState);
            nextState.printInfo();
            statesSearched++;
            System.out.println("Number states searched: " + Integer.toString(statesSearched));
            if(nextState.isGoal()) {
                break;
            }
            getNextStates(nextState);
        }

        System.out.println("Depth-first Search finished. No solution found.");

    }


}
