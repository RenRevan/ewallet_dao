package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.Beneficiary;
import hillel.javapro.ewallet_dao.persistence.BeneficiaryDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {

    private static final Logger LOGGER = Logger.getLogger(BeneficiaryDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.beneficiary (iban, bank_name, mfo, full_name, wallet_id) VALUES (?, ?, ?, ?, ?);";
    private final static String UPDATE = "UPDATE ewalletdb.beneficiary set iban=?, bank_name=?, mfo=?, full_name=?, wallet_id=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.beneficiary where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.beneficiary";
    private final static String DELETE = "DELETE * FROM ewalletdb.beneficiary where id=?";

    @Override
    public Integer create(Beneficiary beneficiary) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,beneficiary.getIban());
        args.put(2,beneficiary.getBankName());
        args.put(3,beneficiary.getMfo());
        args.put(4,beneficiary.getFullName());
        args.put(5,beneficiary.getWallet().getId());
        Integer id = DbStatements.insert(connection,INSERT, args);
        LOGGER.info("New id beneficiary " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(Beneficiary beneficiary) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,beneficiary.getIban());
        args.put(2,beneficiary.getBankName());
        args.put(3,beneficiary.getMfo());
        args.put(4,beneficiary.getFullName());
        args.put(5,beneficiary.getWallet().getId());
        args.put(6,beneficiary.getId());
        DbStatements.update(connection,UPDATE, args);
        LOGGER.info("Updated beneficiary with id=" + beneficiary.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(Beneficiary beneficiary) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, beneficiary.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public Beneficiary get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        args.put(1,id);
        Beneficiary beneficiary = (Beneficiary) DbStatements.select(connection,SELECT_BY_ID, args, Beneficiary.class).getFirst();
        LOGGER.info("Select Beneficiary with id=" + id);
        DbStatements.closeConnection(connection);
        return beneficiary;
    }

    @Override
    public List<Beneficiary> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer,Object> args = new HashMap<>();
        List<Beneficiary> beneficiaries = (List<Beneficiary>) DbStatements.select(connection,SELECT_ALL, args, Beneficiary.class);
        DbStatements.closeConnection(connection);
        return beneficiaries;
    }
}
