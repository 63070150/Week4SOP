package com.example.week4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    private Change change = new Change();
    @RequestMapping(value = "/getChange/{amount}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("amount") int amount){
        change.setB1000(amount/1000);
        amount -= change.getB1000()*1000;

        change.setB500(amount/500);
        amount -= change.getB500()*500;

        change.setB100(amount/100);
        amount -= change.getB100()*100;

        change.setB20(amount/20);
        amount -= change.getB20()*20;

        change.setB10(amount/10);
        amount -= change.getB10()*10;

        change.setB5(amount/5);
        amount -= change.getB5()*5;

        change.setB1(amount/1);
        amount -= change.getB1();
        return change;
    }
}

