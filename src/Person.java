public class Person {

    private int speed;
    private String location; // if people has crossed the bridge, it will be true

    public Person(int bSpeed) {
        speed = bSpeed;
        location = "left";
    }

    /* Setters */
    public void move() {
        if (location.equals("right"))
            location = "left";
        else
            location = "right";
    }

    /* Getters */
    public int getSpeed() { return speed; }
    public String getLocation() { return location; }
}
