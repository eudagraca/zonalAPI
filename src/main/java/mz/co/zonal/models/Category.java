package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String imagePath;
    @OneToMany(cascade = CascadeType.REMOVE,
    mappedBy = "category")
    @JsonIgnore
    private List<Product> product;
    @Nullable
    private byte[] categoryImage;

    public Category() {
    }

    public Category(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Product> getProduct() {
        return product;
    }

    public byte[] getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(byte[] categoryImage) {
        this.categoryImage = categoryImage;
    }
}
