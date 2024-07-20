package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Bank;
import hillel.javapro.ewallet_dao.persistence.BankDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BankDAOImpl implements BankDAO {

    private static final Logger LOGGER = Logger.getLogger(BankDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.bank (name, mfo, address) VALUES (?, ?, ?);";
    private final static String UPDATE = "UPDATE ewalletdb.bank set name=?, mfo=?, address=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.bank where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.bank";
    private final static String DELETE = "DELETE * FROM ewalletdb.bank where id=?";

    @Override
    public Integer create(Bank bank) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,bank.getName());
        args.put(2,bank.getMfo());
        args.put(3,bank.getAddress());
        Integer id = DbStatements.insert(connection,INSERT, args);
        LOGGER.info("New id bank " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Bank bank) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,bank.getName());
        args.put(2,bank.getMfo());
        args.put(3,bank.getAddress());
        args.put(4,bank.getId());
        DbStatements.update(connection,UPDATE, args);
        LOGGER.info("Updated account with id=" + bank.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Bank bank) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, bank.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Bank get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,id);
        Bank bank = (Bank) DbStatements.select(connection,SELECT_BY_ID, args, Bank.class).getFirst();
        LOGGER.info("Select account with id=" + id);
        DbStatements.closeConnection(connection);
        return bank;
    }

    @Override
    public List<Bank> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        List<Bank> banks = (List<Bank>) DbStatements.select(connection,SELECT_ALL, args, Bank.class);
        DbStatements.closeConnection(connection);
        return banks;
    }
}
