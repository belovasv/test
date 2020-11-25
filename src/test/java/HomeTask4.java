import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Feature("Проверка создания issue")
@Tag("gitHubIssue")
@Owner("SV")
public class HomeTask4 {

    String issueTitle = "test";
    String issueText = "bla-bla-bla";
    String repo = "/belovasv/test";
    Login login = new Login();

    @BeforeEach
    public void SetUp() {
        Selenide.clearBrowserCookies();
        Configuration.baseUrl = "https://github.com";
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @DisplayName("Селениде тест")
    @Test
    public void issueSelenide() {
        open("/");
        $(By.linkText("Sign in")).click();
        $("#login_field").setValue(login.getUser());
        $("#password").setValue(login.getPassword());
        $("[type=submit]").click();
        open(repo);
        $("[data-tab-item=issues-tab]").click();
        $(By.linkText("New issue")).click();
        $("#issue_title").setValue(issueTitle);
        $("#issue_body").setValue(issueText);
        $(".flex-justify-end").$("[type=submit").click();
        $(".gh-header-title").shouldHave(Condition.text(issueTitle));
        $(".d-block.comment-body").shouldHave(Condition.exactText(issueText));
        $(byText("Delete issue")).click();
        $(byText("Delete this issue")).click();
    }

    @DisplayName("Lambda тест")
    @Test
    public void lambdaTest() {
        step("Логинимся в GitHub", (step) -> {
            open("/");
            $(By.linkText("Sign in")).click();
            $("#login_field").setValue(login.getUser());
            $("#password").setValue(login.getPassword());
            $("[type=submit]").click();
        });
        step("идем по ссылке в репозиторий " + repo, (step) -> {
            open(repo);
        });
        step("кликаем на вкладку issue", (step) -> {
            $("[data-tab-item=issues-tab]").click();
        });
        step("Кликаем на new issue и создаем новое Issue", (step) -> {
            $(By.linkText("New issue")).click();
            $("#issue_title").setValue(issueTitle);
            $("#issue_body").setValue(issueText);
            $(".flex-justify-end").$("[type=submit").click();
        });
        step("Проверяем название и текст issue", (step) -> {
            $(".gh-header-title").shouldHave(Condition.text(issueTitle));
            $(".d-block.comment-body").shouldHave(Condition.exactText(issueText));
        });
        step("Удаляем issue", (step) -> {
            $(byText("Delete issue")).click();
            $(byText("Delete this issue")).click();
        });
    }

    @DisplayName("@Step тест")
    @Test
    public void stepsTest() {
        new GitHubSteps()
                .login(login.getUser(), login.getPassword())
                .goToLink(repo)
                .clickIssue().createNewIssue(issueTitle, issueText)
                .checkNewIssue(issueTitle, issueText)
                .deleteIssue();
    }


}
