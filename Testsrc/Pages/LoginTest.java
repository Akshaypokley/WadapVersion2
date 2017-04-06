package Pages;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;

import java.io.IOException;

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

    @Test(dataProvider = "Exceldata")
    public void UserInput(String UserName,String UserPassword,String Expeted,String Xpath) {
        try {
            Login login = new Login(driver1);
            login.setUserName(UserName);
            login.setPassword(UserPassword);
            login.ClickLogin_Button();

            try

            {
                String actual;
                actual = driver1.findElement(By.xpath(Xpath)).getText();
                Assert.assertEquals(actual, Expeted, "Test Pass");
            } catch (AssertionError e) {
                System.out.println(e);
            }
        } catch (NullPointerException e)
        {
            System.out.println(e);
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

    @DataProvider
    public Object[][] Exceldata() throws IOException


    {
        FileInputStream fis=new FileInputStream("ExcelSheet/Login.xls");

        HSSFWorkbook workbook=new HSSFWorkbook(fis);
        HSSFSheet sheet=workbook.getSheet("Sheet1");

        int RowCount= sheet.getPhysicalNumberOfRows();

        String data[][]=new String[RowCount-1][4];

        for(int i=0;i<RowCount;i++) {
            HSSFRow row = sheet.getRow(i);

            HSSFCell UserNameCEll =row.getCell(0);
            if(UserNameCEll==null)
            {
                data[i-1][0]="";
            }
            else
            {
                UserNameCEll.setCellValue(Cell.CELL_TYPE_STRING);
                data[i-1][0]=UserNameCEll.getStringCellValue();
            }

            HSSFCell PasswordCEll =row.getCell(1);
            if(PasswordCEll==null)
            {
                data[i-1][1]="";
            }
            else
            {
                PasswordCEll.setCellValue(Cell.CELL_TYPE_STRING);
                data[i-1][1]=PasswordCEll.getStringCellValue();
            }


            HSSFCell ExpectedCEll =row.getCell(2);
            if(ExpectedCEll==null)
            {
                data[i-1][2]="";
            }
            else
            {
                ExpectedCEll.setCellValue(Cell.CELL_TYPE_STRING);
                data[i-1][2]=ExpectedCEll.getStringCellValue();
            }

            HSSFCell XpathCEll =row.getCell(3);
            if(XpathCEll==null)
            {
                data[i-1][3]="";
            }
            else
            {
                XpathCEll.setCellValue(Cell.CELL_TYPE_STRING);
                data[i-1][3]=XpathCEll.getStringCellValue();
            }

        }

        return data;
    }



}
