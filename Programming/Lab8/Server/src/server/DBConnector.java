package server;

import moominClasses.Field;
import moominClasses.Moomin;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class DBConnector {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:9080/studs";
    private static Connection dbConnection;

    public DBConnector() {
        try {
            Class.forName(DB_DRIVER);
            String login = "s264448";
            String password = "eli307";
            dbConnection = DriverManager.getConnection(DB_CONNECTION, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean newUser(String username, String email, String password) {
        try {
            ResultSet rs = dbRequest("SELECT user_name, email FROM users");
            while (rs.next()) {
                if (rs.getString(1).equals(username) || rs.getString(2).equals(email)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO users(user_name, password, email) VALUES ";
        try (Statement stmt = dbConnection.createStatement()){
            stmt.executeUpdate(query + "('" + username + "', '" + PasswordEncryptor.encrypt(password) + "', '" + email + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ResultSet dbRequest(String request) throws SQLException {
        Statement stmt = dbConnection.createStatement();
        ResultSet res = stmt.executeQuery(request);
        return res;
    }

    public static boolean checkPassword(String username, String password) {
        boolean res = false;

        String encryptedPassword = PasswordEncryptor.encrypt(password);

        try {
            ResultSet rs = dbRequest("SELECT password FROM users WHERE user_name='" + username + "'");
            while (rs.next()) {
                if (rs.getString(1).equals(encryptedPassword)) {
                    res = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static int getUserID(String username) {
        int res = -1;

        try {
            ResultSet rs = dbRequest("SELECT user_id FROM users WHERE user_name='" + username + "'");
            while (rs.next()) {
                res = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static boolean addAllMooomins(Vector<Moomin> moomins, int userID) {
        String query = "INSERT INTO moomins(owner_id, creation_time, moomin_name, moomin_field, moomin_type, " +
                "moomin_gender, moomin_position, moomin_condition, x, y) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean flag = true;
        for (int i = 0; i < moomins.size(); i++) {
            Moomin moomin = moomins.get(i);
            try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
                stmt.setInt(1, userID);
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(moomin.getCreationTime().toLocalDateTime()));
                stmt.setString(3, moomin.getName());
                stmt.setString(4, moomin.getField().toString());
                stmt.setString(5, moomin.getType().toString());
                stmt.setString(6, moomin.getGender().toString());
                stmt.setString(7, moomin.getPosition().toString());
                stmt.setString(8, moomin.getCondition().toString());
                stmt.setDouble(9, moomin.getX());
                stmt.setDouble(10, moomin.getY());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

    public static Vector<Moomin> getMoomins() {
        Vector<Moomin> res = new Vector<>();

        try {
            ResultSet rs = dbRequest("SELECT * FROM moomins");
            while (rs.next()) {
                int ownerId = rs.getInt(1);
                ZonedDateTime time = ZonedDateTime.of(rs.getTimestamp(2).toLocalDateTime(), ZoneId.of("Europe/Moscow"));
                String name = rs.getString(3);
                Field field = new Field(rs.getString(4));
                Moomin.TypeOfMoomin type = Moomin.TypeOfMoomin.strToType(rs.getString(5));
                Moomin.Gender gender = Moomin.Gender.strToGender(rs.getString(6));
                Moomin.Position position = Moomin.Position.strToPosition(rs.getString(7));
                Moomin.Condition condition = Moomin.Condition.strToCondition(rs.getString(8));
                double X = rs.getDouble("x");
                double Y = rs.getDouble("y");

                res.add(new Moomin(time, name, field, type, gender, position, condition, X, Y, ownerId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static int getCountOfMoomins() {
        int count = 0;
        try {
            ResultSet res = dbRequest("SELECT COUNT(*) FROM moomins");
            while (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static void clearMoomins(int userID) {
        String query = "DELETE FROM moomins WHERE owner_id=" + userID;
        try (Statement stmt = dbConnection.createStatement()){
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean remove(Moomin moomin, int userID) {
        String query = "DELETE FROM moomins WHERE owner_id=" + userID + " AND moomin_name='" + moomin.getName() + "'";
        try (Statement stmt = dbConnection.createStatement()){
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeLowerest(Moomin moomin, int userID) {
        String query = "DELETE FROM moomins WHERE owner_id=" + userID + " AND moomin_name<='" + moomin.getName() + "'";
        try (Statement stmt = dbConnection.createStatement()){
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
