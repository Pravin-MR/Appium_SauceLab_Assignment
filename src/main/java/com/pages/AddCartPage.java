package com.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.qa.BaseTest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AddCartPage extends BaseTest {

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")
	private MobileElement addToCart;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement cartIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"YOUR CART\"]")
	private MobileElement yourCart;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]")
	private MobileElement removeCart;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE SHOPPING\"] ")
	private MobileElement continueShopping;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]")
	private MobileElement checkout;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CHECKOUT: INFORMATION\"]")
	private MobileElement checkoutInformation;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-First Name\"]")
	private MobileElement firstName;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Last Name\"]")
	private MobileElement lastName;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]")
	private MobileElement postalCode;	

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]")
	private MobileElement continueBtn;	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"CHECKOUT: OVERVIEW\"]")
	private MobileElement checkoutOverview;
	
	@AndroidFindBy(accessibility = "test-FINISH")
	private MobileElement finishBtn;	
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]")
	private MobileElement confirmMsg1;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[2]")
	private MobileElement confirmMsg2;




	public void addItemToCart() {
		click(addToCart);
		click(cartIcon);
		waitForVisibility(yourCart);
		
	}
	
	public void removeItem() {
		click(removeCart);
		click(continueShopping);
		waitForVisibility(addToCart);
		addItemToCart();
	}
	

	public void checkoutItem() {
		click(checkout);
		waitForVisibility(checkoutInformation);
		
	}
	
	public void enterCheckoutDetails(String fn,String ln,String pc) {
		clear(firstName);
		clear(lastName);
		clear(postalCode);
		sendKeys(firstName, fn);
		sendKeys(lastName, ln);
		sendKeys(postalCode, pc);
		click(continueBtn);
	}
	
	public void clickFinish() {
		
		waitForVisibility(checkoutOverview);
		
		TouchAction t = new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
		int startx = size.width / 2;
		int startY = (int) (size.height * 0.7);
		int endY = (int) (size.height * 0.3);
		By WebView = MobileBy.AccessibilityId("test-FINISH");
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				if (driver.findElement(WebView).isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				t.press(PointOption.point(startx, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
						.moveTo(PointOption.point(startx, endY)).release().perform();
			}
		click(finishBtn);	
		
		}
		
	}
	
	public void validateConfirmMsg() {
		waitForVisibility(confirmMsg1);
		waitForVisibility(confirmMsg2);
		
	}
	
	public String getconfirmMsg1() {

		String Msg1 = getText(confirmMsg1);
		return Msg1;
	}
	
	public String getconfirmMsg2() {
		
		String Msg2 = getText(confirmMsg2);
		return Msg2;
	}
	
}
