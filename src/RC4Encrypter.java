import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.InvalidKeyException;
import java.io.*;

public class RC4Encrypter {

    private static String algorithm     = "RC4";
    private static Key key              = null;
    private static Cipher cipher        = null;
    private static String gotKey;
    private static String strNewLine    = null;

    public static void setUp() throws Exception {
        key         = KeyGenerator.getInstance(algorithm).generateKey();
        gotKey      = key.toString();
        cipher      = Cipher.getInstance(algorithm);
    }

    public static void main(String[] args) throws Exception {
        setUp();

        FileInputStream fstream = new FileInputStream("E:/Education/Related to MSc/Research/My_Work/Coding/Secrecy_Measurement/test.txt");
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
             if (strNewLine == null)
                strNewLine = strLine;
             else
                strNewLine = strNewLine + strLine;
         }
        //Close the input stream
        in.close();
        br.close();

        //Get the current system time
        GetCurrentDateTime.GetCurrTime();

        byte[] encryptionBytes = null;
        String input           = strNewLine;

        //Get the current system time in milliseconds
        long startTime1 = System.currentTimeMillis();

        encryptionBytes = RC4_Encrypt(input);

        //Get the current system time in milliseconds
        long endTime1 = System.currentTimeMillis();

        //Calculate the elapsed time in milliseconds
        long timeElapsed1 = GetCurrentDateTime.GetTimeElapsed(startTime1, endTime1);


        //Printing the results...
        System.out.println("\n\t\t* What you have entered: " + input);
        System.out.println("\n\t\t* Generated Key Length: " + gotKey.length());
        System.out.println("\n\t\t* Cipher Text: " + encryptionBytes);
        System.out.println("\n\t\t* Time taken for encryption: " + timeElapsed1 +" ms");

        //Get the current system time in milliseconds
        long startTime2 = System.currentTimeMillis();
        //Decryption
        String recoveredString = RC4_Decrypt(encryptionBytes);
        //Get the current system time in milliseconds
        long endTime2 = System.currentTimeMillis();
        //Calculate the elapsed time in milliseconds
        long timeElapsed2 = GetCurrentDateTime.GetTimeElapsed(startTime2, endTime2);

        System.out.println("\n\t\t* What you have recovered: " + recoveredString);
        System.out.println("\n\t\t* Time taken for decryption: " + timeElapsed2 + " ms");

        //Get the current system time
        GetCurrentDateTime.GetCurrTime();
    }

    public static byte[] RC4_Encrypt(String input)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }

    public static String RC4_Decrypt(byte[] encryptionBytes)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
        String recovered = new String(recoveredBytes);
        return recovered;
    }
}
