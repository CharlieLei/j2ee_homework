package com.example.yummy.model.member;

import com.example.yummy.model.Address;

import javax.persistence.*;

@Entity
@Table(name = "memberDeliveryAddrs")
public class MemberDeliveryAddress {
    @Id
    @Column(name = "addrId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @Column(name = "memberId")
    private String memberId;
    @Embedded
    private Address address;

    public MemberDeliveryAddress() {
        this.address = new Address();
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAddrName() {
        return address.getAddrName();
    }

    public void setAddrName(String addrName) {
        address.setAddrName(addrName);
    }

    public double getLongitude() {
        return address.getLongitude();
    }

    public void setLongitude(double longitude) {
        address.setLongitude(longitude);
    }

    public double getLatitude() {
        return address.getLatitude();
    }

    public void setLatitude(double latitude) {
        address.setLatitude(latitude);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
