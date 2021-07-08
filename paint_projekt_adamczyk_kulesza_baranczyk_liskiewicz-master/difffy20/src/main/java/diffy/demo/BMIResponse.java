package diffy.demo;

public class BMIResponse {
    public double bmi;
    public String status;

    public BMIResponse(double bmi, String status) {
        this.bmi = Math.round(bmi*100.0)/100.0;
        this.status = status;
    }
}
