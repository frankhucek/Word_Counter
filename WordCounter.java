

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * All fileIO used in implementing the HashTable to count words in a file
 * is done here
 * @author Frank Hucek
 */
public class WordCounter
{
    public static HashTable table = new HashTable(17);
    
    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Not enough arguments.");
        }
        else
        {
            System.out.println(WordCounter.wordCount(args[0], args[1]));
        }
    }
    
    /**
     * Counts the number of words in the file and 
     * formats properly and writes to the output file
     * @param inputFileName The input file name
     * @param outputFileName The output file name
     * @return Whether execution was a success and the table info when applicable
     */
    public static String wordCount(String inputFileName, String outputFileName)
    {
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);
        
        boolean success;
        
        success = makeHashTable(inputFile);
        if(!success)
            return "Failed to make HashTable";
        
        success = makeOutput(outputFile);
        if(!success)
            return "Failed to make Output File";
        
        return "Success.\n" + table.tableInfo();
    }
    
    /**
     * Makes a HashTable based on the input file
     * @param inputFile The file to read and make a HashTable of
     * word frequencies
     * @return true/false depending on whether the HashTable was created successfully
     */
    private static boolean makeHashTable(File inputFile)
    {
        String s;
        try
        {
            Scanner scanner = new Scanner(inputFile);
            while(scanner.hasNext())
            {
                s = scanner.nextLine();
                for(String key : s.split("[^a-zA-Z]"))
                {
                    if(!key.equals(""))
                        table.insert(key.toLowerCase(), 1);
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Makes the proper output File
     * @param outputFile The output file name to write to
     * @return true/false depending on if the output file was written to successfully
     */
    private static boolean makeOutput(File outputFile)
    {
        try
        {
            BufferedWriter fw = new BufferedWriter(new FileWriter(outputFile));
            fw.write(table.toString());
            fw.close();
        }
        catch(IOException e)
        {
            return false;
        }
        return true;
    }
    
}
