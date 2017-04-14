package vixxo.ORMBTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExecuteTest {

WebDriver webdriver;
/*
@BeforeTest
@Parameters("browser") 
public void setup(String browserName) throws Exception{
	if (browserName.equalsIgnoreCase("firefox")){
		System.setProperty("webdriver.gecko.driver", "C://Selenium//jars//geckodriver.exe");
		webdriver = new FirefoxDriver();
		
	}
	 else if (browserName.equalsIgnoreCase("Chrome")) {
		System.setProperty("webdriver.chrome.driver", "C://Selenium//jars//chromedriver.exe");
		webdriver = new ChromeDriver();
		
	} 
  else if (browserName.equalsIgnoreCase("ie")) {
		System.setProperty("webdriver.ie.driver", "C://Selenium//jars//IEDriverServer.exe");
		webdriver = new InternetExplorerDriver();
		
	} 
	 else {
		 throw new Exception("Browser not found");
	 }
	webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
*/
@Test
public void testLogin() throws Exception {  

//DesiredCapabilities capability = DesiredCapabilities.chrome();
//capability.setBrowserName("chrome");
//capability.setPlatform(Platform.ANY);
System.setProperty("webdriver.gecko.driver", "C://Selenium//jars//geckodriver.exe");
webdriver = new FirefoxDriver();
//System.setProperty("webdriver.chrome.driver", "C://Selenium//jars//chromedriver.exe");
//webdriver = new ChromeDriver();
//System.setProperty("webdriver.ie.driver", "C://Selenium//jars//IEDriverServer.exe");
//webdriver = new InternetExplorerDriver();
//webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

ReadExcelFile file = new ReadExcelFile();
ReadObject object = new ReadObject();
Properties allObjects = object.getObjectRepository();
UIOperation operation = new UIOperation(webdriver);

//Read keyword sheet
Sheet sSheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
//Find number of rows in excel file
    int rowCount = sSheet.getLastRowNum()-sSheet.getFirstRowNum();
    //Create a loop over all the rows of excel file to read it
    for (int i = 1; i < rowCount+1; i++) {
        //Loop over all the rows
        Row row = sSheet.getRow(i);
        //Check if the first cell contain a value, if yes, That means it is the new testcase name
        if(row.getCell(0).toString().length()==0){
        //Print testcase detail on console
            System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
            row.getCell(3).toString()+"----"+ row.getCell(4).toString());
        //Call perform function to perform operation on UI
            operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
                row.getCell(3).toString(), row.getCell(4).toString());
     }
        else{
            //Print the new testcase name when it started
                System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
            }
        }
    webdriver.quit();
    }
   
  }
