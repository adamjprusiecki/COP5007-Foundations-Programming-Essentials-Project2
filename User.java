/*
 * Project 2
 * User.java
 * 
 * Concrete class, inherits from Account.java
 * 
 * @author Adam Prusiecki
 * @version 1.0
 * @since 2023/07/16
 */

public class User extends Account
{
    //Class variables
    private String username;
    private String fullName;
    private int deptCode;

    //Default constructor
    public User()
    {
        super();
        this.username = "";
        this.fullName = "";
        this.deptCode = 0;
    }

    //Parameterized constructor
    public User(String username, String fullName, int deptCode, String clearPassword, String key)
    {
        super(clearPassword, key);
        this.username = username;
        this.fullName = fullName;
        this.deptCode = deptCode;
    }

    //Boiler plate getters
    public String getUsername()
    {
        return this.username;
    }

    public String getFullName()
    {
        return this.fullName;
    }

    public int getDeptCode()
    {
        return this.deptCode;
    }

    //Boiler plate setters
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setDeptCode(int deptCode)
    {
        this.deptCode = deptCode;
    }

    /*
     * Formats all concrete class, and super class, variables into nicely formatted list
     * 
     *  clearPassword: <>
     *  encryptedPassword: <>
     *  key: <>
     *  accountId: <>
     *  nextIDNum: <>
     *  userName: <>
     *  fullName: <>
     *  deptCode: <>
     */
    public String toString()
    {
        String str = super.toString() 
                    + "userName: " + this.getUsername()
                    + "\nfullName: " + this.getFullName()
                    + "\ndeptCode: " + this.getDeptCode()
                    + "\n";
        return str;
    }
}
