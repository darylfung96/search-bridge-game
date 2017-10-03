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

    }

    public void getNextStates() {
        while(states.isEmpty()) {
            State currentState = states.pop();
            if(visitedStates.contains(currentState)) continue;

            // if we haven't visited this state, we want to get all the next states


        }

    }


}
