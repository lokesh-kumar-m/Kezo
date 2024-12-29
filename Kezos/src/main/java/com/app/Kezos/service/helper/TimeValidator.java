package com.app.Kezos.service.helper;

import java.time.LocalDate;
import java.util.HashMap;

public class TimeValidator implements IEvaluator{

    @Override
    public boolean validateAnswer(HashMap<String, String> studentInfo, HashMap<String, String> courseInfo) {
        LocalDate currentDate = LocalDate.parse(studentInfo.get("date"));
        LocalDate targetDate = LocalDate.parse(courseInfo.get("date"));
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
