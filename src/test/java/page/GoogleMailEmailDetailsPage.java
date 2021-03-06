package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleMailEmailDetailsPage extends AbstractGoogleMailPage {

    @FindBy(xpath = "(//div[@title='Удалить'])[2]")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "(//div[@title='Переместить в'])[3]")
    private WebElement moveToFolderButton;

    @FindBy(xpath = "//div[text()='Входящие']")
    private WebElement moveToInboxButton;

    @FindBy(xpath = "//span[@class='bAq']")
    private WebElement actionConfirmationPopup;

    @FindBy(xpath = "//*[@id=':7z']/span")
    private WebElement emailAddresseeTextField;

    @FindBy(xpath = "//h2[@class='hP']")
    private WebElement emailSubjectTextField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement emailBodyTextField;

    @FindBy(xpath = "//div[contains(@aria-label, 'Удалить черновик')]")
    private WebElement deleteDraftEmailButton;

    public GoogleMailEmailDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getActionConfirmationPopup() {
        return actionConfirmationPopup;
    }

    public WebElement getEmailAddresseeTextField() {
        return emailAddresseeTextField;
    }

    public WebElement getEmailBodyTextField() {
        return emailBodyTextField;
    }

    public GoogleMailHomePage deleteEmail() {
        wait.until(ExpectedConditions.visibilityOf(deleteEmailButton));
        deleteEmailButton.click();
        log.info("Email deleted");
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage moveEmailFromTrashBinToInbox() {
        wait.until(ExpectedConditions.visibilityOf(moveToFolderButton));
        moveToFolderButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(moveToInboxButton));
        moveToInboxButton.click();
        log.info("Email moved to inbox");
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailDraftsPage deleteDraftEmailWithHotKeys() {
        new Actions(driver).keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("d")
                .keyUp(Keys.CONTROL).keyUp(Keys.SHIFT)
                .build().perform();
        log.info("Draft email deleted");
        return new GoogleMailDraftsPage(driver);
    }
}
