package dvlaPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleConfirmationPage {

	WebDriver driver;
	WebElement RegNumberTextbox;
	WebElement ContinueButton;
	
	public VehicleConfirmationPage(WebDriver conDriver) {
		this.driver=conDriver;
	}
	
	public void enterVehicleRegistrationNumber(String regNumber) {
		
		RegNumberTextbox = driver.findElement(By.xpath(".//input[@id='Vrm']"));
			RegNumberTextbox.sendKeys(regNumber);
	}

	public void NavigateToVehicleEnquiryPage() {
		ContinueButton = driver.findElement(By.xpath(".//button[@name='Continue']"));
		ContinueButton.click();
	}

}
