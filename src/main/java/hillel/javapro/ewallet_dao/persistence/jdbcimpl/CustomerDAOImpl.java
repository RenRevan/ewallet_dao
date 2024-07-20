package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Customer;
import hillel.javapro.ewallet_dao.persistence.CustomerDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CustomerDAOImpl implements CustomerDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.customer (mobile_number, email, first_name, last_name, password, customer_role_id) VALUES (?,?,?,?,?,?);";
    private final static String UPDATE = "UPDATE ewalletdb.customer set mobile_number=?, email=?, first_name=?, last_name=?, password=?, customer_role_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.customer where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.customer";
    private final static String DELETE = "DELETE * FROM ewalletdb.customer where id=?";

    @Override
    public Integer create(Customer customer) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customer.getMobileNumber());
        args.put(2, customer.getEmail());
        args.put(3, customer.getFirstName());
        args.put(4, customer.getLastName());
        args.put(5, customer.getPassword());
        args.put(6, customer.getCustomerRole().getId());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id Customer " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Customer customer) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customer.getMobileNumber());
        args.put(2, customer.getEmail());
        args.put(3, customer.getFirstName());
        args.put(4, customer.getLastName());
        args.put(5, customer.getPassword());
        args.put(6, customer.getCustomerRole().getId());
        args.put(7, customer.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated customer with id=" + customer.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Customer customer) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, bank.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Customer get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        Customer customer = (Customer) DbStatements.select(connection, SELECT_BY_ID, args, Customer.class).getFirst();
        LOGGER.info("Select Customer with id=" + id);
        DbStatements.closeConnection(connection);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<Customer> customers = (List<Customer>) DbStatements.select(connection, SELECT_ALL, args, Customer.class);
        DbStatements.closeConnection(connection);
        return customers;
    }
}
