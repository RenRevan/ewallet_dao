package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.CustomerRole;
import hillel.javapro.ewallet_dao.persistence.CustomerRoleDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CustomerRoleDAOImpl implements CustomerRoleDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerRoleDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.customer_role (role) VALUES (?);";
    private final static String UPDATE = "UPDATE ewalletdb.customer_role set role=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.customer_role where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.customer_role";
    private final static String DELETE = "DELETE * FROM ewalletdb.customer_role where id=?";

    @Override
    public Integer create(CustomerRole customerRole) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerRole.getRole());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id CustomerRole " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(CustomerRole customerRole) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerRole.getRole());
        args.put(2, customerRole.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated CustomerRole with id=" + customerRole.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(CustomerRole customerRole) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerRole.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public CustomerRole get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        CustomerRole customerRole = (CustomerRole) DbStatements.select(connection, SELECT_BY_ID, args, CustomerRole.class).getFirst();
        LOGGER.info("Select CustomerRole with id=" + id);
        DbStatements.closeConnection(connection);
        return customerRole;
    }

    @Override
    public List<CustomerRole> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<CustomerRole> customerRoles = (List<CustomerRole>) DbStatements.select(connection, SELECT_ALL, args, CustomerRole.class);
        DbStatements.closeConnection(connection);
        return customerRoles;
    }
}
