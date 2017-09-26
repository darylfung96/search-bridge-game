public class Person {

    private int speed;
    private String location; // if people has crossed the bridge, it will be true

    public Person(int bSpeed) {
        rightSide = false;
        location = "left";
    }

    /* Setters */
    public void moveLeft() { location = "left"; }
    public void moveRight() { location = "right"; }

    /* Getters */
    public int getSpeed() { return speed; }
    public String getLocation() { return location; }
}
