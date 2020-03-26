package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String currency;
    @NotNull
    private String region_country;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "currency", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> products;

    public Currency() {
    }

    public Currency(@NotBlank(message = "Qual é o simbolo da moeda?")
                            String code, @NotBlank(message = "Nome moeda é obrigatório")
            String currency, @NotBlank(message = "Região da moeda é obrigatória")
            String region_country) {
        this.code = code;
        this.currency = currency;
        this.region_country = region_country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRegion_country() {
        return region_country;
    }

    public void setRegion_country(String region_country) {
        this.region_country = region_country;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency1 = (Currency) o;
        return Objects.equals(code, currency1.code) &&
                Objects.equals(currency, currency1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, currency);
    }
}
