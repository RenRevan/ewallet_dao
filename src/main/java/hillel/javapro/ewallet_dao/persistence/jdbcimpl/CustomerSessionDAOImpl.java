package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.CustomerSession;
import hillel.javapro.ewallet_dao.persistence.CustomerSessionDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CustomerSessionDAOImpl implements CustomerSessionDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerSessionDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.customer_session (session_datetime, browser, customer_id) VALUES (?, ?, ?);";
    private final static String UPDATE = "UPDATE ewalletdb.customer_session set session_datetime=?, browser=?, customer_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.customer_session where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.customer_session";
    private final static String DELETE = "DELETE * FROM ewalletdb.customer_session where id=?";

    @Override
    public Integer create(CustomerSession customerSession) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerSession.getSessionDatetime());
        args.put(2, customerSession.getBrowser());
        args.put(3, customerSession.getCustomer().getId());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id CustomerSession " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(CustomerSession customerSession) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerSession.getSessionDatetime());
        args.put(2, customerSession.getBrowser());
        args.put(3, customerSession.getCustomer().getId());
        args.put(4, customerSession.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated CustomerSession with id=" + customerSession.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(CustomerSession customerSession) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, customerSession.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public CustomerSession get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        CustomerSession customerSession = (CustomerSession) DbStatements.select(connection, SELECT_BY_ID, args, CustomerSession.class).getFirst();
        LOGGER.info("Select CustomerSession with id=" + id);
        DbStatements.closeConnection(connection);
        return customerSession;
    }

    @Override
    public List<CustomerSession> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<CustomerSession> customerSessions = (List<CustomerSession>) DbStatements.select(connection, SELECT_ALL, args, CustomerSession.class);
        DbStatements.closeConnection(connection);
        return customerSessions;
    }
}
