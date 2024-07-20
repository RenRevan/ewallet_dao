package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Wallet;
import hillel.javapro.ewallet_dao.persistence.WalletDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WalletDAOImpl implements WalletDAO {

    private static final Logger LOGGER = Logger.getLogger(WalletDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.wallet (customer_id) VALUES (?);";
    private final static String UPDATE = "UPDATE ewalletdb.wallet set customer_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.wallet where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.wallet";
    private final static String DELETE = "DELETE * FROM ewalletdb.wallet where id=?";

    @Override
    public Integer create(Wallet wallet) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, wallet.getCustomer().getId());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id Wallet " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Wallet wallet) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, wallet.getCustomer().getId());
        args.put(2, wallet.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated Wallet with id=" + wallet.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Wallet wallet) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, wallet.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Wallet get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        Wallet wallet = (Wallet) DbStatements.select(connection, SELECT_BY_ID, args, Wallet.class).getFirst();
        LOGGER.info("Select Transaction with id=" + id);
        DbStatements.closeConnection(connection);
        return wallet;
    }

    @Override
    public List<Wallet> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<Wallet> wallets = (List<Wallet>) DbStatements.select(connection, SELECT_ALL, args, Wallet.class);
        DbStatements.closeConnection(connection);
        return wallets;
    }
}
