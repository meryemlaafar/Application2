package Service;

import Mappers.AccountMapper;
import dto.BankAccountRequestDTO;
import dto.BankAccountResponseDTO;
import entities.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.BankAccountRepository;

import java.util.Date;
import java.util.UUID;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO){
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAT(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO){
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createdAT(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
