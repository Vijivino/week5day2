package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class FillMandatory extends BaseClass{
@Test
	public void runfillmandatory() throws InterruptedException {
		// TODO Auto-generated method stub

		//click all
		Shadow shad=new Shadow(driver);
		shad.findElementByXPath("//div[text()='All']").click();
		Thread.sleep(3000);
		//Enter Knowledge  in filter navigator and press enter
		WebElement filter = shad.findElementByXPath("//input[@id='filter']");
		filter.sendKeys("knowledge");
		Thread.sleep(1000);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(7000);
		//Create new Article 
		WebElement frame = shad.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(5000);
		WebElement create = driver.findElement(By.xpath("//button[@data-original-title='Create an Article']/span"));
		driver.executeScript("arguments[0].click()", create);
		//Select knowledge base as IT
		driver.findElement(By.id("lookup.kb_knowledge.kb_knowledge_base")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> list=new ArrayList<String>(handles);
		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("//a[text()='IT']")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(frame);
		//select category as IT- java and Click Ok
		driver.findElement(By.id("lookup.kb_knowledge.kb_category")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='IT']")).click();
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(5000);
		//Update the short description
		driver.findElement(By.id("kb_knowledge.short_description")).sendKeys("selenium");
		//enter the article body inside a frame
		driver.switchTo().frame("kb_knowledge.text_ifr");
		driver.findElement(By.xpath("//body[@class='mce-content-body ']")).sendKeys("It is an automation tool");
		Thread.sleep(5000);
		driver.switchTo().parentFrame();
		//driver.switchTo().frame(frame);//switch back to parent frame
		Thread.sleep(5000);
		//Select the submit option
		driver.findElement(By.id("sysverb_insert")).click();

		System.out.println("New article is created");
		
	}

}
