package Pages;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static Utilites.OpenBrowser.getUrl;
import static Utilites.OpenBrowser.openbrowser;

/**
 * Created by Akshay85 on 4/5/2017.
 */
public class LoginTest {

    WebDriver driver1;
    ExtentReports extent ;
    @BeforeMethod
    public void LoginTet() {
       // extent = initExtentReport.init();

        openbrowser("chrome");
        getUrl();
    }

    @Test(dataProvider = "InputData")
    public void UserInput(String UsNM, String PassWord, String Expected, String Xpath) throws IOException {


        ExtentTest Test= extent.startTest("LoginTest","To Test login button Functionality");
        try{
            login login = new login(driver1);
            Test.log(LogStatus.INFO, "Login Driver initialised");

            login.setUserName(UsNM);
            Test.log(LogStatus.INFO, "Set User Name");
            login.setPassword(PassWord);
            Test.log(LogStatus.INFO, "Set User Password");
            login.ClickLogin_Button();
            Test.log(LogStatus.INFO, "Click on Login Button");


            WebDriverWait wait = new WebDriverWait(driver1, 15);
            if(wait.until(ExpectedConditions.alertIsPresent())==null) {
                System.out.println("alert was not present");
            } else {

                System.out.println("alert was present");
                Alert alert = driver1.switchTo().alert();
                alert.accept();
            }

            try
            {
                driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                String Actual=driver1.findElement(By.xpath(Xpath)).getText();

                Assert.assertEquals(Actual,Expected,"TestPass");

               /* Test.log(LogStatus.PASS, "Test Pass");
                Test.log(LogStatus.INFO, "Snapshot below: " + Test.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/

            }catch(AssertionError e)
            {
                System.out.println(e);
              /*  Test.log(LogStatus.PASS, "Test Failed");
                Test.log(LogStatus.INFO, "Snapshot below: " + Test.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/
            }

        } catch (NullPointerException e) {
            System.out.println(e);
          /*  Test.log(LogStatus.PASS, "Test Faild");
            Test.log(LogStatus.INFO, "Snapshot below: " + Test.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/
        } catch (Throwable e) {
            System.out.println(e);
          /*  Test.log(LogStatus.PASS, "Test Faill");
            Test.log(LogStatus.INFO, "Snapshot below: " + Test.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/
        }
        extent.endTest(Test);
        extent.flush();
        // driver.close();

    }


    @DataProvider

    public Object[][] InputData() throws IOException {
        FileInputStream fis = new FileInputStream("ExcelSheet/Login.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);

        HSSFSheet sheet = workbook.getSheet("Login");
        int rowCount = sheet.getPhysicalNumberOfRows();

        String[][] data = new String[rowCount - 1][4];

        for (int i = 1; i < rowCount; i++) {

            HSSFRow row = sheet.getRow(i);
            HSSFCell UserNameCell = row.getCell(0);
            if (UserNameCell == null) {
                data[i - 1][0] = "";
            } else {
                UserNameCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i - 1][0] = UserNameCell.getStringCellValue();
            }


            HSSFCell PassewordCell = row.getCell(1);
            if (PassewordCell == null) {
                data[i - 1][1] = "";
            } else {
                PassewordCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i - 1][1] = PassewordCell.getStringCellValue();
            }

            HSSFCell ExpetedCell = row.getCell(2);
            if (ExpetedCell == null) {
                data[i - 1][2] = "";
            } else {
                ExpetedCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i - 1][2] = ExpetedCell.getStringCellValue();
            }

            HSSFCell XpathCell = row.getCell(3);
            if (XpathCell == null) {
                data[i - 1][3] = "";
            } else {
                XpathCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i - 1][3] = XpathCell.getStringCellValue();
            }

        }
        return data;
    }


    @Test(dataProvider ="ValidationTest")
    public void validationTest(String Expected,String Xpath) throws IOException {
        ExtentTest vaidtest=extent.startTest("Vaidation Test","To test the functionality uisng validaton");
        try {
            login login = new login(driver1);
            vaidtest.log(LogStatus.PASS, "Load login driver");
            login.ClickLogin_Button();
            vaidtest.log(LogStatus.PASS, "Click on Login  Button");

            try {
                String Actual=driver1.findElement(By.xpath(Xpath)).getText();
                Assert.assertEquals(Actual,Expected,"Test Pass");


            } catch (Throwable e) {
                System.out.println(e);
                vaidtest.log(LogStatus.PASS, "Test fail");

/*
                vaidtest.log(LogStatus.INFO, "Snapshot below: " + vaidtest.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/

            }
        }catch(AssertionError e)
        {
            System.out.println(e);
            vaidtest.log(LogStatus.PASS, "Test fail");

/*
            vaidtest.log(LogStatus.INFO, "Snapshot below: " + vaidtest.addScreenCapture("./screenshots/"+takeScreenshot(driver)));
*/

        }
        extent.endTest(vaidtest);
        extent.flush();
        driver1.close();

    }


    @DataProvider
    public Object[][] ValidationTest() throws IOException {
        FileInputStream fis=new FileInputStream("ExcelSheet/Login.xls");

        HSSFWorkbook workbook=new HSSFWorkbook(fis);
        HSSFSheet sheet=workbook.getSheet("Validation");

        int Rowcount= sheet.getPhysicalNumberOfRows();
        String[][] data = new String[Rowcount - 1][2];
        for(int j=1;j<Rowcount;j++)
        {
            HSSFRow row=sheet.getRow(j);
            HSSFCell ExpetedCell=row.getCell(0);
            if(ExpetedCell==null)
            {
                data[j-1][0]="";
            }else
            {
                ExpetedCell.setCellType(Cell.CELL_TYPE_STRING);
                data[j-1][0]=ExpetedCell.getStringCellValue();
            }

            HSSFCell XpathCell=row.getCell(1);
            if(XpathCell==null)
            {
                data[j-1][1]="";
            }else
            {
                XpathCell.setCellType(Cell.CELL_TYPE_STRING);
                data[j-1][1]=XpathCell.getStringCellValue();
            }
        }


        return data;
    }




}
