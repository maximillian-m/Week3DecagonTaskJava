package com.SuperStore.Services;

import com.SuperStore.Exceptions.SellProductException;
import com.SuperStore.Models.Customer;

public interface ICashierServices {
        public void sellProducts(Customer customer) throws SellProductException;


    }

