package demo;

import org.whispersystems.curve25519.Curve25519;
import org.whispersystems.curve25519.Curve25519KeyPair;

public class Hello {

    public static void main(String[] args) {
        Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
        Curve25519KeyPair keyPair = cipher.generateKeyPair();

        byte[] privateKey = keyPair.getPrivateKey();
        byte[] publicKey = keyPair.getPublicKey();
        byte[] sharedSecret = cipher.calculateAgreement(publicKey, privateKey);

        byte[] signature = cipher.calculateSignature(privateKey, "Hello, world!".getBytes());

        boolean validSignature = cipher.verifySignature(publicKey, "Hello, world!".getBytes(), signature);
        System.out.println("validSignature: " + validSignature);
    }

}
