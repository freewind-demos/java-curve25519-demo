package demo;

import org.whispersystems.curve25519.Curve25519;
import org.whispersystems.curve25519.Curve25519KeyPair;

import java.util.Arrays;

public class Hello {

    private static Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
    private static Curve25519KeyPair keyPair = cipher.generateKeyPair();
    private static byte[] privateKey = keyPair.getPrivateKey();
    private static byte[] publicKey = keyPair.getPublicKey();

    public static void main(String[] args) {
        signatureDemo();
        sharedSecretDemo();
    }

    private static void signatureDemo() {
        byte[] signature = cipher.calculateSignature(privateKey, "Hello, world!".getBytes());

        boolean validSignature = cipher.verifySignature(publicKey, "Hello, world!".getBytes(), signature);
        System.out.println("validSignature: " + validSignature);
    }

    private static void sharedSecretDemo() {
        Curve25519KeyPair keyPair2 = cipher.generateKeyPair();
        byte[] privateKey2 = keyPair2.getPrivateKey();
        byte[] publicKey2 = keyPair2.getPublicKey();

        byte[] sharedSecret1 = cipher.calculateAgreement(publicKey, privateKey2);
        byte[] sharedSecret2 = cipher.calculateAgreement(publicKey2, privateKey);

        System.out.println("Two shared secrets should be the same");
        System.out.println("Shared secret1: " + Arrays.toString(sharedSecret1));
        System.out.println("Shared secret2: " + Arrays.toString(sharedSecret2));
    }

}
