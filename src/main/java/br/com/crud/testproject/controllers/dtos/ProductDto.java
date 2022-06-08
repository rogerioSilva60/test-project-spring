package br.com.crud.testproject.controllers.dtos;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Product DTO")
public class ProductDto {
  
  private long id;
  private String name;
  private String description;

  public void setId(long id) {
      this.id = id;
  }

  public long getId() {
      return id;
  }

  public void setName(String name) {
      this.name = name;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public String getName() {
      return name;
  }

  public String getDescription() {
      return description;
  }
}
