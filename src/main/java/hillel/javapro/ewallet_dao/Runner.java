package hillel.javapro.ewallet_dao;

import hillel.javapro.ewallet_dao.entities.Account;
import hillel.javapro.ewallet_dao.entities.Bank;
import hillel.javapro.ewallet_dao.entities.Currency;
import hillel.javapro.ewallet_dao.entities.Wallet;
import hillel.javapro.ewallet_dao.persistence.AccountDAO;
import hillel.javapro.ewallet_dao.persistence.DAOFactory;
import hillel.javapro.ewallet_dao.persistence.jdbcimpl.AccountDAOImpl;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        //Examples of usage JDBC methods

        LOGGER.info("Insert new account");
        Account account = Account.builder()
                .iban("UA00111111000000000" + new Random().nextLong(1000000000L, 9999999999L))
                .bank(Bank.builder().id(1).build())
                .balance(new Random().nextInt())
                .wallet(Wallet.builder().id(1).build())
                .currency(Currency.builder().id(1).build())
                .build();

        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        Integer newAccountId = accountDAO.create(account);

        //select one account
        LOGGER.info("select one new account with id=" + newAccountId);
        Account account1 = accountDAO.get(newAccountId);
        LOGGER.info(account1.toString());

        //update account
        LOGGER.info("update account");
        account = account.toBuilder().id(2).balance(new Random().nextInt()).build();
        accountDAO.update(account);

        //select all accounts
        LOGGER.info("select all accounts");
        List<Account> accountsAll = accountDAO.getAll();
        accountsAll.forEach(acc -> LOGGER.info(acc.toString()));

    }
}
