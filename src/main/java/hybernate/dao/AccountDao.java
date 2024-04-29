package hybernate.dao;

import hybernate.entity.Account;

import java.util.List;

public interface AccountDao {
    Account saveAccount(Account account);

    Account findAccountById(Long accountId);

    List<Account> getAllAccounts();

    String updateAccount(Long accountId, Account newAccountName);

    String deleteAccountById(Long accountId);

    String cleanAccount();
}
