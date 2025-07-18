package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtil;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        String filePath = "testdata/LoginData.xlsx";
        return ExcelUtil.getTestData(filePath, "Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        test = extent.createTest("Login Test: " + username);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        try {
            Thread.sleep(3000); // Wait for page to load
            String currentURL = driver.getCurrentUrl();

            if (username.equals("Admin") && password.equals("admin123")) {
                assertTrue(currentURL.contains("dashboard"), "Login should succeed");
                test.pass("✅ Login passed for: " + username);
            } else {
                assertTrue(currentURL.contains("auth/login"), "Login should fail");
                test.pass("❌ Login failed as expected for: " + username);
            }

        } catch (Exception e) {
            test.fail("⚠️ Test error for: " + username + " - " + e.getMessage());
        }
    }
}
