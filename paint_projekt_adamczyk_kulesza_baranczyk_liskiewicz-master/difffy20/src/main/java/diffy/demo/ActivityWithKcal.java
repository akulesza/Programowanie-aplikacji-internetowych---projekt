package diffy.demo;

public class ActivityWithKcal {
    public String name;
    public double t;
    public double kcal;

    public ActivityWithKcal(String name, double t, double kcal) {
        this.name = name;
        this.t = t;
        this.kcal = Math.round(kcal);
    }
}
