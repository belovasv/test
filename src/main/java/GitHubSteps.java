import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubSteps {

    @Step("Логинимся в GitHub")
    public GitHubSteps login(String user, String password) {
        open("/");
        $(By.linkText("Sign in")).click();
        $("#login_field").setValue(user);
        $("#password").setValue(password);
        $("[type=submit]").click();
        return this;
    }

    @Step("идем по ссылке ${repo}")
    public GitHubSteps goToLink(String repo) {
        open(repo);
        return this;
    }

    @Step("кликаем на вкладку issue")
    public GitHubSteps clickIssue() {
        $("[data-tab-item=issues-tab]").click();
        return this;
    }

    @Step("Кликаем на new issue, и заполняем issue")
    public GitHubSteps createNewIssue(String issueTitle, String issueText, String labels, String assignees) {
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
        return this;
    }

    @Step("Проверяем название и текст issue")
    public GitHubSteps checkNewIssue(String issueTitle, String issueText, String assignees, String labels) {
        $(".gh-header-title").shouldHave(Condition.text(issueTitle));
        $(".d-block.comment-body").shouldHave(Condition.exactText(issueText));
        $(".js-issue-assignees").shouldHave(Condition.exactText(assignees));
        $(".js-issue-labels").shouldHave(Condition.exactText(labels));
        return this;
    }

    @Step("Удаляем Issue")
    public void deleteIssue() {
        $(byText("Delete issue")).click();
        $(byText("Delete this issue")).click();
    }
}
