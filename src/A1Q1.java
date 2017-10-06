import java.io.IOException;
import java.util.LinkedList;


// algorithm to calculate total actions
// 2^n + 2 = total actions

/*
* We assume that everyone starts from the left side of the bridge
* */

public class A1Q1 {

    public static void main(String[] args) {
        LinkedList<Integer> people = new LinkedList<>();
        int maxTime = Integer.parseInt(args[0]);
        for (int index=1; index<args.length; index++) {
            Integer newPerson = Integer.parseInt(args[index]);
            people.add(newPerson);
        }

        SearchAgent agent = new BreadthSearchAgent(people, maxTime);
        agent.run();
        pressKeyContinue();

        agent = new DepthSearchAgent(people, maxTime);
        agent.run();
        pressKeyContinue();

        agent = new IterativeDeepAgent(people, maxTime);
        agent.run();
    }

    private static void pressKeyContinue() {
        System.out.println("<Click here, Press down key and Press Enter to continue>");
        try {
            System.in.read();
        } catch(IOException e) {
            System.out.println("IO Exception");
        }
    }


}
