package pageObject;

	import java.util.ArrayList;
	import java.util.List;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	
	public class FirstPage extends BasePage {
		
	    WebDriver driver;
		public FirstPage(WebDriver driver){
			super(driver);
		}
		
		@FindBy(xpath="//div[@class='ohcfXYh6LUBy5DS5kNUjRQ==']")
		WebElement user;
		
		@FindBy(xpath="//div[@id='mectrl_currentAccount_primary']")
		WebElement nname;
		
		@FindBy(xpath="//div[@id='mectrl_currentAccount_secondary']")
		WebElement mmail;
		
		@FindBy(xpath="//strong[text()='Around Cognizant']")
		public
		WebElement scroll;
		
		@FindBy(xpath="//a[@target='_blank'][@style='-webkit-line-clamp: 2;']")
		public
		List<WebElement> news;
		
		@FindBy(xpath="//strong[text()='See all']")
		public
		WebElement seeAll;
		
		public void clickUser() throws InterruptedException {
			user.click();
			Thread.sleep(5000);
			user.click();
		}
		
		public String getUserName() {
			String name = nname.getText();
			return name;
		}
		public String getUserMail() {
			String mail = mmail.getText();
			return mail;
		}
		
		public List<String>printtNews(){
			
			List<String> el = new ArrayList<String>();
			for(WebElement ll:news) {
				el.add(ll.getText());
			}	
			return el;
		}
		
		public void clickSee() {
			seeAll.click();
		}
		
		
	}


