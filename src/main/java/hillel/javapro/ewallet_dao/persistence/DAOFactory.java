package hillel.javapro.ewallet_dao.persistence;

import hillel.javapro.ewallet_dao.persistence.jdbcimpl.*;

public class DAOFactory {

    public static AccountDAO getAccountDAO() {
        return new AccountDAOImpl();
    }
    public static BankDAO getBankDAO() {
        return new BankDAOImpl();
    }
    public static BeneficiaryDAO getBeneficiaryDAO() {
        return new BeneficiaryDAOImpl();
    }
    public static CurrencyDAO getCurrencyDAO() {
        return new CurrencyDAOImpl();
    }
    public static CustomerDAO getCustomerDAO() {
        return new CustomerDAOImpl();
    }
    public static CustomerRoleDAO getCustomerRoleDAO() {
        return new CustomerRoleDAOImpl();
    }
    public static CustomerSessionDAO getCustomerSessionDAO() {
        return new CustomerSessionDAOImpl();
    }
    public static OperationCodeDAO getOperationCodeDAO() {
        return new OperationCodeDAOImpl();
    }
    public static TransactionDAO getTransactionDAO() {
        return new TransactionDAOImpl();
    }
    public static WalletDAO getWalletDAO() {
        return new WalletDAOImpl();
    }

}
