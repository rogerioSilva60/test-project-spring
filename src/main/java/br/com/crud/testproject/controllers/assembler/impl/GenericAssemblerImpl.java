package br.com.crud.testproject.controllers.assembler.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.crud.testproject.controllers.assembler.GenericAssembler;

@Component
public class GenericAssemblerImpl<T,R> implements GenericAssembler<T,R>{

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public R toObject(T object, Class<R> destinationType) {
    var value = modelMapper.map(object, destinationType);
    return value;
  }

  @Override
  public List<R> toCollection(List<T> list, Class<R> destinationType) {
    List<R> values = list.stream()
      .map(obj -> toObject(obj, destinationType))
      .collect(Collectors.toList());
    return values;
  }
}
