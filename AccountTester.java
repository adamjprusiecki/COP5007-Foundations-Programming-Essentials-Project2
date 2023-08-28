import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

/*
 * Project 2
 * AccountTester.java
 * 
 * Java program that tests the functionality of Account.java, User.java, and Bot.java
 * Reads a file "data.txt", located at the same directory level as AccountTester.java
 * 
 * NOTE: data.txt file must be in src folder
 * 
 * Validates line by line, checking if the line is:
 *      - a user or a bot account
 *      - the password is at least 8 characters
 *      - the key is at least 5 characters
 * 
 * Creates parameterized objects for every valid line, and calls toString() for every object
 * Creates default objects for every valid line, assigns class variables with setters, and calls toString() for every object
 * 
 * @author Adam Prusiecki
 * @version 1.0
 * @since 2023/07/16
 */

public class AccountTester
{   
    /*
     * args:
     *      String[] lineArr
     *          array of strings, each element being an attribute used to init object
     *  
     * returns
     *      Bot b
     *          bot object
     * 
     * Function used to create an arbitrary parameterized Bot object, based on a validated array of String elements.
     */
    public static Bot makeParameterizedBotObj(String[] lineArr)
    {
        //Slice String array elements, for better readability
        String botFileName = lineArr[1];
        String category = lineArr[2];
        String dateUploaded= lineArr[3];
        String createdBy = lineArr[4];
        String clearPassword = lineArr[5];
        String key = lineArr[6];

        //Create parameterized object
        Bot b = new Bot(botFileName, category, dateUploaded, createdBy, clearPassword, key);
        return b;
    }

    /*
     * args:
     *      String[] lineArr
     *          array of strings, each element being an attribute used to set class variables
     *  
     * returns
     *      Bot b
     *          bot object
     * 
     * Function used to create an arbitrary default Bot object, based on a validated array of String elements.
     * Uses setters to set class variables
     */
    public static Bot makeDefaultBotObj(String[] lineArr)
    {
        //Slice String array elements, for better readability
        String botFileName = lineArr[1];
        String category = lineArr[2];
        String dateUploaded= lineArr[3];
        String createdBy = lineArr[4];
        String clearPassword = lineArr[5];
        String key = lineArr[6];

        //Create default object, use setters to assign class variables
        Bot b = new Bot();
        b.setBotFileName(botFileName);
        b.setCategory(category);
        b.setDateUpdated(dateUploaded);
        b.setCreatedBy(createdBy);
        b.setKey(key);
        b.setClearPassword(clearPassword);
        
        return b;
    }

    /*
     * args:
     *      String[] lineArr
     *          array of strings, each element being an attribute used to init object
     *  
     * returns
     *      User b
     *          user object
     * 
     * Function used to create an arbitrary parameterized User object, based on a validated array of String elements.
     */
    public static User makeParameterizedUserObj(String[] lineArr)
    {
        //Slice String array elements, for better readability
        String username = lineArr[1];
        String fullName = lineArr[2];
        String deptCode = lineArr[3];
        String clearPassword = lineArr[4];
        String key = lineArr[5];
        
        User u = new User(username, fullName, Integer.parseInt(deptCode), clearPassword, key);
        return u;
    }
    
    /*
     * args:
     *      String[] lineArr
     *          array of strings, each element being an attribute used to set class variables
     *  
     * returns
     *      User b
     *          user object
     * 
     * Function used to create an arbitrary default user object, based on a validated array of String elements.
     * Uses setters to set class variables
     */
    public static User makeDefaultUserObj(String[] lineArr)
    {
        //Slice String array elements, for better readability
        String username = lineArr[1];
        String fullName = lineArr[2];
        String deptCode = lineArr[3];
        String clearPassword = lineArr[4];
        String key = lineArr[5];

        //Create default object, use setters to assign class variables
        User u = new User();
        u.setUsername(username);
        u.setFullName(fullName);
        u.setDeptCode(Integer.parseInt(deptCode));
        u.setKey(key);
        u.setClearPassword(clearPassword);

        return u;
    }
    
    /*
     * args:
     *      String line
     *          arbitrary line from data.txt
     *      
     *      boolean displayMsg
     *          boolean variable used to determine if error should be displayed or not
     * 
     * returns
     *      boolean isValidLine
     *          true if
     *              account type is 'u' or 'b'
     *              && password is >= 8 characters
     *              && key is >= 5 characters
     *  
     * Function is used to validate if a line in data.txt is valid to be used for object creation 
     */
    public static boolean isValidLine(String line, boolean displayMsg)
    {
        //Function variables
        boolean isValidLine = true;
        String clearPassword;
        String key;

        String[] lineArr = line.split(","); // Split line into String array
        if(lineArr[0].equals("u")) // IF line is a user 
        {
            clearPassword = lineArr[4];
            key = lineArr[5];
        }
        else if(lineArr[0].equals("b")) // IF line is a bot
        {
            clearPassword = lineArr[5];
            key = lineArr[6];
        }
        else // IF line is neither a user or a bot
        {
            if(displayMsg == true)
            {
                System.out.println("Invalid account type, must be 'u' or 'b':\n" + line + "\n"); // Display account type error message
            }
                return false; // Is invalid line
        }

        if(clearPassword.length() < 8) // Check if password length is at least 8 characters
        {
            if(displayMsg == true)
            {
                System.out.println("Invalid password, must be at least 8 characters:\n" + line + "\n"); // Display password error message
            }
                isValidLine = false; // Is invalid line
        }
        if(key.length() < 5) // Check if key length is at least 5 characters
        {
            if(displayMsg == true)
            {
                System.out.println("Invalid key, must be at least 5 characters:\n" + line + "\n"); // Display key error message
            }
                isValidLine = false; // Is invalid line
        }

        return isValidLine;
    }

