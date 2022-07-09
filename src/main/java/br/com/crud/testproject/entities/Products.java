package br.com.crud.testproject.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

  @Column
  private BigDecimal price = BigDecimal.ZERO;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  public Products() {}

  public Products( String name, String description, BigDecimal price) {
    this.name = name;
    this.description = description;
  }

  public Products(long id, String name, String description, BigDecimal price) {
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

  public void setPrice(BigDecimal price) {
      this.price = price;
  }

  public BigDecimal getPrice() {
      return price;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
      this.createdDate = createdDate;
  }

  public LocalDateTime getCreatedDate() {
      return createdDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
      this.updatedDate = updatedDate;
  }

  public LocalDateTime getUpdatedDate() {
      return updatedDate;
  }

  @PrePersist
  public void beforeSave() {
    createdDate = updatedDate = LocalDateTime.now();
  }

  @PreUpdate
  public void beforeUpdate() {
    updatedDate = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object obj) {
    return (!Objects.isNull(obj) && (obj instanceof Products) && this.getId() == ((Products) obj).getId());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
