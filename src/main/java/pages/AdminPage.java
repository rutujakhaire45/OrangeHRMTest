package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {

    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // LEFT MENU ITEMS
    @FindBy(css = "ul.oxd-main-menu li")
    List<WebElement> leftMenuItems;

    // ADMIN MENU
    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminMenu;

    // Username textbox
    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div/input")
    WebElement usernameInput;

    // User Role dropdown
    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement userRoleDropdown;

    // Status dropdown
    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement statusDropdown;

    // Auto-suggestion after typing name
    @FindBy(xpath = "//div[@role='listbox']//span[contains(text(),'Paul Collings')]")
    WebElement autoSuggestedName;

    // Search button
    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchButton;

    // Reset button
    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement resetButton;

    // Search result rows
    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> searchResultRows;

    // ======================= METHODS =======================

    public int getLeftMenuOptionsCount() {
        List<WebElement> menuOptions = wait.until(ExpectedConditions
            .visibilityOfAllElementsLocatedBy(By.cssSelector("ul.oxd-main-menu > li")));
        return menuOptions.size();
    }

    public void clickAdminMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public void searchByUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        System.out.println("Total Records Found: " + searchResultRows.size());
        resetButton.click();
    }

    public void searchByUserRole(String roleName) {
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        WebElement roleOption = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + roleName + "']"));
        wait.until(ExpectedConditions.visibilityOf(roleOption)).click();
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        System.out.println("Total Records Found: " + searchResultRows.size());
        resetButton.click();
    }

    public void searchByUserStatus(String status) {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        WebElement statusOption = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + status + "']"));
        wait.until(ExpectedConditions.visibilityOf(statusOption)).click();
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultRows));
        System.out.println("Total Records Found: " + searchResultRows.size());
        resetButton.click();
    }
}
