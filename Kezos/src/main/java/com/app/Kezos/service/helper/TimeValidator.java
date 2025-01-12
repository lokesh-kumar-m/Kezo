package com.app.Kezos.service.helper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component("timeValidator")
public class TimeValidator implements IEvaluator{

    @Override
    public boolean validateAnswer(String submittedDate, String deadLineDate) {
        LocalDate currentDate = LocalDate.parse(submittedDate);
        LocalDate targetDate = LocalDate.parse(deadLineDate);
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
