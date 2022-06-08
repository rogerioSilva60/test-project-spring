package br.com.crud.testproject.util;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private T data;
    private List<String> errors;

    public Response() {
    }
    
    public Response(T data) {
    	this.data = data;
    }
    
    public Response(List<String> errors){
        this.errors = errors;
    }

    public Response(T data, List<String> errors){
        this.data = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
