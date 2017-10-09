import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BeamSearchAgent implements SearchAgent {

    private State initialState;
    private boolean IS_LEFT = true; // initial starting point
    private int statesSearched;
    private int maxTime;
    private int beamSize;
    private PriorityQueue<State> beamStates;

    public BeamSearchAgent(LinkedList<Integer> people, int maxTime, int highestSpeed, int beamSize) {
        beamStates = new PriorityQueue<>(4, (State s1, State s2) -> s2.getReward()-s1.getReward()); // comparing function lambda

        initialState = new State(people, null, IS_LEFT, 0, 0, highestSpeed);
        beamStates.add(initialState);
        statesSearched=0;
        this.maxTime = maxTime;
        this.beamSize=beamSize;
    }

    @Override
    public void run() {
        if(getNextStates()) {
            System.out.println("Beam Search succeeded.");
        } else {
            System.out.println("Beam Search failed.");
        }
    }

    public boolean getNextStates() {

        while(beamStates.size() > 0) {
            LinkedList<State> states = new LinkedList<>();


            // get top few states
            int limit = (beamSize > beamStates.size()) ? beamStates.size() : beamSize;
            for (int index = 0; index < limit; index++) {
                State nextState = beamStates.poll();
                nextState.printInfo();
                statesSearched++;
                System.out.println("Number states searched: " + Integer.toString(statesSearched));

                if (nextState.isGoal()) {
                    if (nextState.getTimeTaken() > maxTime) {
                        System.out.println("The search failed. " +
                                "Minimum accepted time exceeded. Time was: " +
                                Integer.toString(nextState.getTimeTaken()) + ".");
                    } else {
                        return true;
                    }
                }
                states.add(nextState);
            }

            beamStates.clear();

            // get all the children from the next states
            for (State state : states)
                beamStates.addAll(state.getNextAvailableStates());
        }
        return false;
    }

}
