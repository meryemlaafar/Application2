package Service;

import dto.BankAccountRequestDTO;
import dto.BankAccountResponseDTO;
import entities.BankAccount;

public interface AccountService  {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO);
}
