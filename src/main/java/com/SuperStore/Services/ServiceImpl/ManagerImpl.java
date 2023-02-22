package com.SuperStore.Services.ServiceImpl;

import com.SuperStore.Exceptions.CreteriaException;
import com.SuperStore.Models.Applicants;
import com.SuperStore.Models.Cashier;
import com.SuperStore.Models.Store;
import com.SuperStore.Services.IManagerServices;

public class ManagerImpl implements IManagerServices {
    private Store store;

    public ManagerImpl(Store store) {
        this.store = store;
    }

    public void hireCashier(Applicants applicants) throws CreteriaException {
        if (applicants.getAge() >= 19 && applicants.getYearsOfExperience() >= 3) {
            applicants.setHired(true);
            System.out.println("congratulations " + applicants.getName() + " you have been hired");
        } else {
            throw new CreteriaException("Dear " + applicants.getName() + " You are not Eligible for this job");
        }
    }


        public void fireCashier(Cashier cashier) throws CreteriaException{
            if (cashier.getCashierStrike() >= 3) {
                cashier.setCashier(false);
                System.out.println("your appointment has been terminated");
            }else{
                throw new CreteriaException("Sorry Cashier hasn't got upto three strikes and cannot be Fired");
            }
        }
        public void strikeCashier(Cashier cashier){
                cashier.setCashierStrike(cashier.getCashierStrike() + 1);
        }
        public void getListOfStockAndPrices () {
            System.out.println(store);
        }
        public double getStoreCash () {
            return store.getStoreCash();
        }

}
