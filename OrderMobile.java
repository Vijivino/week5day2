package week5.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class OrderMobile extends BaseClass{
@Test
public void runordermobile() throws InterruptedException {
	
//Click-All in the shadow dom
	Shadow shad=new Shadow(driver);
	shad.findElementByXPath("//div[text()='All']").click();
	Thread.sleep(3000);
//Enter Service catalog in filter and press enter 
	shad.findElementByXPath("//span[text()='Service Catalog']").click();
	Thread.sleep(3000);
//Click on  mobiles inside the frame in shadow dom
	WebElement frame = shad.findElementByXPath("//iframe[@title='Main Content']");
	driver.switchTo().frame(frame);
	driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
	Thread.sleep(3000);
//Select Apple iphone 13 pro
	driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']")).click();
	Thread.sleep(3000);
//update mandatory field is replaced
	driver.findElement(By.xpath("//label[text()='No']")).click();
//monthly data allowance dropdown	
	WebElement sel = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
	Select select=new Select(sel);
	select.selectByValue("unlimited");
//Update color field to rose gold 
	driver.findElement(By.xpath("//label[text()='Gold']")).click();	
//Select  Order now option
	driver.findElement(By.id("oi_order_now_button")).click();
	Thread.sleep(3000);
//Verify order is placed and copy the request number"
	String text = driver.findElement(By.xpath("//div[@class='notification notification-success']/span")).getText();
	if(text.contains("submitted")) {
		System.out.println("Order is placed");
		System.out.println("Success message : " +text);
	}
	
	String text2 = driver.findElement(By.id("requesturl")).getText();
	System.out.println("Request Number is "+text2);
	
}


}
