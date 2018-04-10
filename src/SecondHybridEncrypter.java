import java.io.*;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class SecondHybridEncrypter {
    private static String decryptedHybridText;
    private static Object strNewLine;


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
        String input           = (String) strNewLine;

        //Get the current system time in milliseconds
        long startTime1 = System.currentTimeMillis();

        encryptionBytes = HybridEncryption(input);

        //Get the current system time in milliseconds
        long endTime1 = System.currentTimeMillis();

        //Calculate the elapsed time in milliseconds
        long timeElapsed1 = GetCurrentDateTime.GetTimeElapsed(startTime1, endTime1);


        //Printing the results...
        System.out.println("\n\t\t* What you have entered: " + input);
        System.out.println("\n\t\t* Cipher Text: " + encryptionBytes);
        System.out.println("\n\t\t* Time taken for encryption: " + timeElapsed1 +" ms");

        //Get the current system time in milliseconds
        long startTime2 = System.currentTimeMillis();
        //Decryption
        //String recoveredString = HybridDecryption(encryptionBytes);
        //Get the current system time in milliseconds
        long endTime2 = System.currentTimeMillis();
        //Calculate the elapsed time in milliseconds
        long timeElapsed2 = GetCurrentDateTime.GetTimeElapsed(startTime2, endTime2);

        //System.out.println("\n\t\t* What you have recovered: " + recoveredString);
        //System.out.println("\n\t\t* Time taken for decryption: " + timeElapsed2 + " ms");

        //Get the current system time
        GetCurrentDateTime.GetCurrTime();
    }


    public static void setUp() throws Exception {
        AESEncrypter.setUp();
        RC4Encrypter.setUp();
    }

    public static byte[] HybridEncryption(String input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException{

        byte[] encryptionBytes = null;

        //3DES Related
        encryptionBytes = AESEncrypter.AES_Encrypt(input);

        //Encrypt with RC4 and Return (Reference: RC4.java)
        return (RC4Encrypter.RC4_Encrypt(encryptionBytes.toString()));
        }

    public static String HybridDecryption(byte[] inputEncryptedByte) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException{

        //RC4 Decryption
        String rc4DecryptedText = RC4Encrypter.RC4_Decrypt(inputEncryptedByte);

        //3DES Related
        decryptedHybridText = AESEncrypter.AES_Decrypt(rc4DecryptedText.getBytes());

        return decryptedHybridText;

        }

    }
