import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.LoginFunction.Loginf;
import static Utilites.OpenBrowser.getUrl;
import static Utilites.OpenBrowser.openbrowser;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class test1 {

    WebDriver driver1;
    @BeforeClass
    public void te()
    {
        driver1= openbrowser("chrome");
        getUrl("url");
    Loginf();
    }

    @Test
    public  void a()
    {
        System.out.println("d");
    }
}
