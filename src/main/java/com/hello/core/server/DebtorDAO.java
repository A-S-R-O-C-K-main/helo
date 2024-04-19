package com.hello.core.server;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import javax.sql.rowset.CachedRowSet;

import com.hello.App;
import com.hello.core.model.Debtor;
import com.hello.core.model.for_enum.CivilStatus;
import com.hello.core.model.for_enum.Gender;
import com.hello.core.service.db.DBCommand;
import com.hello.core.service.db.DBParam;
import com.hello.core.service.db.DBService;
import com.hello.core.util.DateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtorDAO {
    private static final String TABLE = "debtor";
    private static final String DATABASE = "lend";

    public static int getLastId() {
        String sql = "SELECT MAX(debtorID) AS last_id FROM " + TABLE;
        CachedRowSet crs = DBService.QUERY(DATABASE, TABLE, sql, new DBParam[] {});

        try {
            if (crs.next())
                return crs.getInt("last_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public static ObservableList<Debtor> getMasterLsit(App app) throws SQLException {
        ObservableList<Debtor> list = FXCollections.observableArrayList();
        CachedRowSet crs = DBCommand.READ(DATABASE, TABLE, new DBParam[] {});

        while (crs.next()) {
            Debtor debtor = _data(crs, app);
            if (debtor != null) {
                list.add(debtor);
            }
        }

        return list;
    }

    public static void insert(Debtor debtor) {
        DBCommand.CREATE(DATABASE, TABLE, _params(debtor).toArray(new DBParam[] {}));
    }

    public static void remove(Debtor debtor) {
        DBCommand.DELETE(DATABASE, TABLE, new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()));
    }

    public static void update(Debtor debtor) {
        DBCommand.UPDATE(DATABASE, TABLE, new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()),
                _params(debtor).toArray(new DBParam[] {}));
    }

    private static Debtor _data(CachedRowSet crs, App app) throws SQLException {
        int debtorID = crs.getInt("debtorID");
        String firstName = crs.getString("firstName");
        String lastName = crs.getString("lastName");
        String middleName = crs.getString("middleName");
        String email = crs.getString("email");
        String phoneNumber = crs.getString("phoneNumber");
        String address = crs.getString("address");
        Gender gender = Gender.valueOf(crs.getString("gender"));
        LocalDate dateOfBirth = DateUtil.parse(crs.getString("dateOfBirth"));
        CivilStatus status = CivilStatus.valueOf(crs.getString("status"));

        return new Debtor(debtorID,
                firstName,
                lastName,
                middleName,
                email,
                phoneNumber,
                address,
                gender,
                dateOfBirth,
                status);
    }

    private static ObservableList<DBParam> _params(Debtor debtor) {
        ObservableList<DBParam> parameters = FXCollections.observableArrayList();
        parameters.add(new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()));
        parameters.add(new DBParam(Types.VARCHAR, "firstName", debtor.getFirstName()));
        parameters.add(new DBParam(Types.VARCHAR, "lastName", debtor.getLastName()));
        parameters.add(new DBParam(Types.VARCHAR, "middleName", debtor.getMiddleName()));
        parameters.add(new DBParam(Types.VARCHAR, "email", debtor.getEmail()));
        parameters.add(new DBParam(Types.VARCHAR, "phoneNumber", debtor.getPhoneNumber()));
        parameters.add(new DBParam(Types.VARCHAR, "address", debtor.getAddress()));
        parameters.add(new DBParam(Types.VARCHAR, "gender", debtor.getGender()));
        parameters.add(new DBParam(Types.VARCHAR, "dateOfBirth", DateUtil.format(debtor.getDateOfBirth())));
        parameters.add(new DBParam(Types.VARCHAR, "status", debtor.getStatus()));

        return parameters;
    }
}
