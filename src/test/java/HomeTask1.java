import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeTask1 {

    String firstName = "Mark",
            lastName = "Brown",
            email = "brown@example.com",
            gender = "Female",
            phone = "1234567890",
            dayOfBirth = "12",
            monthOfBirth = "July",
            yearOfBirth = "1987",
            firstLetter = "b",
            subject = "Biology",
            hobby = "Reading",
            address = "Big city",
            state = "Haryana",
            city = "Karnal";


    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void test() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(firstLetter);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.png"));
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

    }

}
