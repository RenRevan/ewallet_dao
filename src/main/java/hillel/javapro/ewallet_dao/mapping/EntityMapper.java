package hillel.javapro.ewallet_dao.mapping;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {

    public static <T> T getEntityFromResultSet(Class entityClass, ResultSet resultSet) throws SQLException {
        switch (entityClass.getSimpleName()) {
            case "Account": {
                return (T) Account.builder()
                        .id(resultSet.getInt(1))
                        .iban(resultSet.getString(2))
                        .balance(resultSet.getInt(3))
                        .wallet(Wallet.builder().id(resultSet.getInt(4)).build())
                        .bank(Bank.builder().id(resultSet.getInt(5)).build())
                        .currency(Currency.builder().id(resultSet.getInt(6)).build())
                        .build();
            }
            case "Bank": {
                return (T) Bank.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .mfo(resultSet.getString(3))
                        .address(resultSet.getString(4))
                        .build();
            }
            case "Beneficiary": {
                return (T) Beneficiary.builder()
                        .id(resultSet.getInt(1))
                        .iban(resultSet.getString(2))
                        .bankName(resultSet.getString(3))
                        .mfo(resultSet.getInt(4))
                        .fullName(resultSet.getString(5))
                        .wallet(Wallet.builder().id(resultSet.getInt(6)).build())
                        .build();
            }
            case "Currency": {
                return (T) Currency.builder()
                        .id(resultSet.getInt(1))
                        .code(resultSet.getString(2))
                        .build();
            }
            case "Customer": {
                return (T) Customer.builder()
                        .id(resultSet.getInt(1))
                        .mobileNumber(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .firstName(resultSet.getString(4))
                        .lastName(resultSet.getString(5))
                        .password(resultSet.getString(6))
                        .customerRole(CustomerRole.builder().id(resultSet.getInt(7)).build())
                        .build();
            }
            case "CustomerRole": {
                return (T) CustomerRole.builder()
                        .id(resultSet.getInt(1))
                        .role(resultSet.getString(2))
                        .build();
            }
            case "CustomerSession": {
                return (T) CustomerSession.builder()
                        .id(resultSet.getInt(1))
                        .sessionDatetime(resultSet.getTimestamp(2).toInstant())
                        .browser(resultSet.getString(3))
                        .customer(Customer.builder().id(resultSet.getInt(4)).build())
                        .build();
            }
            case "OperationCode": {
                return (T) OperationCode.builder()
                        .id(resultSet.getInt(1))
                        .code(resultSet.getString(2))
                        .build();
            }
            case "Transaction": {
                return (T) Transaction.builder()
                        .id(resultSet.getInt(1))
                        .amount(resultSet.getInt(2))
                        .date(resultSet.getTimestamp(3).toInstant())
                        .description(resultSet.getString(4))
                        .ibanFrom(resultSet.getString(5))
                        .ibanTo(resultSet.getString(6))
                        .account(Account.builder().id(resultSet.getInt(7)).build())
                        .operationCode(OperationCode.builder().id(resultSet.getInt(8)).build())
                        .build();
            }
            case "Wallet": {
                return (T) Wallet.builder()
                        .id(resultSet.getInt(1))
                        .customer(Customer.builder().id(resultSet.getInt(2)).build())
                        .build();
            }
            default:
                throw  new RuntimeException("Object not described");
        }
    }

}
