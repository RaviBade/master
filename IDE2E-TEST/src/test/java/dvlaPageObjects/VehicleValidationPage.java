package dvlaPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleValidationPage {

	WebDriver driver;
	WebElement vehicleMake;
	WebElement vehicleColour;
	
	public VehicleValidationPage(WebDriver conDriver) {
		this.driver=conDriver;
	}
	
	public String getVehicleMake() {
		vehicleMake = driver.findElement(By.xpath(".//div[@id='pr3']/div/ul/li[2]/span[2]/strong"));
		return vehicleMake.getText();
	}

	public String getVehicleColur() {
		vehicleColour = driver.findElement(By.xpath(".//div[@id='pr3']/div/ul/li[3]/span[2]/strong"));
		return vehicleColour.getText();
		
	}
	
}
