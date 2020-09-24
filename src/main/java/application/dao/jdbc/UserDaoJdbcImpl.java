package application.dao.jdbc;

import application.dao.UserDao;
import application.exceptions.DataProcessingException;
import application.lib.Dao;
import application.model.Role;
import application.model.User;
import application.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        String query = "SELECT * FROM users WHERE deleted = FALSE AND login = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user with login " + login, e);
        }
        user.setRoles(getRoles(user.getId()));
        return Optional.of(user);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users(name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return insertRoles(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create user " + user, e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM users INNER JOIN users_roles ON id = user_id "
                + "WHERE deleted = FALSE AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user with id " + id, e);
        }
        user.setRoles(getRoles(user.getId()));
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users INNER JOIN users_roles ur ON id = user_id "
                + "WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get users from DB", e);
        }
        for (User user : users) {
            user.setRoles(getRoles(user.getId()));
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update user " + user, e);
        }
        deleteRoles(user.getId());
        insertRoles(user);
        user.setRoles(getRoles(user.getId()));
        return user;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete user with id " + id, e);
        }
    }

    private void deleteRoles(Long userId) {
        String query = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
            return;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete roles of user with id "
                    + userId, e);
        }
    }

    private Set<Role> getRoles(Long userId) {
        String query = "SELECT * FROM roles r INNER JOIN users_roles ur ON r.id = ur.role_id "
                + "WHERE ur.user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                Long roleId = resultSet.getLong("id");
                String roleName = resultSet.getString("name");
                Role role = Role.of(roleName);
                role.setId(roleId);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get roles of user with id "
                    + userId, e);
        }
    }

    private User insertRoles(User user) {
        String query = "INSERT INTO users_roles(user_id, role_id) "
                + "VALUES(?,(SELECT id FROM roles WHERE name = ?))";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            for (Role role : user.getRoles()) {
                statement.setLong(1, user.getId());
                statement.setString(2, role.getRoleName().name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update role of the user " + user, e);
        }
        user.setRoles(getRoles(user.getId()));
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        return new User(id, name, login, password);
    }
}
