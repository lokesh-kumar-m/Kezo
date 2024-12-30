package com.app.Kezos.service.helper;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component("answerValidator")
public class AnswerValidator implements IEvaluator{

    @Override
    public boolean validateAnswer(HashMap<String, String> studentInfo, HashMap<String, String> courseInfo) {
        return true;
    }
    
}
