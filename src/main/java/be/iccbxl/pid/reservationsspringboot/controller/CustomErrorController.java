package be.iccbxl.pid.reservationsspringboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @GetMapping
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleErrorGet() {
        return "Erreur 500 (GET) : Une erreur s'est produite.";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleErrorPost() {
        return "Erreur 500 (POST) : Une erreur s'est produite.";
    }
}
