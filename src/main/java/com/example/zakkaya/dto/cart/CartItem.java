package com.example.zakkaya.dto.cart;



import java.io.Serializable;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable{

    private String id;
    private Integer productId;
    private String name;
    private Integer price;
    private String imageUrl;
    private int quantity;
    private int subtotal;    
}
