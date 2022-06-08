package br.com.crud.testproject.controllers.dtos.inputs;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Product")
public class ProductInput {

  @ApiModelProperty(example = "Arroz", required = true)
  @NotEmpty(message = "Required name")
  private String name;

  @ApiModelProperty(example = "Tipo 1", required = true)
  @NotEmpty(message = "Required description")
  private String description;

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
