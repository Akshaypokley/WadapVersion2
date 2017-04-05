package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class Forgot {

    WebDriver driver;

    @FindBy(xpath = "//div[1]/h3")
    WebElement Logo;

    @FindBy(xpath = ".//*[@id='formCommon']/a")
    WebElement forgotlink;

    @FindBy(xpath =".//*[@id='Email']" )
    WebElement EmailBox;

    @FindBy(xpath = ".//*[@id='btnSubmit']")
    WebElement sendButtonl;


    public Forgot(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        if(!Logo.isDisplayed())
            throw new IllegalStateException("This not Forgot page");
    }


    public void clickForgotlink()
    {
        forgotlink.click();

    }


    public void setEmailBox(String UserMail)
    {
        EmailBox.sendKeys(UserMail);
    }

    public void ClickSendButtonl()
    {
        sendButtonl.click();
    }

}
