package hillel.javapro.ewallet_dao.persistence.jdbcimpl;

import hillel.javapro.ewallet_dao.connection.DbStatements;
import hillel.javapro.ewallet_dao.entities.OperationCode;
import hillel.javapro.ewallet_dao.persistence.OperationCodeDAO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class OperationCodeDAOImpl implements OperationCodeDAO {

    private static final Logger LOGGER = Logger.getLogger(OperationCodeDAOImpl.class.getName());

    private final static String INSERT = "INSERT INTO ewalletdb.operation_code (code) VALUES (?);";
    private final static String UPDATE = "UPDATE ewalletdb.operation_code set code=? where id=?";
    private final static String SELECT_BY_ID = "SELECT * FROM ewalletdb.operation_code where id=?";
    private final static String SELECT_ALL = "SELECT * FROM ewalletdb.operation_code";
    private final static String DELETE = "DELETE * FROM ewalletdb.operation_code where id=?";

    @Override
    public Integer create(OperationCode operationCode) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, operationCode.getCode());
        Integer id = DbStatements.insert(connection, INSERT, args);
        LOGGER.info("New id OperationCode " + id);
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
        return id;
    }

    @Override
    public void update(OperationCode operationCode) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, operationCode.getCode());
        args.put(2, operationCode.getId());
        DbStatements.update(connection, UPDATE, args);
        LOGGER.info("Updated OperationCode with id=" + operationCode.getId());
        DbStatements.commit(connection);
        DbStatements.closeConnection(connection);
    }

    @Override
    public void delete(OperationCode operationCode) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, operationCode.getId());
        DbStatements.delete(connection, DELETE, args);
        DbStatements.closeConnection(connection);
    }

    @Override
    public OperationCode get(int id) {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        args.put(1, id);
        OperationCode operationCode = (OperationCode) DbStatements.select(connection, SELECT_BY_ID, args, OperationCode.class).getFirst();
        LOGGER.info("Select OperationCode with id=" + id);
        DbStatements.closeConnection(connection);
        return operationCode;
    }

    @Override
    public List<OperationCode> getAll() {
        Connection connection = DbStatements.createConnect();
        Map<Integer, Object> args = new HashMap<>();
        List<OperationCode> operationCodes = (List<OperationCode>) DbStatements.select(connection, SELECT_ALL, args, OperationCode.class);
        DbStatements.closeConnection(connection);
        return operationCodes;
    }
}
