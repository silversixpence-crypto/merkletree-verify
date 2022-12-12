import java.nio.charset.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.*;

public class MerkleTreeBuild {

    public static String dir = "U:\\_dev\\Git\\Merkle Tree\\merkletree-verify\\";

    public static void main(String[] args) {
        try {
            /*
             * Cleanup
             */
            File folder = new File(dir);
            File[] files = folder.listFiles();
            for (File f : files) {
                if (f.getName().startsWith("tree-")) {
                    f.delete();
                }
            }

            /*
             * Need to generate some leaves. The leaves would come from actual exchange
             * data. Interpret the input data to the SHA256 hash function as a string.
             * The tree-level number by Merkle Tree definition is the wrong way round. The
             * root is suppose to be level 0.
             * The next node in the tree is the SHA256 hash of the concatenated previous two
             * nodes. This concatenation is interpreted as a hex-number, not a string.
             */
            int num_leaves = 10000;
            String[] leaves = new String[num_leaves];
            try (BufferedWriter w = new BufferedWriter(new FileWriter("tree-level0.csv"))) {
                for (int n = 0; n < num_leaves; n++) {
                    leaves[n] = sha256string(UUID.randomUUID().toString());
                    w.write(leaves[n]);
                    w.newLine();
                }
            }

            /*
             * Leaves are level 0 (root should be level 0, but our levels are wrong way
             * round); calculate level 1 of the tree.
             */
            calcTreeLevel(1, leaves);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int calcTreeLevel(int level, String[] h) throws Exception {

        System.out.println("calculating tree level-" + level);

        try (BufferedWriter w = new BufferedWriter(new FileWriter(dir + "tree-level" + level + ".csv"))) {
            int num_hashes = h.length;
            int add_hashes = num_hashes % 2;

            String[] hashes = Arrays.copyOf(h, num_hashes);
            String[] next_hashes = new String[(num_hashes + add_hashes) / 2];

            for (int q = 0; q < hashes.length - 1; q = q + 2) {
                String hash = sha256hex(hashes[q] + hashes[q + 1]);

                next_hashes[(int) (q / 2)] = hash;

                w.write(hash + "," + level + "," + hashes[q] + "," + hashes[q + 1]);
                w.newLine();
            }

            w.close();

            if (add_hashes != 0) { // move unused node to next level
                next_hashes[next_hashes.length - 1] = hashes[hashes.length - 1];
            }

            if (next_hashes.length == 1) {
                /*
                 * Stop if only the root hash is left.
                 */
                return level;
            } else {
                /*
                 * Calculate the next-higher level
                 */
                return calcTreeLevel(level + 1, next_hashes);
            }
        }
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