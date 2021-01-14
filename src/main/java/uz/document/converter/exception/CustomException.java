package uz.document.converter.exception;

public class CustomException extends Exception {
    private Integer code;

    public CustomException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}
