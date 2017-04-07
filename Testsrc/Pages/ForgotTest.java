package Pages;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
 * Created by AKSHAY on 3/23/2017.
 */
public class ForgotTest {
    WebDriver driver;


    @BeforeMethod
    public void LodeDriver()
    {
        driver= openbrowser("chrome");
        getUrl("url");
    }


    @Test(dataProvider = "ForgotData")
    public void forgotpasswordtest(String EmailID,String Expected,String Xpath) throws IOException {
        try{

            Forgot forgot =new Forgot(driver);
            forgot.clickForgotlink();
            forgot.setEmailBox(EmailID);
            forgot.ClickSendButtonl();
        try{

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String Actual=driver.findElement(By.xpath(Xpath)).getText();
            Assert.assertEquals(Actual,Expected,"Test Pass");

            if (Actual!=Expected)
            {
                System.out.println("Test fail");
            }else {
                System.out.println("Test Pass");
            }



        }
        catch(Throwable e)

        {
            System.out.println(e);

        }
    }catch(AssertionError e)
        {
            System.out.println(e);

        }

        driver.close();

    }

    @DataProvider

    public Object [][] ForgotData() throws IOException

    {

        FileInputStream fis=new FileInputStream("ExcelSheet/forgot.xls");

        HSSFWorkbook workbook=new HSSFWorkbook(fis);

        HSSFSheet sheet=workbook.getSheet("forgot");

        int rowCount=sheet.getPhysicalNumberOfRows();

        String [][] data =new String[rowCount-1][3];

        for(int i=1;i<rowCount;i++)
        {
            HSSFRow row=sheet.getRow(i);

            HSSFCell EmailCell=row.getCell(0);
            if(EmailCell==null)
            {
                data[i-1][0]="";
            }
            else
            {
                EmailCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][0]=EmailCell.getStringCellValue();
            }

            HSSFCell ExpectedCell=row.getCell(1);
            if (ExpectedCell==null)
            {
                data[i-1][1]="";
            }
            else
            {
                ExpectedCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][1]=ExpectedCell.getStringCellValue();
            }

            HSSFCell XpathCell=row.getCell(2);
            if(XpathCell==null)
            {
                data[i-1][3]="";

            }
                else
            {
                XpathCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][2]=XpathCell.getStringCellValue();
            }
        }


        return data;
    }

}
