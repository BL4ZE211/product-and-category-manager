package com.productmanager.api;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class Product {


    public long id;

    public String name;

    boolean is_active;

    LocalDateTime created_at;

    LocalDateTime updated_at;

    double price;

    long category_id;

    public Product(String name,float price,long category_id,float Price) {
        this.name = name;
        this.is_active =  true;;
        this.created_at = LocalDateTime.now();
        this.category_id = category_id;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_active=" + is_active +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", price=" + price +
                ", category_id=" + category_id +
                '}';
    }
}
