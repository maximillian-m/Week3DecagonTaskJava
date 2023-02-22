package com.SuperStore.Services.ServiceImpl;

import com.SuperStore.Exceptions.CheckOutException;
import com.SuperStore.Exceptions.SellProductException;
import com.SuperStore.Models.Customer;
import com.SuperStore.Models.Product;
import com.SuperStore.Models.ReadProductList;
import com.SuperStore.Services.ICashierServices;

import java.util.*;

public class cashierServImplm implements ICashierServices {

    private double customerCheck;
    private ReadProductList list;
    private Queue<Customer> customerList = new LinkedList<>();
    private boolean checkPriority;

    public cashierServImplm(ReadProductList list) {
        this.list = list;
    }

    public double getCustomerCheck() {
        return customerCheck;
    }

    public void setCustomerCheck(double customerCheck) {
        this.customerCheck = customerCheck;
    }

    public void checkOutMethod(boolean useFIFO) throws CheckOutException, SellProductException {
        if (customerList == null || customerList.isEmpty()) {
            System.out.println("There is no Customer on the Queue");
            return;
        }
        if (useFIFO) {
            Iterator it = customerList.iterator();
            while (it.hasNext()) {
                Customer cst = (Customer) it.next();
                if (cst != null) {
                    customerCheck(cst);
                    System.out.println("You have used FIFO method to sell to customers");
                    it.remove();
                }
            }
        } else {
            setCheckPriority(true);
            PriorityQueue<Customer> priority = new PriorityQueue<>(customerList.size(), new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o2.getCart().size() - o1.getCart().size();
                }
            });
            priority.addAll(customerList);
            while (!priority.isEmpty()) {
                Customer customers = priority.poll();
                if (customers != null) {
                    customerCheck(customers);
                    customerList.poll();
                    System.out.println("Checking out using priority method");
                }
            }
        }
    }


    public void setCheckPriority(boolean set){
        this.checkPriority = set;
    }

    public boolean isCheckPriority() {
        return checkPriority;
    }

    public Queue<Customer> getCustomerList() {
        return customerList;
    }

    public void addCustomersToList(Customer customer){
        customerList.add(customer);
    }

public double customerCheck(Customer customer) throws CheckOutException, SellProductException {
    double output = 0.0;
    List<Product> cart = customer.getCart();
    Iterator<Product> cartIterator = cart.iterator();

    while (cartIterator.hasNext()) {
        Product a = cartIterator.next();

        for (Product b : list.getProducts()) {
            if (b.getName().equals(a.getName())) {
                if (b.getQuantity() == 0) {
                    cartIterator.remove();
                    throw new CheckOutException(a.getName() + " is Out of Stock");
                } else if (b.getQuantity() < a.getQuantity()) {
                    cartIterator.remove();
                    throw new CheckOutException("We have Limited quantity of " + b.getName());
                } else {
                    b.setQuantity(b.getQuantity() - a.getQuantity());
                    output += a.getPrice() * a.getQuantity();
                }
            }
            setCustomerCheck(output);
        }
    }

    sellProducts(customer);
    return output;
}


    @Override
        public void sellProducts (Customer customer) throws SellProductException {
            if(getCustomerCheck() > customer.getWallet()){
                throw new SellProductException(" Insufficient wallet amount to Purchase goods");
            }else{
                System.out.println("Purchase successful.... Here is your receipt");
                issueReceipt(customer);
                customer.getCart().clear();
            }
        }

        private void issueReceipt(Customer customer){
            System.out.println("RECEIPT OF PURCHASE");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("Your purchases include: ");
            for(Product item : customer.getCart()){
                System.out.println("item :" + item.getName() + "   QTY:" + item.getQuantity() + "   price:" + item.getPrice());
                System.out.println();
            }
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("Total : NGN" + getCustomerCheck());
            customer.setWallet(customer.getWallet() - getCustomerCheck());
//            customer.getCart().clear();
        }

    }
