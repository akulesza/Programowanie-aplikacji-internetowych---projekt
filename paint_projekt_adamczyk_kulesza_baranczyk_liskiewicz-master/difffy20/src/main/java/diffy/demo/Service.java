package diffy.demo;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public Service(){

    }

    public BMIResponse GetBMI(BMIRequest req) {
        double bmi = calculateBMI(req);
        String status = bmiStatus(bmi);
        return new BMIResponse(Math.round(bmi*100.0)/100.0, status);
    }

    private double calculateBMI(BMIRequest req) {
        return req.weight / (req.height/100) / (req.height/100) ;
    }

    private String bmiStatus(double bmi) {
        if(bmi < 19) {
            return "Oznacz to, ze masz niedowage";
        }
        else if((bmi >= 19) && (bmi < 25)) {
            return "Jest to wartosc prawidlowa";
        }
        else if((bmi >= 25) && (bmi < 30)) {
            return "Oznacz to, ze masz nadwage";
        }
        else if((bmi >= 30) && (bmi < 35)) {
            return "Oznacz to, ze masz otylosc I stopnia";
        }
        else if((bmi >= 35) && (bmi < 40)) {
            return "Oznacz to, ze masz otylosc II stopnia";
        }
        else {
            return "Oznacz to, ze masz otylosc III stopnia";
        }
    }

    public caloricDemandResponse GetCaloricDemand(caloricDemandRequest req) {
        String goal = GetGoal(req.actualWeight, req.goalWeight);

        double kcal=calculateDemand(req);

        {
            if(req.actualWeight < req.goalWeight) {
                kcal=1.15*kcal;
            }
            if (req.actualWeight == req.goalWeight) {
                kcal=kcal;
            }
            if(req.actualWeight>req.goalWeight){
                kcal=0.85*kcal;
            }
        }
        return new caloricDemandResponse(kcal, goal);
    }

    private double calculateDemand(caloricDemandRequest req) {
        //double waf;
        if(req.activityLevel==0){
            req.waf=1.4;
        }
        else if(req.activityLevel==1){
            req.waf=1.7;
        }
        else if(req.activityLevel==2){
            req.waf=2.0;
        }
        else if(req.activityLevel==3){
            req.waf=2.2;
        }

        if(req.male==true) {

            req.caloricDemand=req.waf*(66.47+13.7* req.actualWeight+5* req.height-6.76* req.age);
        }

        else if(req.male==false) {
            req.caloricDemand=req.waf*(665.09+9.56* req.actualWeight+1.85* req.height-4.67* req.age);
        }

        return req.caloricDemand;
    }

    private String GetGoal(double actualWeight, double goalWeight) {
        if(actualWeight < goalWeight) {
                return  "przytyć";
        }
        if (actualWeight == goalWeight) {
            return "utrzymać wagę";
        }
        return "schudnąć";
    }

    public mealsCaloriesResponse GetMealsCalories(mealsCaloriesRequest req) {
        List<MealWithCalories> mealsWithCalories = new ArrayList<MealWithCalories>();
        for (Meal m:req.meals) {
            mealsWithCalories.add(m.toMealWithCalories());
        }
      return new mealsCaloriesResponse(mealsWithCalories);
    };

    public burnCaloriesResponse GetBurnCalories(burnCaloriesRequest req) {
        List<ActivityWithKcal> activitiesWithCalories = new ArrayList<ActivityWithKcal>();
        for (Activity a:req.activities) {
            activitiesWithCalories.add(a.toActivityWithKcal());
        }
        return new burnCaloriesResponse(activitiesWithCalories);
    };

}
