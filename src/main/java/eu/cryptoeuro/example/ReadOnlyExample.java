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
        // service = new HttpService("https://mainnet.infura.io/v3/");
        service = new HttpService();
        // service = new UnixIpcService("/Users/egon/Library/Ethereum/geth.ipc");
        Web3j web3j = Web3j.build(service);
        try {
            System.out.println(web3j.web3ClientVersion().send().getWeb3ClientVersion());

            Contracts contracts = Contracts.loadReadonly(web3j);

            String reserveBank = contracts.reserve.reserveBank().send();
            System.out.println(reserveBank);

            // TransactionReceipt txr = contracts.accounts.transfer("", BigInteger.valueOf(1500)).send();
        } finally {
            web3j.shutdown();
        }
    }
}
