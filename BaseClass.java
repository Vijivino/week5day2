package week5.day2;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
  public ChromeDriver driver;
  public String excelname;
  
  @Parameters({"url","username","password"})
  @BeforeMethod
  public void beforeMethod(String url,String username,String password) throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.findElement(By.id("user_name")).sendKeys(username);
	  driver.findElement(By.id("user_password")).sendKeys(password);
	  driver.findElement(By.id("sysverb_login")).click();
	  Thread.sleep(15000);
	  
  }
  
  @AfterMethod
  public void aftermethod() {
	  driver.close();
  }
  
  @DataProvider(name="newcallerdata") //fetching the data from excel file
  public String[][] fetch() throws IOException{
  	
	  /*String[][] data=new String[1][3];
      data[0][0]="vijayalaxmi";
  	  data[0][1]="R";
  	  data[0][2]="88880000";
  	  */
  	return readexceldata(excelname); //calling the readexceldata method to read the excel and giving to @test
  	  
  } 
 
  //method to read data from excel file 
  public String[][] readexceldata(String excelname) throws IOException{
	
	  XSSFWorkbook book=new XSSFWorkbook("./data/"+excelname+".xlsx");
	  XSSFSheet sheet = book.getSheetAt(1);
	  int RowNum = sheet.getLastRowNum();
	  short cellNum = sheet.getRow(0).getLastCellNum();
	  String[][] data=new String[RowNum][cellNum];
	  
	  for(int i=1;i<=RowNum;i++) {
		  XSSFRow row = sheet.getRow(i);
		    for(int j=0;j<cellNum;j++) {
		    	XSSFCell cell = row.getCell(j);
		    	String stringCellValue = cell.getStringCellValue();
		    	data[i-1][j]=stringCellValue;
		    	System.out.println(data);
		    }
	  }
	  
	  book.close();
	  return data;
	  
  }

}
