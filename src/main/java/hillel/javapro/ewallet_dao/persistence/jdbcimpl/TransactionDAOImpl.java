package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Transaction;
import hillel.javapro.ewallet_dao.persistence.TransactionDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TransactionDAOImpl implements TransactionDAO {

    private static final Logger LOGGER = Logger.getLogger(TransactionDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.transaction (amount, date, description, iban_from, iban_to, account_id, operation_code_id) VALUES (?,?,?,?,?,?,?);";
    private final static String UPDATE = "UPDATE ewalletdb.transaction set amount=?, date=?, description=?, iban_from=?, iban_to=?, account_id=?, operation_code_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.transaction where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.transaction";
    private final static String DELETE = "DELETE * FROM ewalletdb.transaction where id=?";

    @Override
    public Integer create(Transaction transaction) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, transaction.getAmount());
        args.put(2, transaction.getDate());
        args.put(3, transaction.getDescription());
        args.put(4, transaction.getIbanFrom());
        args.put(5, transaction.getIbanTo());
        args.put(6, transaction.getAccount().getId());
        args.put(7, transaction.getOperationCode().getId());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id Transaction " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Transaction transaction) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, transaction.getAmount());
        args.put(2, transaction.getDate());
        args.put(3, transaction.getDescription());
        args.put(4, transaction.getIbanFrom());
        args.put(5, transaction.getIbanTo());
        args.put(6, transaction.getAccount().getId());
        args.put(7, transaction.getOperationCode().getId());
        args.put(8, transaction.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated Transaction with id=" + transaction.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Transaction transaction) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, transaction.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Transaction get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        Transaction transaction = (Transaction) DbStatements.select(connection, SELECT_BY_ID, args, Transaction.class).getFirst();
        LOGGER.info("Select Transaction with id=" + id);
        DbStatements.closeConnection(connection);
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<Transaction> transactions = (List<Transaction>) DbStatements.select(connection, SELECT_ALL, args, Transaction.class);
        DbStatements.closeConnection(connection);
        return transactions;
    }
}
