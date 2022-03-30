package com.qa.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.pages.ProductDetailPage;
import com.pages.ProductPage;
import com.pages.AddCartPage;
import com.qa.BaseTest;

public class AddCart extends BaseTest {

	LoginPage loginPage;
	ProductPage productPage;
	ProductDetailPage productDetailPage;
	AddCartPage AddCartPage;
	InputStream details;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws FileNotFoundException {

		details = new FileInputStream("C:\\Users\\PravinMR\\eclipse-workspace\\SauceLabTestFramework\\SauceLabTool\\src\\test\\resources\\Data\\data.json");
		JSONTokener jsonToken = new JSONTokener(details);
		loginUsers = new JSONObject(jsonToken);
		closeApp();
		launchApp();

	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("*************** starting Test: " + m.getName() + "   *****************");
		loginPage = new LoginPage();
		productPage = new ProductPage();
		AddCartPage = new AddCartPage();
	}

	
	@Test(priority=1)

	public void validUser() {

		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		loginPage.pressLogin();

		String actualProductTitle = productPage.getTitle();
		String expectedProductTitle = "PRODUCTS";

		Assert.assertEquals(actualProductTitle, expectedProductTitle);
	}
	
	@Test(priority=2)
	public void addToCart() {

		AddCartPage.addItemToCart();		
		AddCartPage.removeItem();		
		AddCartPage.checkoutItem();
		AddCartPage.enterCheckoutDetails("abc","def","400087");
		
		AddCartPage.clickFinish();
		
		
		String actualConfirmMsg1= AddCartPage.getconfirmMsg1();
		String expectedConfirmMsg1 = "THANK YOU FOR YOU ORDER";
		Assert.assertEquals(actualConfirmMsg1, expectedConfirmMsg1);
		
		String actualConfirmMsg2 = AddCartPage.getconfirmMsg2();
		String expectedConfirmMsg2 = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		Assert.assertEquals(actualConfirmMsg2, expectedConfirmMsg2);
		
	}

	@AfterClass
	public void afterClass() throws IOException {

		details.close();

	}

}
