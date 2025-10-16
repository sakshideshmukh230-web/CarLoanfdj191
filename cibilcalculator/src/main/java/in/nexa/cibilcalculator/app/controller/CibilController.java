package in.nexa.cibilcalculator.app.controller;

import java.util.Date;
import java.util.Random;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CibilController {


	 @GetMapping("/cibil")				
	 public int getCibil() { 
	     int randomScore = new Random().nextInt(601) + 300; 
	     return randomScore;
    
	    }

}
