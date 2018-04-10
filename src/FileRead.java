import java.io.*;

class FileRead
{
    private static String strNewLine;
    
    public static void main(String args[])
    {
        try{
        // Open the file that is the first
        // command line parameter
        FileInputStream fstream = new FileInputStream("E:/Education/Related to Researches/MSc/My_Work/Coding/Hybrid_Encryption/test.txt");
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            strNewLine = strNewLine + strLine;
    }

        System.out.println (strNewLine);
        //Close the input stream
        in.close();
        }catch (Exception e){//Catch exception if any
        System.err.println("Error: " + e.getMessage());
        }
    }
}