package com.example.yummy.model.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "productItems")
public class ProductItem implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "productId")
    private int productId;
    @Column(name = "itemId")
    private int itemId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "itemId", referencedColumnName = "productId")
//    private Product itemProduct;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "productId")
//    private Product productBelong;

    public ProductItem() {
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

//    public Product getItemProduct() {
//        return itemProduct;
//    }
//
//    public void setItemProduct(Product itemProduct) {
//        this.itemProduct = itemProduct;
//    }
}
