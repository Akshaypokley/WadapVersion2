import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utilites.OpenBrowser.getUrl;
import static Utilites.OpenBrowser.openbrowser;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class test1 {

    @BeforeClass
    public void te()
    {
        openbrowser("chrome");
       // getUrl();
    }

    @Test
    public  void a()
    {
        System.out.println("d");
    }
}
