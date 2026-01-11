package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DatePickerUtil {

    private WebDriver driver;

    public DatePickerUtil(WebDriver inputDriver) {

        this.driver = inputDriver;
    }

    public void datePick(List<WebElement> columns, String dateOfPick) {
        for (WebElement cell: columns) {
            if (cell.getText().equals(dateOfPick)) {
                cell.click();
                break;
            }
        }
    }

    public String getCurrentDay (){
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt +"\n");

        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");

        return todayStr;
    }
}
