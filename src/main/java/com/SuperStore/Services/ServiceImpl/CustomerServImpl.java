package com.SuperStore.Services.ServiceImpl;

import com.SuperStore.Models.Customer;
import com.SuperStore.Models.Product;
import com.SuperStore.Services.ICustomerServices;

import java.util.List;


public class CustomerServImpl implements ICustomerServices {
    private Customer customer;

    public CustomerServImpl(Customer customer) {
        this.customer = customer;
    }

    public void addToCart(String product, int quantity) {
       customer.addToCart(product, quantity);
    }
    public double getWallet() {
        return customer.getWallet();
    }
    public List<Product> getCart() {
        return customer.getCart();
    }
    public void setWallet(double wallet) {

        customer.setWallet(wallet);
    }
}
