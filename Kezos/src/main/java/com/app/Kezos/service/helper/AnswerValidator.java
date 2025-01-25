package com.app.Kezos.service.helper;

import org.springframework.stereotype.Component;

@Component("answerValidator")
public class AnswerValidator implements IEvaluator{

    @Override
    public boolean validate(String studentInfo, String courseInfo) {
        return true;
    }
    
}
