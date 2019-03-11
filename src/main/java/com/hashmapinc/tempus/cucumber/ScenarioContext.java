package com.hashmapinc.tempus.cucumber;

import com.hashmapinc.tempus.enums.Context;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ScenarioContext {

    private  Map<Context, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new EnumMap<>(Context.class);
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getContext(Context key){
        return scenarioContext.get(key);
    }

    public Boolean contains(Context key){
        return scenarioContext.containsKey(key);
    }

}