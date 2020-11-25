import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Feature("Проверка создания issue")
@Tag("gitHubIssue")
@Owner("SV")
public class HomeTask4 {

    String issueTitle = "test";
    String issueText = "bla-bla-bla";
    String assignees = "belovasv";
    String repo = "/" + assignees  +"/test";
    String labels = "bug";
    Login login = new Login();


    @BeforeEach
    public void SetUp() {
        Selenide.clearBrowserCookies();
        Configuration.baseUrl = "https://github.com";
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
        Configuration.browserSize = "1920x1080";
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
        $("#assignees-select-menu").click();
        $(".select-menu-item").$(byText(assignees)).click();
        $("#assignees-select-menu").click();
        $("#labels-select-menu").click();
        $(".js-filterable-issue-labels").$(byText(labels)).click();
        $("#labels-select-menu").click();
        $(byText("Submit new issue")).click();
        $(".gh-header-title").shouldHave(Condition.text(issueTitle));
        $(".d-block.comment-body").shouldHave(Condition.exactText(issueText));
        $(".js-issue-assignees").shouldHave(Condition.exactText(assignees));
        $(".js-issue-labels").shouldHave(Condition.exactText(labels));
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
        step("идем по ссылке в репозиторий ", (step) -> {
            open(repo);
        });
        step("кликаем на вкладку issue", (step) -> {
            $("[data-tab-item=issues-tab]").click();
        });
        step("Кликаем на new issue и создаем новое Issue c Labels и assignees", (step) -> {
            $(By.linkText("New issue")).click();
            $("#issue_title").setValue(issueTitle);
            $("#issue_body").setValue(issueText);
            $("#assignees-select-menu").click();
            $(".select-menu-item").$(byText(assignees)).click();
            $("#assignees-select-menu").click();
            $("#labels-select-menu").click();
            $(".js-filterable-issue-labels").$(byText(labels)).click();
            $("#labels-select-menu").click();
            $(byText("Submit new issue")).click();
        });
        step("Проверяем название, текст, дabels и фssignees issue", (step) -> {
            $(".gh-header-title").shouldHave(Condition.text(issueTitle));
            $(".d-block.comment-body").shouldHave(Condition.exactText(issueText));
            $(".js-issue-assignees").shouldHave(Condition.exactText(assignees));
            $(".js-issue-labels").shouldHave(Condition.exactText(labels));
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
                .clickIssue().createNewIssue(issueTitle, issueText,assignees ,labels)
                .checkNewIssue(issueTitle, issueText, assignees,labels)
                .deleteIssue();
    }

}
