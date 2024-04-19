package com.hello;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import com.hello.core.model.Debtor;
import com.hello.core.model.Loan;
import com.hello.core.server.DebtorDAO;
import com.hello.core.server.LoanDAO;
import com.hello.core.service.ui.Content;

public class App extends Application {

    private Stage applicationStage;
    private StackPane applicationRoot;
    private StackPane stackPaneRoot;

    private ObservableList<Debtor> deptorMasterList;
    private ObservableList<Loan> loanMasterList;


    @Override
    public void start(Stage stage) throws Exception {
        this.applicationStage = stage;
        _load_resources();
        _initialize();
    }

    private void _initialize() throws IOException {
        Content.load_MAIN(this);
    }
    private void _load_resources() throws SQLException {
        deptorMasterList = DebtorDAO.getMasterLsit(this);
        loanMasterList = LoanDAO.getMasterLsit(this);
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public StackPane getApplicationRoot() {
        return applicationRoot;
    }

    public StackPane getstackPaneRoot() {
        return stackPaneRoot;
    }

    public void setApplicationRoot(StackPane applicationRoot) {
        this.applicationRoot = applicationRoot;
    }

    public void setstackPaneRoot(StackPane applicationRoot) {
        this.stackPaneRoot = applicationRoot;
    }

    public ObservableList<Debtor> getDeptorMasterList() {
        return deptorMasterList;
    }
    public ObservableList<Loan> getLoanMasterList() {
        return loanMasterList;
    }

    public static void main(String[] args) {
        launch(args);
    }

}