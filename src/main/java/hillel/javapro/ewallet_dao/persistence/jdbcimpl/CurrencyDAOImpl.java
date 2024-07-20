package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Currency;
import hillel.javapro.ewallet_dao.persistence.CurrencyDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CurrencyDAOImpl implements CurrencyDAO {

    private static final Logger LOGGER = Logger.getLogger(CurrencyDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.currency (code) VALUES (?);";
    private final static String UPDATE = "UPDATE ewalletdb.currency set code=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.currency where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.currency";
    private final static String DELETE = "DELETE * FROM ewalletdb.currency where id=?";

    @Override
    public Integer create(Currency currency) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,currency.getCode());
        Integer id = DbStatements.insert(connection,INSERT, args);
        LOGGER.info("New id Currency " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Currency currency) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,currency.getCode());
        args.put(2,currency.getId());
        DbStatements.update(connection,UPDATE, args);
        LOGGER.info("Updated currency with id=" + currency.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Currency currency) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, currency.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Currency get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,id);
        Currency currency = (Currency) DbStatements.select(connection,SELECT_BY_ID, args, Currency.class).getFirst();
        LOGGER.info("Select Currency with id=" + id);
        DbStatements.closeConnection(connection);
        return currency;
    }

    @Override
    public List<Currency> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        List<Currency> currencies = (List<Currency>) DbStatements.select(connection,SELECT_ALL, args, Currency.class);
        DbStatements.closeConnection(connection);
        return currencies;
    }
}
