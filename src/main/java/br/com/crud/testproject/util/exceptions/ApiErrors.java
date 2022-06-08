package br.com.crud.testproject.util.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErrors<T> {
    
    private List<String> errors;

    public ApiErrors(T t) {
        errors = new ArrayList<>();
        
        if(t instanceof BindingResult) {
            BindingResult bindingResult = (BindingResult) t;
            this.errors = new ArrayList<String>();
            bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
        }
        else if(t instanceof NotFoundException) {
            NotFoundException notFoundException = (NotFoundException) t;            
            errors.add(notFoundException.getMessage());
        }
        else if(t instanceof BusinessException) {
            BusinessException businessException = (BusinessException) t;
            errors.add(businessException.getMessage());
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
