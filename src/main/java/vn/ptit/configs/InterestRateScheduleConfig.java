package vn.ptit.configs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.DepositAccountWithDateCreate;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.DepositAccountService;

@Configuration
@EnableScheduling
public class InterestRateScheduleConfig {
	@Autowired
	DepositAccountService depositAccountService;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	DepositAccountRepository depositAccountRepository;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Scheduled(cron = "0 30 23 * * ?")
	public void checkInterestRateSchedule() {
		Date date = new Date();

		List<DepositAccountWithDateCreate> depositAccountWithDateCreates = depositAccountService
				.findAllWithStatusTrueAndDateCreate();

		for (DepositAccountWithDateCreate depositAccountWithDateCreate : depositAccountWithDateCreates) {
			long mili = date.getTime() - depositAccountWithDateCreate.getDateCreate().getTime();
			long day = TimeUnit.MILLISECONDS.toDays(mili);
			if (day > 0 && day % 30 == 0) {
				DepositAccount depositAccount = depositAccountService
						.findByIdAndStatusTrue(depositAccountWithDateCreate.getId());
				double afterBalanceDeposit = depositAccount.getBalance()
						+ depositAccount.getBalance() * depositAccount.getInterestRate() / 100;
				Transaction transaction = new Transaction();
				transaction.setDepositAccount(depositAccount);
				transaction.setMoney(depositAccount.getBalance() * depositAccount.getInterestRate() / 100);
				transaction.setType("INTEREST");
				transaction.setNote("Cộng tiền lãi suất hàng tháng");
				transaction.setDateCreate(new Date());
				transaction.setAfterBalanceDeposit(afterBalanceDeposit);
				depositAccount.setBalance(afterBalanceDeposit);
				transactionRepository.save(transaction);
				depositAccountRepository.save(depositAccount);
				System.out.println("Cộng tiền lãi suất hàng tháng tài khoản: " + depositAccount.getId());
			}
		}
	}
}
