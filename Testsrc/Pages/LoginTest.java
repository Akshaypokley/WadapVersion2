package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.OpenBrowser.getUrl;
import static Utilites.OpenBrowser.openbrowser;

/**
 * Created by Akshay85 on 4/6/2017.
 */
public class LoginTest{

    WebDriver driver1;
    @BeforeClass
    public void LoadDriver()
    {
        driver1= openbrowser("chrome");
        getUrl("url");



    }

    @Test
    public void UserInput()
    {

        Login login=new Login(driver1);
        login.setUserName("d");
        login.setPassword("d");
        login.ClickLogin_Button();

    }
}
