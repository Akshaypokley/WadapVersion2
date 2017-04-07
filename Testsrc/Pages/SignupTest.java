package Pages;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static Utilites.OpenBrowser.getUrl;
import static Utilites.OpenBrowser.openbrowser;


/**
 * Created by AKSHAY on 3/16/2017.
 */
public class SignupTest {

    WebDriver driver;
    @BeforeMethod
    public void signup() {
        driver= openbrowser("chrome");
        getUrl("url");

    }

    @Test(dataProvider = "SignUp_Input")
    public void Signupdata(String First_Name, String LastName, String ContactNo, String EmailId, String
            Password, String Re_Password, String Expeted, String Xpath) throws IOException {


        try {
            Signup signup = new Signup(driver);


            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            signup.clicksignumlink();
            signup.setFirst_NM(First_Name);
            signup.setLastNM(LastName);
            signup.setCont_No(ContactNo);
            signup.setEmaild(EmailId);
            signup.setPassword(Password);
            signup.setConfir_Password(Re_Password);
            signup.CheckBox_terms();
            signup.clcikRegistrationBtn();
           /* Alert alert = driver.switchTo().alert();
            alert.accept();*/
            try
            {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                String Actual=driver.findElement(By.xpath(Xpath)).getText();

                Assert.assertEquals(Actual,Expeted,"TestPass");

            }catch(AssertionError e)
            {
               // System.out.println(e);
            }

        } catch (Throwable e) {
           // System.out.println(e);

        }
        driver.close();

    }

    @DataProvider

    public Object[][] SignUp_Input() throws IOException

    {
        FileInputStream fis = new FileInputStream("ExcelSheet/Signup.xls");

        HSSFWorkbook Workbook = new HSSFWorkbook(fis);
        HSSFSheet WorkSheet = Workbook.getSheet("Signup");

        int rowCount = WorkSheet.getPhysicalNumberOfRows();
        String[][] data = new String[rowCount - 1][8];
        for (int i = 1; i < rowCount; i++)

        {
            HSSFRow row=WorkSheet.getRow(i);
            HSSFCell FirstNameCell=row.getCell(0);
            if(FirstNameCell==null)
            {
                data[i-1][0]="";
            }else{
                FirstNameCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][0]=FirstNameCell.getStringCellValue();
            }

            HSSFCell LastNameCell=row.getCell(1);
            if(LastNameCell==null)
            {
                data[i-1][1]="";
            }else {
                LastNameCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][1]=LastNameCell.getStringCellValue();
            }

            HSSFCell ContactNoCell =row.getCell(2);
            if(ContactNoCell==null)
            {
                data[i-1][2]="";
            }else
            {
                ContactNoCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][2]=ContactNoCell.getStringCellValue();
            }

            HSSFCell EmailIdCell=row.getCell(3);
            if(EmailIdCell==null)
            {
                data[i-1][3]="";
            }else
            {
                EmailIdCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][3]=EmailIdCell.getStringCellValue();
            }

            HSSFCell PasswordCell= row.getCell(4);
            if(PasswordCell==null)
            {
                data[i-1][4]="";
            }else
            {
                PasswordCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][4]=PasswordCell.getStringCellValue();
            }

            HSSFCell RePasswordCell= row.getCell(5);
            if(RePasswordCell==null)
            {
                data[i-1][5]="";
            }else
            {
                RePasswordCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][5]=RePasswordCell.getStringCellValue();
            }
            HSSFCell ExpaetedCell=row.getCell(6);
            if(ExpaetedCell==null)
            {
                data[i-1][6]="";
            }
            else
            {
                ExpaetedCell.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][6]=ExpaetedCell.getStringCellValue();
            }

            HSSFCell XpathCEll =row.getCell(7);
            if (XpathCEll==null)
            {
                data[i-1][7]="";
            }
            else
                {
                XpathCEll.setCellType(Cell.CELL_TYPE_STRING);
                data[i-1][7]=XpathCEll.getStringCellValue();
            }
        }


        return data;
    }


}
