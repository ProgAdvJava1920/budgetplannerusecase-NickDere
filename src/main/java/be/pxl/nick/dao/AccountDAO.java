package be.pxl.nick.dao;

import be.pxl.nick.entity.Account;

import java.sql.*;

public class AccountDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM contacts WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM contacts WHERE name = ?";
    private static final String UPDATE = "UPDATE contacts SET name=?, phone=?, email=? WHERE id = ?";
    private static final String INSERT = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM contacts WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public AccountDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public Account createAccount(Account account) {

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contact.getName());
            stmt.setInt(2, contact.getPhone());
            stmt.setString(3, contact.getEmail());
            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        contact.setId(rs.getInt(1));
                        return contact;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;


    }

    public boolean updateContact(Account account) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getIBAN());
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(long id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setLong(4, id);
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Account readAccount(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setPhone(rs.getInt("phone"));
        account.setEmail(rs.getString("email"));
        return account;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }
}
