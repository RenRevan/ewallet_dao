package hillel.javapro.ewallet_dao.connection;

import hillel.javapro.ewallet_dao.mapping.EntityMapper;
import hillel.javapro.ewallet_dao.utils.ProjProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DbStatements {

    private static Properties prop = ProjProperties.getDbProperties();

    public static Connection createConnect() {
        return DbConnection.createConnect(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
    }

    public static void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can`t close connection. " + e);
        }
    }

    public static <T> Integer insert(Connection connection, String sql, Map<Integer, T> args) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= args.size(); i++) {
                statement.setObject(i, args.get(i));
            }
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static <T> void update(Connection connection, String sql, Map<Integer, T> args) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= args.size(); i++) {
                statement.setObject(i, args.get(i));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<?> select(Connection connection, String sql, Map<Integer, T> args, Class entityClass) {
        List<?> entitiesList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= args.size(); i++) {
                statement.setObject(i, args.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entitiesList.add(EntityMapper.getEntityFromResultSet(entityClass, resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entitiesList;
    }

    public static <T> void delete(Connection connection, String sql, Map<Integer, T> args) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= args.size(); i++) {
                statement.setObject(i, args.get(i));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
