package PulseCheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PulseCheckController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Welcome to PulseCheck API!";
    }
}