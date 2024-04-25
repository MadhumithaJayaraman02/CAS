package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.FirstPage;
import pageObject.SecondPage;
import testBase.BaseClass;
import testBase.ScrollDown;
import utilities.ExcelUtility;


public class TC_001_AroundCognizant extends BaseClass {
	
	List<String> ar=new ArrayList<String>();
	List<String> newsHeader=new ArrayList<String>();
	List<String> newsDesc=new ArrayList<String>();

	    
		@Test(priority=1)
		public void testUser() throws InterruptedException {
			
			try {
				FirstPage fp=new FirstPage(driver);
				Thread.sleep(5000);
				logger.info("***Test case 1 is started***");
				
				fp.clickUser();
				logger.info("***user icon is clicked***");
				
				Thread.sleep(3000);
				logger.info("***getting user name***");
				fp.getUserName();
				
				Thread.sleep(3000);
				System.out.println("**Printing user details**");
				
				Thread.sleep(3000);
				logger.info("***getting user mail***");
				fp.getUserMail();
				System.out.println("userName is :"+ fp.getUserName());
				System.out.println("user mail is :"+ fp.getUserMail());
				
				logger.info("***validating user mail and username***");
				Thread.sleep(5000);
				Assert.assertEquals(fp.getUserName(),"J, Madhumitha (Contractor)");
				Assert.assertEquals(fp.getUserMail(),"2318412@cognizant.com");
				
				logger.info("***Test case 1 is ended***");
			}
			
			catch(Exception e) {
				
				logger.error("***test case 1 gets failed***");
			}	
		}
			
			@Test(priority=2)
			public void checkNewsToolTip() throws InterruptedException, IOException {	
				
				FirstPage fpp;
				ScrollDown s;
				
				fpp=new FirstPage(driver);
				
				s=new ScrollDown(driver);
				
				try {
				   logger.info("**Test case 2 is started***");
				   
				   //Thread.sleep(5000);
				   s.Scrol(fpp.scroll);
				   fpp.printtNews();
				   
				   logger.info("**validating around cognizant section***");
				   Thread.sleep(5000);
				   Assert.assertEquals(fpp.scroll.getText(), "Around Cognizant");
				   
				   Thread.sleep(5000);
				   logger.info("**printing all the news available in the news around cognizant section***");
				   System.out.println("News around Cognizant section are");
				   System.out.println();
				   System.out.println();
				   ar.addAll(fpp.printtNews());
				   System.out.println(fpp.printtNews());
				   
				   for(WebElement ll:fpp.news) {
						String tooltip=ll.getAttribute("title");
						String header=ll.getText();
						if(tooltip.equals(header)) {
						  System.out.println("Header and ToolTip of news around cognizant are same");
						  logger.info("**validating header and tool tip of news around cognizant section***");
						  Thread.sleep(5000);
						  Assert.assertEquals(tooltip, header);
						  break;
						}
						else {
							System.out.println("Header and ToolTip are same not same");
						}
						
					}
				   logger.info("**test case 2 ended***");
				}
				
				catch(Exception e) {
					logger.error("**test case 2 gets failed***");
				}
		}
			
			@Test(priority=3)
			public  void checkAllNews() throws InterruptedException, IOException {
				
				FirstPage fp;
				SecondPage sp;
				
				fp=new FirstPage(driver);
				
				ScrollDown sd=new ScrollDown (driver);
				
				sp=new SecondPage(driver);
				
				
				try {
					logger.info("*** test case 3 is started");
					
					sd.Scrol(fp.seeAll);
					Thread.sleep(3000);
					
					fp.clickSee();			
					Thread.sleep(3000);
					
					sp.printTwoNews();
					
					logger.info("***printing all the news headers from news section***");
					System.out.println("News after clicking see All");
					System.out.println();
					System.out.println(sp.printTwoNews());
					newsHeader.addAll(sp.printTwoNews());
					 for(WebElement i:sp.second_news) {
							String tooltip=i.getAttribute("title");
							String header=i.getText();
							if(tooltip.equals(header)) {
							  System.out.println("***News section and tooltip of news after clicking see All are same***");
							  logger.info("***validating header of news section and tool tip of the every news***");
							  Thread.sleep(5000);
							  Assert.assertEquals(tooltip, header);
							  break;
							}
							else {
								System.out.println("***News section and tooltip of news after clicking see All are not same***");
							}
							
						}
				 logger.info("*** test case 3 is ended");
				}
				
				catch(Exception e) {
					logger.error("*** test case 3 gets failed");
					}
  }

			@Test(priority=4)
			public void lastTest() throws IOException, InterruptedException {
				ScrollDown sd=new ScrollDown(driver);
				
				JavascriptExecutor js;
				
				try {
					logger.info("***Test case 4 is started***");
					
				for (int i=1;i<6;i++) {
					
					js = (JavascriptExecutor)driver;
					WebElement SecondNews = driver.findElement(By.xpath("(//a[@data-automation-id='newsItemTitle'][1])["+i+"]"));			
					
					sd.Scrol(SecondNews);
					Thread.sleep(3000);
					String href = SecondNews.getAttribute("href");
					
					// Open a new tab
					logger.info("***opening a new tab***");
					js.executeScript("window.open()");
					
					// Switch to new tab
					logger.info("***switching to new tab***");
					Set<String>window=driver.getWindowHandles();
					List<String>tabs=new ArrayList<String>(window);
					
					String parent=tabs.get(0);
					String child=tabs.get(1);
					
					driver.switchTo().window(child);
					
					// Navigate to new URL
					logger.info("***navigating to new URL***");
					driver.get(href);
			
					Thread.sleep(5000);
					
					String News = driver.findElement(By.xpath("//*[@data-automation-id='textBox']")).getText();
					logger.info("***Printing the description of the top five news ***");
					
					System.out.println();
					System.out.println();
					
					newsDesc.add(News);
					System.out.println(News);

					
					System.out.println(News);
					driver.close();	
					
					Thread.sleep(3000);
					driver.switchTo().window(parent);

					Thread.sleep(3000);
					
			}
				
				Thread.sleep(5000);
				Assert.assertTrue(true);
				logger.info("***Testcase 4 is ended***");
			
			}catch(Exception e) {
				logger.error("test case 4 gets failed");
				
			}
				
				Thread.sleep(5000);	
				ExcelUtility.output(ar, newsHeader, newsDesc);
				ExcelUtility.closeExcel();

	  }
}
		


	

