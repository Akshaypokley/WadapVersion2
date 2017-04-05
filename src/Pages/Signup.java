package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class Signup {

    WebDriver driver;

    /*Xpath for Text Boxes*/
    @FindBy(xpath = "//div[1]/h3")
    WebElement Lebel_Name;

    @FindBy(linkText = "SIGNUP")
    public WebElement signumlink;



    @FindBy(xpath="//*[@id='Full_Nm']")
    public WebElement First_NM;

    @FindBy(xpath = ".//*[@id='User_Nm']")
    public WebElement LastNM;

    @FindBy(xpath = ".//*[@id='Mob_No']")
    public WebElement Cont_No;

    @FindBy(xpath=".//*[@id='Email']")
    public WebElement Emaild;

    @FindBy(xpath = ".//*[@id='Password']")
    public WebElement Password;

    @FindBy(xpath = ".//*[@id='Confirm_Password']")
    public WebElement Confir_Password;

    @FindBy(xpath = ".//*[@id='formCommon']/label/i")
    public WebElement CheckBox_terms;

    @FindBy(xpath = ".//*[@id='btnSubmit']")
    public WebElement RegistrationBtn;

    public Signup(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

        if(!Lebel_Name.isDisplayed())
            throw new IllegalStateException("This not signup page");

    }

    public WebElement getLebel_Name()
    {
        return  Lebel_Name;

    }

    public void  clicksignumlink()
    {
        //signumlink.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", signumlink);
    }

    public void setFirst_NM(String FirstName)
    {
        First_NM.sendKeys(FirstName);
    }

    public void setLastNM(String LastName)
    {
        LastNM.sendKeys(LastName);
    }

    public void setCont_No(String MobileNo)
    {
        Cont_No.sendKeys(MobileNo);
    }

    public void setEmaild(String Email)
    {
        Emaild.sendKeys(Email);
    }

    public void setPassword(String Passwordd)

    {
        Password.sendKeys(Passwordd);
    }

    public void setConfir_Password(String Re_pass)
    {
        Confir_Password.sendKeys(Re_pass);
    }

    public void CheckBox_terms()
    {
        CheckBox_terms.click();
    }

    public void clcikRegistrationBtn()
    {
        RegistrationBtn.click();
    }
}
