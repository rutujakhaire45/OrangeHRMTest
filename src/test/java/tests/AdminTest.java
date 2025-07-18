package tests;

import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class AdminTest extends BaseTest {

    @Test(priority = 1)
    public void validateLeftMenuAndNavigateToAdmin() {
        test = extent.createTest("Validate Left Menu Count and Click Admin");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        AdminPage adminPage = new AdminPage(driver);
        int count = adminPage.getLeftMenuOptionsCount();
        test.info("Menu Option Count: " + count);
        assertEquals(count, 12, "Menu option count should be 12");

        adminPage.clickAdminMenu();
        test.pass("✅ Clicked Admin from Menu Successfully");
    }

    @Test(priority = 2)
    public void searchByUsername() {
        test = extent.createTest("Search By Username");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();
        adminPage.searchByUsername("Admin");
        test.pass("✅ Searched by Username successfully");
    }

    @Test(priority = 3)
    public void searchByUserRole() {
        test = extent.createTest("Search By User Role");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();
        adminPage.searchByUserRole("Admin");
        test.pass("✅ Searched by Role successfully");
    }

    @Test(priority = 4)
    public void searchByUserStatus() {
        test = extent.createTest("Search By User Status");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();
        adminPage.searchByUserStatus("Enabled");
        test.pass("✅ Searched by Status successfully");
    }
}
