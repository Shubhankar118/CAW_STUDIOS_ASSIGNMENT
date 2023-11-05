package basefile;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public static Properties props = new Properties();
    public static Properties loc = new Properties();
    public static FileReader fr;


    @BeforeTest
//    Before the execution this method wil Run
    public void setUp() throws IOException {
        if (driver == null) {
            fr = new FileReader("src/test/resources/config.properties");
            props.load(fr);   // using FileReader reading the url form config.properties
        }

        // Check if the browser property is set to "chrome" in the configuration file
        if (props.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup(); //downloading and Initialize the chrome driver
            driver = new ChromeDriver();
            driver.get(props.getProperty("testurl"));
            driver.manage().window().maximize();

            // Check if the browser property is set to "firefox" in the configuration file
        } else if (props.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup(); //downloading and Initialize the firefox driver
            driver = new FirefoxDriver();
            driver.get(props.getProperty("testurl"));
            driver.manage().window().maximize();

        }

    }
    //this
    @AfterTest
//    after the execution this method will run and close the browser
    public void tearDown () {
        driver.quit();
    }
}
