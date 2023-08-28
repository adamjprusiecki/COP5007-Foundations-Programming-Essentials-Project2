import org.junit.Test;

public class AccountJUnitTest
{
    @Test
    public void test_Encrypt()
    {
        Account a1 = new Account();
        a1.setKey("house");
        a1.setClearPassword("z3Br@d0G");
        assert(a1.getEncryptedPassword().equals("g'<j*Q$A"));
    }
}
