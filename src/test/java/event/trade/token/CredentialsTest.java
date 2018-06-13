package event.trade.token;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;

public class CredentialsTest {
    public static void main(String[] args) {
        try {
            Credentials credentials = WalletUtils.loadCredentials("zAq1xsw2",
                   "./src/main/resources/UTC.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

    }
}
