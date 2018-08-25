
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

public class Main {


    public static void main() throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        Credentials credentials = Credentials.create("xxxx");
        BigInteger gasPrice = BigInteger.valueOf(1);
        BigInteger gasLimit = BigInteger.valueOf(4 << 20);

        CryptoFiat root = CryptoFiat.load(
                "0xa10a263D4336E4466502b2889D27D04582a86663",
                web3j, credentials, gasPrice, gasLimit
        );

        Data data = Data.load(root.contractAddress(Constants.DATA).send(), web3j, credentials, gasPrice, gasLimit);
        Accounts accounts = Accounts.load(root.contractAddress(Constants.ACCOUNTS).send(), web3j, credentials, gasPrice, gasLimit);
        Approving approving = Approving.load(root.contractAddress(Constants.APPROVING).send(), web3j, credentials, gasPrice, gasLimit);
        Reserve reserve = Reserve.load(root.contractAddress(Constants.RESERVE).send(), web3j, credentials, gasPrice, gasLimit);
        Enforcement enforcement = Enforcement.load(root.contractAddress(Constants.ENFORCEMENT).send(), web3j, credentials, gasPrice, gasLimit);
        AccountRecovery accountRecovery = AccountRecovery.load(root.contractAddress(Constants.ACCOUNT_RECOVERY).send(), web3j, credentials, gasPrice, gasLimit);
        Delegation delegation = Delegation.load(root.contractAddress(Constants.DELEGATION).send(), web3j, credentials, gasPrice, gasLimit);

        TransactionReceipt txr = accounts.transfer("des", BigInteger.valueOf(1500)).send();
    }
}
