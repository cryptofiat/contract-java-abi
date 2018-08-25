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
import org.web3j.tx.*;

import java.math.BigInteger;

public class ReadOnlyExample {
    public static void main() throws Exception {
        Contracts contracts = Contracts.httpReadOnly();

        String reserveBank = contracts.reserve.reserveBank().send();
        System.out.println(reserveBank);

        // TransactionReceipt txr = contracts.accounts.transfer("", BigInteger.valueOf(1500)).send();
    }

}
