import java.nio.charset.*;
import java.security.*;
import java.math.*;

public class MerkleTreeVerify {
    public static void main(String[] args) throws Exception {
        /*
         * To calculate your leaf hash, the input text should be interpreted as text.
         * To calculate a parent node hash from the two child nodes, the input text
         * should be interpreted as a hexadecimal number.
         */

        /*
         * Exchanges have different method for producing their merkle leaf hashes.
         * A common way is to hash the concatenated string of your user ID, some random
         * string (salt), the audit ID and a string that contains you rassets and their
         * balances. Some exchanges might not include salt or might double-hash the
         * concatenated string.
         */
        String user_id = "18fc922081c123456eccad04cf52514942b83bb97e50b3a9f9876fa72fefd04c";
        String salt = "9713e8bd";
        String audit_id = "PR31DEC22";
        String balances_string = "BTC=1.0000|ETH=2.0000|USDC=3.0000|USDT=4.0000";
        System.out.println("Merkle Leaf Hash: " + sha256string(user_id + salt + audit_id + balances_string));

        /*
         * Left child node hash:
         * e5f039b0905c0b6c82bdd7edf6f87ac7b3e983b0124acfaaeb4ed1c05302ec1f
         * Righ child node hash:
         * ff9345288cc66a29dd711784a0cf0b499ad99159bfd982d8537b12bd1449c61f
         * 
         * Parent node hash:
         * 917c99591ea45feb6096b83b7e265f7b3040db5a659497c42c5a02f4076fa7eb
         */
        String left = "e5f039b0905c0b6c82bdd7edf6f87ac7b3e983b0124acfaaeb4ed1c05302ec1f";
        String right = "ff9345288cc66a29dd711784a0cf0b499ad99159bfd982d8537b12bd1449c61f";
        System.out.println("Parent Node Hash: " + sha256hex(left + right));
    }

    public static String sha256string(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(data.getBytes(Charset.forName("UTF-8")));

        char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[hash.length * 2];
        for (int j = 0; j < hash.length; j++) {
            int v = hash[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars);
    }

    public static String sha256hex(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] b = new byte[data.length() / 2];
        for (int n = 0; n < b.length; n++) {
            b[n] = new BigInteger(data.substring(2 * n, 2 * n + 2), 16).byteValue();
        }

        byte[] hash = md.digest(b);

        char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[hash.length * 2];
        for (int j = 0; j < hash.length; j++) {
            int v = hash[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars);
    }
}