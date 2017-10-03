import java.util.LinkedList;


// algorithm to calculate total actions
// 2^n + 2 = total actions

/*
* We assume that everyone starts from the left side of the bridge
* */

public class A1Q1 {

    public static void main(String[] args) {
        LinkedList<Integer> people = new LinkedList<>();
        for (String arg : args) {
            Integer newPerson = Integer.parseInt(arg);
            people.add(newPerson);
        }

        SearchAgent agent = new BreadthSearchAgent(people, 28);
        agent.run();
    }


}
