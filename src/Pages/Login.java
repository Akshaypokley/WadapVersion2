package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class Login {

    WebDriver driver;
    @FindBy(xpath = "//div[1]/h3")
    WebElement LoginLogo;

    @FindBy(xpath = ".//*[@id='User_Nm']")
    WebElement UserName;

    @FindBy(xpath = ".//*[@id='Password']")
    WebElement Password;

    @FindBy(xpath = ".//*[@id='formCommon']/input")
    WebElement Login_Button;

    public Login(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        if(!LoginLogo.isDisplayed())
            throw new IllegalStateException("This not Login page");
    }



    public void setUserName(String userNm)
    {
        UserName.sendKeys(userNm);
    }

    public void setPassword(String password)
    {
        Password.sendKeys(password);
    }

    public void ClickLogin_Button()
    {
        Login_Button.click();
    }
}
