package za.ac.cput.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value={"/","/restaurant-management"})
    public ResponseEntity<String> restaurantManagement(){
        return new ResponseEntity<>("Welcome to our Restaurant Management Application!", HttpStatus.OK);
    }
}
