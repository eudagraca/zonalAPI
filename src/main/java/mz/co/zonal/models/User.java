package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String fullName;
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private int phoneNumber;
    @NotEmpty
    @Size(min = 5)
    private String password;
    private  Date createAt;
    @Nullable
    private String picPath;
    @Nullable
    private String token;
    private String city;
    private String province;
    private String country;
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(
            name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
            mappedBy = "user")
    @JsonIgnore
    private List<Product> product;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
    mappedBy = "user")
    @JsonIgnore
    private List<View> view;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
    mappedBy = "sender")
    @JsonIgnore
    private List<Message> messagesSender;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            mappedBy = "receiver")
    @JsonIgnore
    private List<Message> messagesReceiver;
    private Double latitude;
    private Double longitude;
    @OneToMany(
            mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ProductLikes> productLikes;

    public User() {
        super();
    }

    public User(@NotEmpty String fullName, @NotEmpty @Email String email,
                @NotNull int phoneNumber, @NotEmpty @Size(min = 5) String password) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        createAt = new Date();
    }

    public User(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<View> getView() {
        return view;
    }

    public void setView(List<View> view) {
        this.view = view;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<ProductLikes> getProductLikes() {
        return productLikes;
    }

    public void setProductLikes(List<ProductLikes> productLikes) {
        this.productLikes = productLikes;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
