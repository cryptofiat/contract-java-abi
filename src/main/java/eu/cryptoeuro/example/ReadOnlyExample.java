package eu.cryptoeuro.example;

import eu.cryptoeuro.contract.*;

import org.web3j.abi.*;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.*;
import org.web3j.crypto.*;
import org.web3j.protocol.*;
import org.web3j.protocol.http.*;
import org.web3j.protocol.core.*;
import org.web3j.protocol.core.methods.request.*;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.ipc.*;
import org.web3j.tx.*;

import java.math.BigInteger;

public class ReadOnlyExample {

    public static void main(String[] args) throws Exception {
        Web3jService service;
        // service = new HttpService("https://mainnet.infura.io/");
        service = new HttpService();
        Web3j web3j = Web3j.build(service);
        try {
            System.out.println(web3j.web3ClientVersion().send().getWeb3ClientVersion());

            TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, null);
            CryptoFiat root = CryptoFiat.load("0xa10a263D4336E4466502b2889D27D04582a86663", web3j, transactionManager, BigInteger.valueOf(1), BigInteger.valueOf(1));
            BigInteger contractsLength = root.contractsLength().send();

            System.out.println(contractsLength);

            //Contracts contracts = Contracts.loadReadonly(web3j);

            //String reserveBank = contracts.reserve.reserveBank().send();
            //System.out.println(reserveBank);

            // TransactionReceipt txr = contracts.accounts.transfer("", BigInteger.valueOf(1500)).send();
        } finally {
            web3j.shutdown();
        }
    }
}
