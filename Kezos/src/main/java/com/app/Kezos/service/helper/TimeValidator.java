package com.app.Kezos.service.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component("timeValidator")
public class TimeValidator implements IEvaluator{

    @Override
    public boolean validate(String submittedDate, String deadLineDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(submittedDate,formatter);
        LocalDate targetDate = LocalDate.parse(deadLineDate,formatter);
        boolean valFlag=false;
        if(currentDate.isAfter(targetDate)){
            valFlag=false;
        }
        else{
            valFlag=true;
        }
        return valFlag;
    }
    
}
