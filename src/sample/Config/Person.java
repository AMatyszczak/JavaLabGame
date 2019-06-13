package sample.Config;

public class Person {

    private String name = null;
    private String points = null;

    public Person() {
    }

    public Person(String firstName, String points) {
        this.name = firstName;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
