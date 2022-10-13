package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class NewCaller extends BaseClass {

	@BeforeTest   //setting the name of the excel to be read
	public void setexcelname() {
		excelname = "ServiceNow2";
	}


	@Test(dataProvider="newcallerdata") //Receiving the data from excel using @dataprovider and passing it as parameters
	public void runnewcaller(String firstname,String lastname,String email,String mobno,String profile) throws InterruptedException {

		//Click-All under shadow root
		Shadow shad=new Shadow(driver);
		shad.findElementByXPath("//div[text()='All']").click();
		Thread.sleep(3000);
		//Enter Callers in filter navigator and press enter 
		WebElement filter = shad.findElementByXPath("//input[@id='filter']");
		filter.sendKeys("caller");
		Thread.sleep(1000);
		filter.sendKeys(Keys.ENTER);

		//Click new button in frame under shadow root
		WebElement frame = shad.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		//fill in the input fields
		driver.findElement(By.id("sys_user.first_name")).sendKeys(firstname);
		driver.findElement(By.id("sys_user.last_name")).sendKeys(lastname);
		driver.findElement(By.id("sys_user.email")).sendKeys(email);
		driver.findElement(By.id("sys_user.phone")).sendKeys(mobno);

		driver.findElement(By.id("lookup.sys_user.title")).click(); //why no frame
		//handle the window and click the input title from excel
		Set<String> handles = driver.getWindowHandles();
		List<String> list=new ArrayList<String>(handles);
		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("//a[text()='"+profile+"']")).click();
		//get back to main window
		driver.switchTo().window(list.get(0));
		Thread.sleep(3000);
		//click 'Submit'.
		driver.switchTo().frame(frame);
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		//Search and verify the newly created Caller"
		WebElement check = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		check.sendKeys(firstname);
		check.sendKeys(Keys.ENTER);
		WebElement table = driver.findElement(By.xpath("//tbody[@class='list2_body']//tr[1]//td[4]"));
		System.out.println(table.getText());
		if(table.getText().equals(firstname)) {
			System.out.println("New caller is created successfully");
		}else {
			System.out.println("No new caller");
		}

	}

}
