/*
 * Project 2
 * Account.java
 * 
 * Inherited super class
 * 
 * @author Adam Prusiecki
 * @version 1.0
 * @since 2023/07/16
 */

public class Account
{
    //Class variables
    private String clearPassword;
    private String encryptedPassword;
    private String key;
    private int accountId;
    private static int nextIDNum = 1000; 

    //Default Constructor
    public Account()
    {
        this.clearPassword = "";
        this.encryptedPassword = "";
        this.key = "";
        this.accountId = this.nextIDNum;
        this.nextIDNum++;
    }

    //Parameterized constructor
    public Account(String clearPassword, String key)
    {
        this.clearPassword = clearPassword;
        this.key = key;
        this.accountId = this.nextIDNum;
        this.nextIDNum++;

        this.Encrypt();
    }

    //Boiler plate getters
    public String getClearPassword()
    {
        return this.clearPassword;
    }

    public String getEncryptedPassword()
    {
        return this.encryptedPassword;
    }

    public String getKey()
    {
        return this.key;
    }

    public int getAccountId()
    {
        return this.accountId;
    }

    public int getNextIDNum()
    {
        return this.nextIDNum;
    }

    //Boiler plate setters
    public void setClearPassword(String clearPassword)
    {
        this.clearPassword = clearPassword;
        this.Encrypt(); //Call Encrypt() when password is assigned
    }

    public void setKey(String key)
    {
        this.key = key;
        this.Encrypt(); //Call Encrypt() when key is updated
    }

    public void setAccountId()
    {
        this.accountId = this.nextIDNum;
        this.nextIDNum++;
    }

    /*
     * Encrypt method that uses Vigenère cipher to set class variable 
     * encryptedPassword. Uses '!' as an arbitrary starting point and 'z'
     * as an arbitrary ending point. 
     * 
     * Populates a "matrix" as a 2D array, where the corresponding element relative
     * to the [key character][password character] is the correct encrypted value. 
     */
    private void Encrypt()
    {
        char start = '!'; // Arbitrary character start
        char end = 'z'; // Arbitrary character end
        int range = end - start + 1; // Arbitrary range

        char[][] cipherTable = new char[range][range]; // 2D array that gets populated using the Vigenère cipher "matrix" format
        char nextChar;

        /* 
         * Populates cipherTable from Arbitrary character start to Arbitrary character end
        */
        for(int i = 0; i < range; i++)
        {
            for(int j = 0; j < range; j++)
            {
                nextChar = (char)(j + i + start);
                if(nextChar > end) // If ascii value is greater than end character, loop back to starting character
                {
                    nextChar = (char)(start + (nextChar - end - 1));
                }
                cipherTable[i][j] = nextChar; // Assign next character in row
            }
        }

        int keyIndex = 0; // Index to know when to loop key back to beginning
        String encryptedPass = "";

        /* Iterates through this.clearPassword and encrypts each character relative to the cipherTable */
        for(int i = 0; i < this.getClearPassword().length(); i++)
        {
            encryptedPass += cipherTable[this.getKey().charAt(keyIndex) - start][this.getClearPassword().charAt(i) - start]; // cipherTable[key char][password char]
            keyIndex++;
            if(keyIndex == this.key.length())
            {
                keyIndex = 0; // Loop keyIndex back to start
            }
        }
        this.encryptedPassword = encryptedPass; // Assign class variable instance with encrypted password
    }

    /*
     * Formats all class variables into nicely formatted list
     * 
     *  clearPassword: <>
     *  encryptedPassword: <>
     *  key: <>
     *  accountId: <>
     *  nextIDNum: <>
     */
    public String toString()
    {
        String str = "clearPassword: " + this.clearPassword
                + "\nencryptedPassword: " + this.encryptedPassword
                + "\nkey: " + this.key
                + "\naccountId: " + this.accountId
                + "\nnextIDNum: " + this.nextIDNum
                + "\n";

        return str;
    }
} 