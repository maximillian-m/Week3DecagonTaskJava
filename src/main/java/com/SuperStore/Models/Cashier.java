package com.SuperStore.Models;

import com.SuperStore.Exceptions.CheckOutException;
import com.SuperStore.Exceptions.CreteriaException;
import com.SuperStore.Exceptions.SellProductException;
import com.SuperStore.Services.ServiceImpl.cashierServImplm;

import java.util.Queue;

public class Cashier extends Staff {

    private boolean isCashier;
    private Store store;
    private int cashierStrike = 0;
    private com.SuperStore.Services.ServiceImpl.cashierServImplm cashierServImplm;
    public Cashier(String name, cashierServImplm cashierServImplm) {
        super(name);
        this.cashierServImplm = cashierServImplm;
        this.isCashier = true;
    }

    public void setCashier(boolean cashier) {
        isCashier = cashier;
    }
    public void sellProducts (Customer customer) throws SellProductException, CreteriaException {
        if (isCashier) {
            this.cashierServImplm.sellProducts(customer);
        }else{
            throw new CreteriaException(" Sorry you are not hired yet to perform duties or you have been Fired");
        }
    }

    public double customerCheck(Customer customer) throws CheckOutException, SellProductException, CreteriaException {
        if (isCashier) {
            return cashierServImplm.customerCheck(customer);
        }else{
            throw new CreteriaException("Sorry you are not hired yet to perform duties or you have been Fired ");
        }
    }
    public void checkOutMethod(boolean useFIFO) throws CheckOutException, SellProductException, CreteriaException {
        if (isCashier) {
            cashierServImplm.checkOutMethod(useFIFO);
        }else{
            throw new CreteriaException("Sorry you are not hired yet to perform duties or you have been Fired");
        }
    }
    public void addToCustomerList(Customer customer) throws CreteriaException{
        if (isCashier) {
            cashierServImplm.addCustomersToList(customer);
        }else{
            throw new CreteriaException("Sorry you are not hired yet to perform duties or you have been Fired");
        }
    }
    public int getCashierStrike() {
        return cashierStrike;
    }

    public boolean isCashier() {
        return isCashier;
    }

    public boolean isCheckPriority() throws CreteriaException{
        if (isCashier) {
            return cashierServImplm.isCheckPriority();
        }
        throw new CreteriaException("Sorry you are not hired yet to perform duties or you have been Fired");
    }
    public void setCashierStrike(int cashierStrike) {
        this.cashierStrike = cashierStrike;
    }
    public Queue<Customer> getCustomerList() throws CreteriaException{
        if (isCashier) {
            return cashierServImplm.getCustomerList();
        }else{
            throw new CreteriaException("Sorry you are not hired yet to perform duties or you have been Fired");
        }
    }

    @Override
    String getStaffId() {
        return staffId;
    }
}
