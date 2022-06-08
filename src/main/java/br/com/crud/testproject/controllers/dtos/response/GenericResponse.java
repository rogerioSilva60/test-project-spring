package br.com.crud.testproject.controllers.dtos.response;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.crud.testproject.util.Response;

@Component
public class GenericResponse<T> {
    
    public Response<T> toObjectResponse(T t) {
        return new Response<>(t);
    }

    public Response<List<T>> toColectionResponse(List<T> list) {
        return new Response<>(list);
    }
}
