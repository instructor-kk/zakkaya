package com.example.zakkaya.dto.cart;

import java.io.Serializable;
import java.util.LinkedHashMap;

import lombok.Data;

import java.util.*;

@Data
public class Cart implements Serializable{
    private Map<String, CartItem> items = new LinkedHashMap<>();
    private int totalQuantity;
    private int totalPrice;

    //カートに商品を追加
    public void addItem(CartItem item){
        String itemId = String.valueOf(item.getProductId());

        //既存の商品があれば数量を加算(カート内の数量+商品詳細で選択した数量)
        if(items.containsKey(itemId)){
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            existingItem.setSubtotal(existingItem.getPrice() * existingItem.getQuantity());
        }else{
            //新しい商品を追加
            item.setId(itemId);
            item.setSubtotal(item.getPrice() * item.getQuantity());
            items.put(itemId, item);
        }
        //カート内合計金額の計算
        calculateTotals();
    }

    //カート内の商品数量を更新
    public void updateQuantity(String itemId, int quantity){
        //既存の商品があれば数量を加算(新しい数量で上書き)
        if(items.containsKey(itemId)){
            //既存の商品を取り出して
            CartItem item = items.get(itemId);

            //数量を変更する            
            item.setQuantity(quantity);
            //小計を計算する
            item.setSubtotal(item.getPrice() * quantity);

            //カート内合計を計算
            calculateTotals();
        }
    }

    //カートから商品を削除
    public void removeItem(String itemId){
        //同一商品があれば削除
        if(items.containsKey(itemId)){
            items.remove(itemId);
            calculateTotals();
        }
    }

    //カート内合計金額を計算
    public void calculateTotals(){
        totalQuantity = 0;
        totalPrice = 0;

        for(CartItem item: items.values()){
            totalQuantity += item.getQuantity();
            totalPrice += item.getSubtotal();
        }
    }

}
