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
*       states: All the next states available in the queue.
*       torchLocation: The current torch position.
*           - If torch position is on the left side, only players on the left side
*             is able to take an action to move to the other side of the bridge.
* */
public class BreadthSearchAgent implements SearchInterface{
    /* CONSTANT VARIABLES */
    public final static String INITIAL_TORCH_LOCATION = "left";
    public final static String LEFT_SIDE = "left";
    public final static String RIGHT_SIDE = "right";
    private Deque<State> states;

    public BreadthSearchAgent(LinkedList<Person> people) {
        states = new ArrayDeque<State>();
        states.add(new State(INITIAL_TORCH_LOCATION, people));
    }


    @Override
    public void run() {

    }

    /*
    * Get the next available states for all the states in the queue
    *
    * */
    public void getNextStates() {

        LinkedList<Person> tempPeople;

        for (State state : states) {
            processState(state);
        }
    }

    /*
        processState

        Purpose: This function process a state and generates all the available
        next states and add them to the queue.
    * */
    private void processState(State state) {
        LinkedList<Person> tempPeople = state.getAvailableActions();

        for (int first_index = 0; first_index < tempPeople.size(); first_index++) {
            LinkedList<Person> newPeople = new LinkedList<>();
            Person person = tempPeople.get(first_index);
            person.move();
            newPeople.add(person);
            for (int second_index=first_index+1; second_index < tempPeople.size(); second_index++) {
                newPeople.add(tempPeople.get(second_index));
            }
            State newState = new State(person.getLocation(), newPeople);
            states.add(newState);


            //TODO: add the double people crossing bridge
            //for ()
        }

    }

    /*

    * */
    private void addToStates() {

    }


    public static void main(String[] args) {
        Deque<Integer> testQueue = new ArrayDeque<Integer>();
        testQueue.add(1);
        testQueue.add(2);
        testQueue.add(3);
        testQueue.removeLast();
        for (Integer item : testQueue) {
            System.out.println(item);
        }

    }

}
