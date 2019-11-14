package com.nawe.provideControl.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ordr")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer count;

    public Order() {
    }

    public Order(String name, String description, Integer count) {
        this.name = name;
        this.description = description;
        this.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
