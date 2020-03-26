package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @ManyToOne
    private Category category;
    private boolean sold;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    @CreatedBy
    private User user;
    @Nullable
    @OneToMany(mappedBy = "product",
            cascade = CascadeType.REMOVE)
    private List<Images> images;
    private Date createdDate = new Date();
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE,
            mappedBy = "product")
    private List<View> view;
    @Nullable
    @OneToMany(cascade = CascadeType.REMOVE,
            mappedBy = "product")
    private List<Message> messages;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="type_id")
    private Type type;
    private Long viewCount;
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;
    @Nullable
    @OneToMany(mappedBy = "product",
            cascade = CascadeType.REMOVE)
    private List<ProductLikes> productLikes;
    @Nullable
    @Column
    @ElementCollection(targetClass=byte.class)
    private List<byte[]> imagesByte;
    private Long likesCount;

    public Product() {
    }

    public Product(@NotNull String title, @NotNull String description,
                   @NotNull double price, Category category,
                   Currency currency, User user,
                   Type type) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.currency = currency;
        this.user = user;
        this.type = type;
        this.sold = false;
        this.likesCount = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public Date getCreateAt() {
        return createdDate;
    }

    public List<View> getView() {
        return view;
    }

    public void setView(List<View> view) {
        this.view = view;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Nullable
    public List<ProductLikes> getProductLikes() {
        return productLikes;
    }

    public void setProductLikes(@Nullable List<ProductLikes> productLikes) {
        this.productLikes = productLikes;
    }

    @Nullable
    public List<byte[]> getImagesByte() {
        return imagesByte;
    }

    public void setImagesByte(@Nullable List<byte[]> imagesByte) {
        this.imagesByte = imagesByte;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    @Nullable
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(@Nullable List<Message> messages) {
        this.messages = messages;
    }
}
