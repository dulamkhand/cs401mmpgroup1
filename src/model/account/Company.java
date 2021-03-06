/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import javafx.beans.property.StringProperty;

/**
 *
 * @author bek
 */
public class Company extends Vendor {

    private StringProperty compRegNumber;
    private StringProperty name;
    private StringProperty companyRepresentative;
    
    public Company() {
    }

    public Company(StringProperty compRegNumber, StringProperty name, StringProperty companyRepresentative, Account acc, StringProperty number) {
        super(acc, number);
        this.compRegNumber = compRegNumber;
        this.name = name;
        this.companyRepresentative = companyRepresentative;
    }
    
    public void setName(StringProperty name) {
        this.name = name;
    }
    
    public StringProperty getName() {
        return name;
    }
}
