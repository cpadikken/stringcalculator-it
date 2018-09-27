package nl.oose.stringcalculator.webservice;

import nl.oose.stringcalculator.core.StringAddedFactory;
import nl.oose.stringcalculator.core.StringAdder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StringCalculatorService {
    private StringAdder adder;

    public StringCalculatorService()
    {
        adder = StringAddedFactory.create();
    }

    public StringCalculatorService(StringAdder adder)
    {
        this.adder = adder;
    }

    @RequestMapping("/add")
    public AddResult add(@RequestParam(value = "numbers") String numbers) {
        return new AddResult(numbers, adder.add(numbers));
    }

    public static void main(String[] args) {
        SpringApplication.run(StringCalculatorService.class, args);
    }
}
