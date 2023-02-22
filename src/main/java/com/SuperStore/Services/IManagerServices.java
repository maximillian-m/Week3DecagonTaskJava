package com.SuperStore.Services;

import com.SuperStore.Exceptions.CreteriaException;
import com.SuperStore.Models.Applicants;
import com.SuperStore.Models.Cashier;

public interface IManagerServices {
    void hireCashier(Applicants applicants) throws CreteriaException;
    void fireCashier(Cashier cashier) throws CreteriaException;
}
