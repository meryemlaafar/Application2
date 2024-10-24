package web;

import Mappers.AccountMapper;
import Service.AccountService;
import dto.BankAccountRequestDTO;
import dto.BankAccountResponseDTO;
import entities.BankAccount;
import org.springframework.web.bind.annotation.*;
import repositories.BankAccountRepository;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        this.accountService = accountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null)
            account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAT()!=null)
                account.setCreatedAT(new Date());
        if(bankAccount.getType()!=null)
            account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null)
            account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
}
    @DeleteMapping("/bankAccounts/{id}")
public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
