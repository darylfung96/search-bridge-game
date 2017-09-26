import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthSearchAgent implements SearchInterface{
    private LinkedList<Person> people;
    private String torchLocation;

    public BreadthSearchAgent(LinkedList<Person> people) {
        this.people = people;
        torchLocation = "left";
    }


    @Override
    public void run() {
    }


    public void getTotalActions() {
        for (Person person : people) {
           if (person.getLocation().equals(torchLocation)) {

           }
        }
    }

}
