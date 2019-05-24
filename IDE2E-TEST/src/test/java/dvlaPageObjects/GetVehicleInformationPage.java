package dvlaPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GetVehicleInformationPage {

	WebDriver driver;
	
	public GetVehicleInformationPage(WebDriver conDriver) {
		this.driver=conDriver;
	}
	
	
	public void NavigateToVehicleConfirmationPage() {
		driver.findElement(By.xpath(".//*[@id='get-started']/a")).click();
	}

}
