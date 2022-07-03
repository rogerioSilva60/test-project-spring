package br.com.crud.testproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(name = "is_active")
  private Boolean isActive = true;

  public Products() {}

  public Products(long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
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

  public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
  }

  public Boolean getIsActive() {
      return isActive;
  }

  @Override
  public boolean equals(Object arg0) {
      return super.equals(arg0);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
