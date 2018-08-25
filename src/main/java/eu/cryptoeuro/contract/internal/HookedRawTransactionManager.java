package eu.cryptoeuro.contract.internal;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.RawTransactionManager;

import java.io.IOException;
import java.math.BigInteger;
import java.util.function.Consumer;

public class HookedRawTransactionManager extends RawTransactionManager {
    public Consumer<EthSendTransaction> onceBeforeWait = null;

    public HookedRawTransactionManager(Web3j web3j, Credentials credentials) {
        super(web3j, credentials);
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value) throws IOException {
        EthSendTransaction tx = super.sendTransaction(gasPrice, gasLimit, to, data, value);
        if(onceBeforeWait != null){
            onceBeforeWait.accept(tx);
            onceBeforeWait = null;
        }
        return tx;
    }
}
