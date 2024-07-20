package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Account;
import hillel.javapro.ewallet_dao.persistence.AccountDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AccountDAOImpl implements AccountDAO {

    private static final Logger LOGGER = Logger.getLogger(AccountDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.account (iban, balance, wallet_id, bank_id, currency_id) VALUES (?, ?, ?, ?, ?);";
    private final static String UPDATE = "UPDATE ewalletdb.account set iban=?, balance=?, wallet_id=?, bank_id=?, currency_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.account where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.account";
    private final static String DELETE = "DELETE * FROM ewalletdb.account where id=?";

    @Override
    public Integer create(Account account) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, account.getIban());
        args.put(2, account.getBalance());
        args.put(3, account.getWallet().getId());
        args.put(4, account.getBank().getId());
        args.put(5, account.getCurrency().getId());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id account " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Account account) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, account.getIban());
        args.put(2, account.getBalance());
        args.put(3, account.getWallet().getId());
        args.put(4, account.getBank().getId());
        args.put(5, account.getCurrency().getId());
        args.put(6, account.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated account with id=" + account.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Account account) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, account.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Account get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        Account account = (Account) DbStatements.select(connection, SELECT_BY_ID, args, Account.class).getFirst();
        LOGGER.info("Select account with id=" + id);
        DbStatements.closeConnection(connection);
        return account;
    }

    @Override
    public List<Account> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<Account> account = (List<Account>) DbStatements.select(connection, SELECT_ALL, args, Account.class);
        DbStatements.closeConnection(connection);
        return account;
    }
}
