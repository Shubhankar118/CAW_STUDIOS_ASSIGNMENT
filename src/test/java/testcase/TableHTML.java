package testcase;

import basefile.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TableHTML extends BaseTest {


    @Test
    public static void site() throws InterruptedException, IOException {

        try {
            WebElement tableDataButton = driver.findElement(By.xpath("//summary[text()='Table Data']"));
            tableDataButton.click();//clicking on table data button


            //reading the json data from json file
            String jsonData = new String(Files.readAllBytes(Paths.get("src/test/resources/testdata.json")));


            WebElement inputTextBox = driver.findElement(By.xpath("//textarea[@id=\"jsondata\"]"));
            inputTextBox.clear();// clearing the text filed
            inputTextBox.sendKeys(jsonData);// clicking on the text filed


            WebElement refreshTextBox = driver.findElement(By.xpath("//button[@class=\"styled-click-button\"]"));
            refreshTextBox.click();// clicking on refresh button



            // Locating the table and getting all the rows
            WebElement table = driver.findElement(By.xpath("//table[@id='dynamictable']"));
            List<WebElement> rows = table.findElements(By.xpath("//table[@id='dynamictable']//tr[2]"));

            // Comparing the JSON data with the data in the table
            boolean dataMatches = true;
            for (int i = 1; i < rows.size(); i++) { // Starting from 1 to skip the header row
                WebElement row = rows.get(i);
                List<WebElement> columns = row.findElements(By.xpath("//table[@id='dynamictable']//td"));
                String name = columns.get(0).getText();
                String age = columns.get(1).getText();
                String gender = columns.get(2).getText();

                // Splitting the JSON data into name, age, and gender
                String[] jsonDataParts = jsonData.split("\"name\" : \"" + name + "\", \"age\" : " + age + ", \"gender\" : \"" + gender + "\"");

                if (jsonDataParts.length != 2) {
                    dataMatches = false;
                    break;
                }
            }

            if (dataMatches) {
                System.out.println("Data in the table matches the JSON data.");
            } else {
                System.out.println("Data in the table does not match the JSON data.");
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }



}
