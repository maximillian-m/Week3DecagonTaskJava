package com.SuperStore.Models;

import com.SuperStore.Exceptions.CreteriaException;
import com.SuperStore.Services.ServiceImpl.ManagerImpl;
public class Manager extends Staff {

    private ManagerImpl managerImpl;

    public Manager(String name, ManagerImpl managerImpl) {
        super(name);
        this.managerImpl = managerImpl;
    }

    public void hireCashier(Applicants applicants) throws CreteriaException {
        managerImpl.hireCashier(applicants);
    }
    public void fireCashier(Cashier cashier) throws CreteriaException {
        managerImpl.fireCashier(cashier);
    }
    public void getListOfStockAndPrices(){
       managerImpl.getListOfStockAndPrices();
    }
    public double getStoreCash(){
        return managerImpl.getStoreCash();
    }
    public void strikeCashier(Cashier cashier){
        managerImpl.strikeCashier(cashier);
    }

    @Override
    String getStaffId() {
        return staffId;
    }
}
