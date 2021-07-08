package diffy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
@Controller
public class DiffyApplication {
    private Service srv = new Service();
    public static void main(String[] args) {
        SpringApplication.run(DiffyApplication.class, args);
    }

    @GetMapping("/ping")
    public ResponseEntity<PingResponse> ping() {
        return ResponseEntity.ok(new PingResponse("Test", 1));
    }

    @PostMapping("/bmi")
    public ResponseEntity<BMIResponse> bmi(@RequestBody BMIRequest req) {
        return ResponseEntity.ok(srv.GetBMI(req));
    }

    @PostMapping("/caloricDemand")
    public ResponseEntity<caloricDemandResponse> bmi(@RequestBody caloricDemandRequest req) {
        return ResponseEntity.ok(srv.GetCaloricDemand(req));
    }

    @PostMapping("/mealsCalories")
    public ResponseEntity<mealsCaloriesResponse> bmi(@RequestBody mealsCaloriesRequest req) {
        return ResponseEntity.ok(srv.GetMealsCalories(req));
    }

    @PostMapping("/burnCalories")
    public ResponseEntity<burnCaloriesResponse> bmi(@RequestBody burnCaloriesRequest req) {
        return ResponseEntity.ok(srv.GetBurnCalories(req));
    }
}
