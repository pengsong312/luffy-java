package com.luffy.java.spring.boot.entity;

import javax.persistence.*;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description todo
 */
@Entity
@Table(name = "persion")
public class Persion {

    private int id;
    private String name;

    public Persion() {

    }

    public Persion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

