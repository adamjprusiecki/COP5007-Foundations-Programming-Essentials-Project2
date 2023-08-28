import java.util.GregorianCalendar;
import java.util.Calendar;

/*
 * Project 2
 * Bot.java
 * 
 * Concrete class, inherits from Account.java
 * 
 * @author Adam Prusiecki
 * @version 1.0
 * @since 2023/07/16
 */

public class Bot extends Account
{
    //Class variables
    private String botFileName;
    private String category;
    private GregorianCalendar dateUpdated;
    private String createdBy;

    //Default constructor
    public Bot()
    {
        this.botFileName = "";
        this.category = "";
        this.dateUpdated = null;
        this.createdBy = "";
    }

    //Parameterized constructor
    public Bot(String botFileName, String category, String date, String createdBy, String clearPassword, String key)
    {
        super(clearPassword, key);
        this.botFileName = botFileName;
        this.category = category;
        this.dateUpdated = this.getGregorianCalendar(date);
        this.createdBy = createdBy;
    }

    /*
     * args
     *      String date
     *          String date formatted as "01/01/2023"
     * 
     * returns
     *      GregorianCalendar gc
     *          GregorianCalendar Object
     * 
     * Helper method to convert String date into a GregorianCalendar object
     */
    private GregorianCalendar getGregorianCalendar(String date)
    {
        String[] dateArr = date.split("/");
        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);

        GregorianCalendar gc = new GregorianCalendar(year, month - 1, day);
        return gc;
    } 

    //Boiler plate getters
    public String getBotFileName()
    {
        return this.botFileName;
    }

    public String getCategory()
    {
        return this.category;
    }

    public GregorianCalendar getDateUpdated()
    {
        return this.dateUpdated;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    //Boiler plate setters
    public void setBotFileName(String botFileName)
    {
        this.botFileName = botFileName;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setDateUpdated(String date)
    {
        GregorianCalendar gc = this.getGregorianCalendar(date);
        this.dateUpdated = gc;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    /*
     * Formats all concrete class, and super class, variables into nicely formatted list
     * 
     *  clearPassword: <>
     *  encryptedPassword: <>
     *  key: <>
     *  accountId: <>
     *  nextIDNum: <>
     *  botFileName: <>
     *  category: <>
     *  date: <>
     *  createdBy: <>
     */
    public String toString()
    {
        String str = super.toString()
                    + "botFileName: " + this.getBotFileName()
                    + "\ncategory: " + this.getCategory()
                    + "\ndate: "
                    + (this.getDateUpdated().get(Calendar.MONTH)+1) + "/"
                    + this.getDateUpdated().get(Calendar.DAY_OF_MONTH) + "/"
                    + this.getDateUpdated().get(Calendar.YEAR)
                    + "\ncreatedBy: " + this.getCreatedBy()
                    + "\n";
        return str;
    }
}
