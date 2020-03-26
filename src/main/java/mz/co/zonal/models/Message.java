package mz.co.zonal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "sender_id")
    private User sender;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    @NotNull
    private Date timeSent;
    private boolean isRead;
    @NotNull
    private String message;

    public Message() {
    }

    public Message(@NotNull User sender, @NotNull User receiver, @NotNull Product product, @NotNull String contents) {
        this.timeSent = new Date();
        this.message = contents;
        this.isRead = false;
        this.product = product;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent() {
        this.timeSent = new Date();
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
