package br.com.crud.testproject.controllers.assembler;

import java.util.List;

public interface GenericAssembler<T,R> {
  
  R toObject(T object, Class<R> destinationType);

  List<R> toCollection(List<T> list, Class<R> destinationType);
}