    /*
     * args
     *      String[] lines
     *          Arbitrary line from data.txt, broken up into a String array, where each element is an object variable
     * 
     * returns
     *      int validLines
     *          Total number of valid lines in data.txt, that can be used to generate objects
     * 
     * Gets the total number of valid lines from data.txt, so I know what size to assign to the Accounts[] array
     * The entire purpose of this function is so I can set the size of the array correctly.
     * I did it this way, because I assumed we are not allowed to use arrayLists.
     */
    public static int getNumOfValidLines(String[] lines)
    {
        
        int validLines = 0;
        String line;

        for(int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            if(isValidLine(line, true) == true) // IF line from data.txt is a valid line, increment validLines
            {
                validLines++;
            }
        }
        return validLines;
    }

    /*
     * args
     *      String[] lines
     *          arbitrary array of lines from the data.txt file
     *      
     *      boolean buildWithDefaultConstructor
     *          true if
     *              Want to build the Account[] array with the default constructor and setters
     *          false if
     *              Want to build the Account[] array with the parameterized constructor'
     * 
     * returns
     *      Account[] accounts
     *          polymorphic array with an arbitrary number of Bot and User objects as elements
     *          size == number of valid lines from data.txt
     * 
     * Gets the valid number of lines from data.txt
     * Creates an Accounts[] array with that size
     * Builds and assigns Bot or User objects to that array, based on which constructor you want to build with
     * Returns that array
     */
    public static Account[] getAccountsArray(String[] lines, boolean buildWithDefaultConstructor)
    {
        int validLines = getNumOfValidLines(lines); // Get total number of valid lines from data.txt
        Account[] accounts = new Account[validLines]; // Create polymorphic accounts array, with a size of total number of valid lines
        int validLineNum = 0; // Valid line index
        String[] lineArr; // Line broken out into an array, where each element is a class variable

        if(buildWithDefaultConstructor == false) // IF build with parametrized constructor
        {
            for(int i = 0; i < lines.length; i++)
            {
                if(isValidLine(lines[i], false) == true) // IF the line is valid, make object
                {
                    lineArr = lines[i].split(",");
                    if(lineArr[0].equals("u")) // IF object is a user object
                    {
                        accounts[validLineNum] = makeParameterizedUserObj(lineArr); 
                        validLineNum++;
                    }
                    else if(lineArr[0].equals("b")) // IF object is a bot object
                    {
                        accounts[validLineNum] = makeParameterizedBotObj(lineArr); // Make and return parameterized bot object
                        validLineNum++;
                    }
                    else // Default error catch, should never reach
                    {
                        System.out.println("Entered line is not a user or bot");
                    }
                }
                else
                {
                    continue; // Skip IF line is not valid
                }
            }
        }
        else // IF build with Default constructor
        {
            for(int i = 0; i < lines.length; i++)
            {
                if(isValidLine(lines[i], false) == true) // IF the line is valid, make object
                {
                    lineArr = lines[i].split(",");
                    if(lineArr[0].equals("u")) // IF object is a bot object
                    {
                        accounts[validLineNum] = makeDefaultUserObj(lineArr); // Make and return default bot object, initialized with setters
                        validLineNum++;
                    }
                    else if(lineArr[0].equals("b")) // IF object is a bot object
                    {
                        accounts[validLineNum] = makeDefaultBotObj(lineArr); // Make and return default bot object, initialized with setters
                        validLineNum++;
                    }
                    else // Default error catch, should never reach
                    {
                        System.out.println("Entered line is not a user or bot");
                    }
                }
                else
                {
                    continue; // Skip IF line is not valid
                }
            }
        }

        return accounts;
    }

    public static void main(String[] args)
    {
        String filename = "src/data.txt"; // Arbitrary file with input data
        long numLines = 0;
        File file = new File(filename); // File object
        
        if(file.exists()) // IF the file exists
        {
            Path path = Paths.get(filename);
            try
            {
                numLines = Files.lines(path).count(); // Get total number of lines from file
            }
            catch(Exception e)
            {
                System.out.println("Failed with exception:\n" + e); // Exception catch
            }

            String[] lines = new String[(int)numLines]; // Create String array with all lines from data file, using numLines for an array size. I did it this way because I assume we were not allowed to use arrayLists
            try
            {
                int lineNum = 0;
                Scanner scnr = new Scanner(file);
                while(scnr.hasNextLine())
                {
                    lines[lineNum] = scnr.nextLine(); // Populate lines array elements, where each element is a line from the data file
                    lineNum++;
                }
            }
            catch(Exception e)
            {
                System.out.println("Failed with exception:\n" + e); // Exception catch
            }

            System.out.println("Creating objects with parameterized constructor\n");
            Account[] parameterizedAccounts = getAccountsArray(lines,false); // Create parameterized objects, using input from data file
            System.out.println("Parameterized objects**********************************\n");
            for(int i = 0; i < parameterizedAccounts.length; i++)
            {
                System.out.println(parameterizedAccounts[i].toString()); // Print all objects toString()
            }

            System.out.println("Creating objects with default constructor\n");
            Account[] defaultAccounts = getAccountsArray(lines,true); // Create default objects, using input from data file
            System.out.println("Default objects****************************************\n");
            for(int i = 0; i < defaultAccounts.length; i++)
            {
                System.out.println(defaultAccounts[i].toString()); // Print all objects toString()
            }
        }
        else
        {
            System.out.println("File: " + filename + " does not exist!"); // IF file does not exist
        }
    }
}
