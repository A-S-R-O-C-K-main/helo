package com.hello.layout;

import java.time.LocalDate;

import com.hello.App;
import com.hello.core.model.Debtor;
import com.hello.core.model.for_enum.CivilStatus;
import com.hello.core.model.for_enum.Gender;
import com.hello.core.server.DebtorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainController {
    // ---------ROOT------------------------------//
    @FXML
    private StackPane root;

    // ----------VBOX--------------------------------//
    @FXML
    private VBox mainVBox;
    @FXML
    private VBox addDebtorVBox;
    @FXML
    private VBox debtorInfoVBox;

    // ----------BUTTON----------------------------------//
    @FXML
    private Button mainButton;
    @FXML
    private Button addButton;

    // -----------SUBMIT BUTTON---------------------------------//
    @FXML
    private Button submitDebtorButton;

    // ----------TEXTFIELD--------------------------------------//
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;

    // ---------COMBOBOX-----------------------------------//
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private ComboBox<CivilStatus> statusComboBox;

    // ----------DATEPICKER----------------------------------------//
    @FXML
    private DatePicker birthdayPicker;

    // ----------TABLE-VIEW AND TABLE-COLUMN---------------------------------//
    @FXML
    private TableView<Debtor> debtorTableView;
    @FXML
    private TableColumn<Debtor, String> debtorName;
    @FXML
    private TableColumn<Debtor, String> debtorDate;

    // ---------LABEL-------------------------------------//
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label middleNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthdateLabel;
    @FXML
    private Label statusLabel;

    private App app;
    private Debtor debtor;

    public void load(App app) {
        this.app = app;
        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        app.setApplicationRoot(root);
        genderComboBox.getItems().setAll(Gender.values());
        statusComboBox.getItems().setAll(CivilStatus.values());
        birthdayPicker.getEditor().setOnMouseClicked(e -> {
            birthdayPicker.show();
        });

        debtorName.setCellValueFactory(e -> e.getValue().fullnameProperty());
        debtorTableView.setItems(app.getDeptorMasterList());

        // click the main button
        mainButton.setOnAction(e -> {
            mainScene();
        });
        // click the add button
        addButton.setOnAction(e -> {
            addDebtorScene();
            submitDebtorButton();
        });
        debtorTableView.setRowFactory(tv -> new TableRow<>() {

            {
                itemProperty().addListener((obs, oldItem, newItem) -> {

                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && newItem != null) {
                            debtorInfoScene();
                            debtorInfo();
                        }
                    });

                    if (newItem != null) {
                        setCursor(javafx.scene.Cursor.HAND);
                    } else {
                        setText(null);
                        setCursor(null);

                    }
                });
            }

        });
    }

    private void _load_bindings() {

    }

    private void _load_listeners() {

    }

    private void submitDebtorButton() {
        submitDebtorButton.setOnAction(e -> {
            String firstname = firstNameField.getText().trim();
            String lastname = lastNameField.getText().trim();
            String middlename = middleNameField.getText().trim();
            Gender gender = genderComboBox.getSelectionModel().getSelectedItem();
            String email = emailField.getText();
            String phonenumber = phoneNumberField.getText().trim();
            String address = addressField.getText().trim();
            LocalDate birthdate = birthdayPicker.getValue();
            CivilStatus status = statusComboBox.getSelectionModel().getSelectedItem();

            debtor = new Debtor(DebtorDAO.getLastId() + 1,
                    firstname,
                    lastname,
                    middlename,
                    email,
                    phonenumber,
                    address,
                    gender,
                    birthdate,
                    status);
            app.getDeptorMasterList().add(debtor);
            DebtorDAO.insert(debtor);
            mainScene();
            clear();
        });
    }

    private void debtorInfo() {
        if (debtor != null) {   
            firstNameLabel.setText(debtor.getFirstName());
            middleNameLabel.setText(debtor.getMiddleName());
            lastNameLabel.setText(debtor.getLastName());
            genderLabel.setText(debtor.getGender().getValue());
            statusLabel.setText(debtor.getStatus().getValue());
            birthdateLabel.setText(debtor.getDateOfBirth().toString());
            emailLabel.setText(debtor.getEmail());
            phoneNumberLabel.setText(debtor.getPhoneNumber());
            addressLabel.setText(debtor.getAddress());
        } else {
            firstNameLabel.setText("null");
            middleNameLabel.setText("null");
            lastNameLabel.setText("null");
            genderLabel.setText("null");
            statusLabel.setText("null");
            birthdateLabel.setText("null");
            emailLabel.setText("null");
            phoneNumberLabel.setText("null");
            addressLabel.setText("null");

        }

    }

    private void debtorInfoScene() {
        mainVBox.setVisible(false);
        addDebtorVBox.setVisible(false);
        debtorInfoVBox.setVisible(true);
        addButton.setVisible(false);
        mainButton.setVisible(true);
    }

    private void addDebtorScene() {
        mainVBox.setVisible(false);
        addDebtorVBox.setVisible(true);
        debtorInfoVBox.setVisible(false);
        addButton.setVisible(false);
        mainButton.setVisible(true);
    }

    private void mainScene() {
        mainVBox.setVisible(true);
        addDebtorVBox.setVisible(false);
        debtorInfoVBox.setVisible(false);
        addButton.setVisible(true);
        mainButton.setVisible(true);
    }

    private void clear() {
        firstNameField.clear();
        lastNameField.clear();
        middleNameField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        emailField.clear();
        phoneNumberField.clear();
        birthdayPicker.setValue(null);
        statusComboBox.getSelectionModel().clearSelection();
        addressField.clear();
    }

}
