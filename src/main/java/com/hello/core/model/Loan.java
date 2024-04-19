package com.hello.core.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Loan {
    private IntegerProperty loanID;
    private ObjectProperty<LocalDate> dateOfLoan;
    private IntegerProperty amount;

    public Loan(int loanID, LocalDate dateOfLoan, int amount) {
        this.loanID = new SimpleIntegerProperty(loanID);
        this.dateOfLoan = new SimpleObjectProperty<>(dateOfLoan);
        this.amount = new SimpleIntegerProperty(amount);
    }

    // loanID
    public int getLoanID() {
        return loanID.get();
    }

    public void setLoanID(int loanID) {
        this.loanID.set(loanID);
    }

    public IntegerProperty loanIDProperty() {
        return loanID;
    }

    // date of loan
    public LocalDate getDateOfLoan() {
        return dateOfLoan.get();
    }

    public void setDateOfLoan(LocalDate dateOfLoan) {
        this.dateOfLoan.set(dateOfLoan);
    }

    public ObjectProperty<LocalDate> dateOfLoanProperty() {
        return dateOfLoan;
    }

    // amount
    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public IntegerProperty amountProperty() {
        return amount;
    }
}
