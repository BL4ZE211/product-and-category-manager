package com.productmanager.api;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter


public class Category {


    public long id;

    public String name;

    boolean is_active;

    LocalDateTime created_at;

    LocalDateTime updated_at;

    public Category(String name,long id) {
        this.name = name;
        this.id = id;
        this.is_active =  true;
        this.created_at = LocalDateTime.now();
    }
}
