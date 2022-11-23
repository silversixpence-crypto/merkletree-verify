import java.nio.charset.*;
import java.security.*;

public class MerkleTreeVerify {
    public static void main(String[] args) throws Exception {
        // to calculate your leaf hash, paste the following information here:
        // with example data expect:
        // 253FB2FE65EC3A4DC0F095F1A3D9306EE64558CF1128455989250F52FA6A5504
        String userid = "287e29b8-de5b-4924-8906-b216f2d48cd6";
        String salt = "2A496ECE";
        String balances = "BTC=76.83|ETH=19.26|XRP=68.95|USDC=6.32";

        String to_be_hashed = userid + salt + balances;
        String hash = bytesToHex(sha256d(to_be_hashed));

        System.out.println("User ID: " + userid);
        System.out.println("Salt: " + salt);
        System.out.println("Balances: " + balances);

        System.out.println("Merkle Leaf: " + hash);

        // to calculate the next node hash, paste the four node hashes here:
        // with example data expect:
        // C0A8940CBF1F7E924B24D8612E0F2E3E2FE77679B09CEB7BADF8FF49FB39AECD
        String node1 = "B09DC86278DE3AB2E78D4978B88282DAD07AFEF25A6354435444557D0A2F7D06";
        String node2 = "B1925680ACC7728A8B7AD83936C1124537635F93164A0E990F005B5CB5E31535";
        String node3 = "8B55D98DDA16E22E66AFD4E7DB6390BE3A1EF9CE8EB8832CE53EB023396FFD37";
        String node4 = "E53D261F0B974D9DA5C599538CFB329742E9413F23D4999A78F73A00F843E630";

        to_be_hashed = node1 + node2 + node3 + node4;
        hash = bytesToHex(sha256d(to_be_hashed));

        System.out.println("Node Hash: " + hash);
    }

    public static byte[] sha256d(String data1) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash1 = md.digest(stringToBytes(data1));
        byte[] hash2 = md.digest(hash1);
        return hash2;
    }

    public static byte[] stringToBytes(String input) throws Exception {
        return input.getBytes(Charset.forName("UTF-8"));
    }

    public static String bytesToHex(byte[] bytes) throws Exception {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}