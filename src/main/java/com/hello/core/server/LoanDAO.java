package com.hello.core.server;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import javax.sql.rowset.CachedRowSet;

import com.hello.App;
import com.hello.core.model.Loan;
import com.hello.core.service.db.DBCommand;
import com.hello.core.service.db.DBParam;
import com.hello.core.service.db.DBService;
import com.hello.core.util.DateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanDAO {
    private static final String TABLE = "loan";
    private static final String DATABASE = "lend";

    public static int getLastId() {
        String sql = "SELECT MAX(loanID) AS last_id FROM " + TABLE;
        CachedRowSet crs = DBService.QUERY(DATABASE, TABLE, sql, new DBParam[] {});

        try {
            if (crs.next())
                return crs.getInt("last_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public static ObservableList<Loan> getMasterLsit(App app) throws SQLException {
        ObservableList<Loan> list = FXCollections.observableArrayList();
        CachedRowSet crs = DBCommand.READ(DATABASE, TABLE, new DBParam[] {});

        while (crs.next()) {
            Loan loan = _data(crs, app);
            if (loan != null) {
                list.add(loan);
            }
        }

        return list;
    }

    public static void insert(Loan loan) {
        DBCommand.CREATE(DATABASE, TABLE, _params(loan).toArray(new DBParam[] {}));
    }

    public static void remove(Loan loan) {
        DBCommand.DELETE(DATABASE, TABLE, new DBParam(Types.INTEGER, "loanID", loan.getLoanID()));
    }

    public static void update(Loan loan) {
        DBCommand.UPDATE(DATABASE, TABLE, new DBParam(Types.INTEGER, "loanID", loan.getLoanID()),
                _params(loan).toArray(new DBParam[] {}));
    }

    private static Loan _data(CachedRowSet crs, App app) throws SQLException {
        int loanID = crs.getInt("loanID");
        LocalDate dateOfLoan = DateUtil.parse(crs.getString("dateOfLoan"));
        int amount = crs.getInt("amount");
        return new Loan(loanID, dateOfLoan, amount);
    }

    private static ObservableList<DBParam> _params(Loan loan) {
        ObservableList<DBParam> parameters = FXCollections.observableArrayList();
        parameters.add(new DBParam(Types.INTEGER, "loanID", loan.getLoanID()));
        parameters.add(new DBParam(Types.VARCHAR, "dateOfLoan", loan.getDateOfLoan()));
        parameters.add(new DBParam(Types.INTEGER, "amount", loan.getAmount()));

        return parameters;
    }
}
