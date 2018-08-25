package eu.cryptoeuro.contract;

import eu.cryptoeuro.contract.internal.HookedRawTransactionManager;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;

import java.io.IOException;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class Contracts {
    public static String RootAddress = "0xa10a263D4336E4466502b2889D27D04582a86663";
    public static BigInteger GasPrice = BigInteger.valueOf(1); //TODO:
    public static BigInteger GasLimit = BigInteger.valueOf(400000); //TODO:

    public Web3j web3j;
    public Credentials credentials;
    public HookedRawTransactionManager transactionManager;

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
        contracts.transactionManager = new HookedRawTransactionManager(web3j, credentials);

        contracts.initContracts(contracts.transactionManager);
        return contracts;
    }

    public static Contracts loadReadonly(Web3j web3j) throws Exception {
        Contracts contracts = new Contracts();
        contracts.web3j = web3j;

        contracts.initContracts(new ReadonlyTransactionManager(web3j, RootAddress));
        return contracts;
    }

    private void initContracts(TransactionManager txm) throws Exception {
        this.root = CryptoFiat.load(RootAddress, web3j, txm, GasPrice, GasLimit);

        this.data = Data.load(addressOf(Index.Data), web3j, txm, GasPrice, GasLimit);
        this.accounts = Accounts.load(addressOf(Index.Accounts), web3j, txm, GasPrice, GasLimit);
        this.approving = Approving.load(addressOf(Index.Approving), web3j, txm, GasPrice, GasLimit);
        this.reserve = Reserve.load(addressOf(Index.Reserve), web3j, txm, GasPrice, GasLimit);
        this.enforcement = Enforcement.load(addressOf(Index.Enforcement), web3j, txm, GasPrice, GasLimit);
        this.accountRecovery = AccountRecovery.load(addressOf(Index.AccountRecovery), web3j, txm, GasPrice, GasLimit);
        this.delegation = Delegation.load(addressOf(Index.Delegation), web3j, txm, GasPrice, GasLimit);
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