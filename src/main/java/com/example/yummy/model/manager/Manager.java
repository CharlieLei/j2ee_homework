package com.example.yummy.model.manager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 经理
 */
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @Column(name = "managerId")
    private String id;
    @Column(name = "password")
    private String password;

    private String name;

    public Manager() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
