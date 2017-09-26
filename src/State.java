import java.util.HashMap;
import java.util.LinkedList;

/*
*   State: Stores all the information of the current state everything is in.
*   for example:    If there are 4 people in the game.
*                   It will store these 4 people and their location in the game,
*                   either left or right side of the bridge.
*
* */
public class State {

    private HashMap<String, LinkedList<Person>> people;
    private String torchLocation;

    public State(String torchLocation) {
        people = new HashMap<>();
        this.torchLocation = torchLocation;
    }

    public State(String location, LinkedList<Person> people) {
        for (Person person : people) {
            this.people.computeIfAbsent(location, newPerson -> new LinkedList<Person>())
                    .add(person);
        }
    }

    /*
    *
        getAvailableActions return all the people that are able to move
        which means that the torch location is the same location as them
    * */
    public LinkedList<Person> getAvailableActions() {
        return people.get(torchLocation);
    }



    public void addPerson(String location, Person person) {
        people.computeIfAbsent(location, newPerson -> new LinkedList<Person>())
        .add(person);
    }


    public boolean isGoal() {
        return !people.containsKey(BreadthSearchAgent.INITIAL_TORCH_LOCATION);
    }

    public String getTorchLocation() { return torchLocation; }

}
