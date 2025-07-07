package com.example.zakkaya.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zakkaya.repositort.ProductRepository;
import com.example.zakkaya.dto.cart.*;
import com.example.zakkaya.entity.Product;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {
    
    private static final String CART_SESSION_KEY = "cart";
    
    //商品検索用
    private final ProductRepository productRepository;

    @Autowired
    public CartService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // //セッションからカート取得
    public Cart getCartFromSession(HttpSession session){
        Cart cart = (Cart)session.getAttribute(CART_SESSION_KEY);

        //cartがなければ新規作成
        if(cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    //カートに商品を追加
    public Cart addItemToCart(Integer productId, Integer quantity, HttpSession session) {
        Optional<Product> productOpt = productRepository.findById(productId);

        //商品が存在したら
        if(productOpt.isPresent()) {
            //Productを取得
            Product product = productOpt.get();

            //Cart取得
            Cart cart = getCartFromSession(session);

            //CartItemを作成してCartに追加
            CartItem item = new CartItem();
            item.setProductId(productId);
            item.setPrice(productId);
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setImageUrl(product.getImageUrl());
            item.setQuantity(quantity);
            item.setSubtotal(item.getPrice() * quantity);
            cart.addItem(item);

            //Cartをセッションに追加
            session.setAttribute(CART_SESSION_KEY, cart);
            //Cartをreturn
            return cart;
        }
        //商品がなければnullをreturn
        return null;
    }

    //カートの数量を変更
    public Cart updateItemQuantity(String itemId, Integer quantity, HttpSession session) {
        //Cart取得
        Cart cart = getCartFromSession(session);
        //itemIdが同じもの//数量変更
        cart.updateQuantity(itemId, quantity);
        //ここいる？
        session.setAttribute(CART_SESSION_KEY, cart);

        //cartをreturn
        return cart;
    }


    //カートから商品を削除
    public Cart removeItemFromCart(String itemId, HttpSession session){
        Cart cart = (Cart)session.getAttribute(CART_SESSION_KEY);
        cart.removeItem(itemId);

        //ここいる？
        session.setAttribute(CART_SESSION_KEY, cart);
        return cart;
    }

    //カートをクリア
    public void clearCart(HttpSession session){
        session.removeAttribute(CART_SESSION_KEY);
    }


}
