package com.hello.core.model;

import java.time.LocalDate;

import com.hello.core.model.for_enum.CivilStatus;
import com.hello.core.model.for_enum.Gender;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Debtor {
    private IntegerProperty debtorID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty middleName;
    private StringProperty email;
    private StringProperty phoneNumber;
    private StringProperty address;
    private ObjectProperty<Gender> gender;
    private StringProperty fullname;
    private ObjectProperty<LocalDate> dateOfBirth;
    private ObjectProperty<CivilStatus> status;

    public Debtor(int debtorID,
            String firstName,
            String lastName,
            String middleName,
            String email,
            String phoneNumber,
            String address,
            Gender gender,
            LocalDate dateOfBirth,
            CivilStatus status) {
        this.debtorID = new SimpleIntegerProperty(debtorID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleObjectProperty<>(gender);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.status = new SimpleObjectProperty<>(status);

        if (middleName == null || middleName.isBlank()) {
            this.fullname = new SimpleStringProperty(firstName + " " + lastName);
        } else {
            this.fullname = new SimpleStringProperty(firstName + " " + middleName.charAt(0) + ". " + lastName);
        }
    }

    // debtorID
    public int getDebtorID() {
        return debtorID.get();
    }

    public void setDebtorID(int debtorID) {
        this.debtorID.set(debtorID);
    }

    public IntegerProperty debtorIDProperty() {
        return debtorID;
    }

    // firstname
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    // lastname
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    // middleName
    public String getMiddleName() {
        return middleName.get();
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    // email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // phoneNumber
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    // address
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    // gender
    public Gender getGender() {
        return gender.get();
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    // fullname
    public String getFullname() {
        return fullname.get();
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    public StringProperty fullnameProperty() {
        return fullname;
    }

    // dateOfBirth
    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    // status
    public CivilStatus getStatus() {
        return status.get();
    }

    public void setStatus(CivilStatus status) {
        this.status.set(status);
    }

    public ObjectProperty<CivilStatus> statusProperty() {
        return status;
    }
}
