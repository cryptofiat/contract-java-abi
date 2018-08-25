package eu.cryptoeuro.contract;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;

public class Contracts {
    public static String RootAddress = "0xa10a263D4336E4466502b2889D27D04582a86663";
    public static BigInteger GasPrice = BigInteger.valueOf(1); //TODO:
    public static BigInteger GasLimit = BigInteger.valueOf(400000); //TODO:

    public Web3j web3j;
    public Credentials credentials;
    public TransactionManager transactionManager;

    public CryptoFiat root;

    public Data data;
    public Accounts accounts;
    public Approving approving;
    public Reserve reserve;
    public Enforcement enforcement;
    public AccountRecovery accountRecovery;
    public Delegation delegation;


    public static Contracts load(Web3j web3j, Credentials credentials) throws Exception {
        Contracts contracts = new Contracts();
        contracts.web3j = web3j;
        contracts.credentials = credentials;

        contracts.initContracts();
        return contracts;
    }

    public static Contracts loadReadonly(Web3j web3j) throws Exception {
        Contracts contracts = new Contracts();
        contracts.web3j = web3j;
        contracts.transactionManager = new ReadonlyTransactionManager(web3j, RootAddress);

        contracts.initContractsReadOnly();
        return contracts;
    }

    private void initContracts() throws Exception {
        this.root = CryptoFiat.load(RootAddress, web3j, credentials, GasPrice, GasLimit);

        this.data = Data.load(addressOf(Index.Data), web3j, credentials, GasPrice, GasLimit);
        this.accounts = Accounts.load(addressOf(Index.Accounts), web3j, credentials, GasPrice, GasLimit);
        this.approving = Approving.load(addressOf(Index.Approving), web3j, credentials, GasPrice, GasLimit);
        this.reserve = Reserve.load(addressOf(Index.Reserve), web3j, credentials, GasPrice, GasLimit);
        this.enforcement = Enforcement.load(addressOf(Index.Enforcement), web3j, credentials, GasPrice, GasLimit);
        this.accountRecovery = AccountRecovery.load(addressOf(Index.AccountRecovery), web3j, credentials, GasPrice, GasLimit);
        this.delegation = Delegation.load(addressOf(Index.Delegation), web3j, credentials, GasPrice, GasLimit);
    }

    private void initContractsReadOnly() throws Exception {
        this.root = CryptoFiat.load(RootAddress, web3j, transactionManager, GasPrice, GasLimit);

        this.data = Data.load(addressOf(Index.Data), web3j, transactionManager, GasPrice, GasLimit);
        this.accounts = Accounts.load(addressOf(Index.Accounts), web3j, transactionManager, GasPrice, GasLimit);
        this.approving = Approving.load(addressOf(Index.Approving), web3j, transactionManager, GasPrice, GasLimit);
        this.reserve = Reserve.load(addressOf(Index.Reserve), web3j, transactionManager, GasPrice, GasLimit);
        this.enforcement = Enforcement.load(addressOf(Index.Enforcement), web3j, transactionManager, GasPrice, GasLimit);
        this.accountRecovery = AccountRecovery.load(addressOf(Index.AccountRecovery), web3j, transactionManager, GasPrice, GasLimit);
        this.delegation = Delegation.load(addressOf(Index.Delegation), web3j, transactionManager, GasPrice, GasLimit);
    }

    private String addressOf(int v) throws Exception {
        return root.contractAddress(BigInteger.valueOf(v)).send();
    }

    static class Index {
        static int Data = 1;
        static int Accounts = 2;
        static int Approving = 3;
        static int Reserve = 4;
        static int Enforcement = 5;
        static int AccountRecovery = 6;
        static int Delegation = 7;
    }
}