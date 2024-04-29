package hybernate.service.impl;

import hybernate.dao.AccountDao;
import hybernate.dao.impl.AccountDaoImpl;
import hybernate.entity.Account;
import hybernate.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDaoImpl();
    @Override
    public Account saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    public String updateAccount(Long accountId, Account newAccountName) {
        return accountDao.updateAccount(accountId,newAccountName);
    }

    @Override
    public String deleteAccountById(Long accountId) {
        return accountDao.deleteAccountById(accountId);
    }

    @Override
    public String cleanAccount() {
        return accountDao.cleanAccount();
    }
}
