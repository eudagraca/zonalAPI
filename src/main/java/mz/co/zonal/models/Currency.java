package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Currency{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Qual é o simbolo da moeda?")
    private String code;
    @NotBlank(message = "Nome moeda é obrigatório")
    private String currency;
    @NotBlank(message = "Região da moeda é obrigatória")
    private String region_country;
    @OneToOne(mappedBy = "currency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
