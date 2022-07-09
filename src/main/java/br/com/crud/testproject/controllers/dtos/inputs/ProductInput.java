package br.com.crud.testproject.controllers.dtos.inputs;

import java.math.BigDecimal;

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

    @ApiModelProperty(example = "5.99")
    private BigDecimal price = BigDecimal.ZERO;

    public ProductInput() {
    }

    public ProductInput(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
