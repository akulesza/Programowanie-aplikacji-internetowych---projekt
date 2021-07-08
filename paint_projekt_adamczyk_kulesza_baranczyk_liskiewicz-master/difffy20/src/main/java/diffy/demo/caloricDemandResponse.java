package diffy.demo;

public class caloricDemandResponse {
    public double kcal;
    public String goal;

    public caloricDemandResponse(double kcal, String goal) {
        this.kcal = Math.round(kcal);
        this.goal = goal;
    }
}
