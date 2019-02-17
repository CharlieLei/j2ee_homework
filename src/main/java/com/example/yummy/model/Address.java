package com.example.yummy.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
    private String memberId;
    private String name;
    private double longtitude;
    private double latitude;
}
