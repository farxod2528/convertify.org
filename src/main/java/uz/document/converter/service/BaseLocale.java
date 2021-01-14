package uz.document.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Objects;

public class BaseLocale {
    @Autowired
    private Environment env;

    public BaseLocale(){

    }

    public String getMessage(String key){
        String value = env.getProperty(key);
        return Objects.isNull(value) ? "" : value;
    }

    public Integer getCode(String key){
        Integer code = env.getProperty(key, Integer.class);
        return Objects.isNull(code) ? 500 : code;
    }
}
