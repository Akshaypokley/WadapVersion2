package Utilites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class OpenBrowser {

    static WebDriver driver;
    public static void openbrowser(String BroweserName)
    {
        switch (BroweserName) {
            case "firefox":

                System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
                driver = new FirefoxDriver();

                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
                driver = new ChromeDriver();


                break;
            case "IE":
                System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
                driver = new InternetExplorerDriver();

                break;
            default:
                System.out.println("browser : " + BroweserName + " is invalid, Launching Firefox as browser of choice..");
                driver = new ChromeDriver();
        }
    }

    public static void getUrl()
    {
        driver.get("http://192.168.0.57:8018/");
    }
    }


