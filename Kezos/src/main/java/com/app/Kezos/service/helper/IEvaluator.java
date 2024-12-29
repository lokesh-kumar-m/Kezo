package com.app.Kezos.service.helper;

import java.util.HashMap;


public interface IEvaluator {
    boolean validateAnswer(HashMap<String,String> studentInfo, HashMap<String,String> courseInfo);
}
