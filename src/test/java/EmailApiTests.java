import apis.EmailApiClient;
import data.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EmailApiTests {
    EmailApiClient emailChecker = new EmailApiClient();

   @Test
    public void verifyTheUserEmailIsDisposable() {
      Assert.assertEquals(emailChecker.isEmailDisposable(TestData.disposableEmail) , "true");
    }

    @Test
    public void verifyTheUserEmailIsNotDisposable() {
        Assert.assertEquals(emailChecker.isEmailDisposable(TestData.validEmail) , "false");
    }

   @Test
   public void verifyEmailIsValidGivenValidToken() {
      Assert.assertEquals(emailChecker.isEmailValid(TestData.validToken ,TestData.validEmail),"true");
   }

    @Test
    public void verifyEmailIsValidGivenInValidToken() {
        Assert.assertTrue(emailChecker.isEmailValid(TestData.invalidToken, TestData.validEmail).contains("Unauthorized"));
    }
}
