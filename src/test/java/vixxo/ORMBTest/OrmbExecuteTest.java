package vixxo.ORMBTest;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.openqa.selenium.remote.ProtocolHandshake;

public class OrmbExecuteTest {
	
	WebDriver webdriver;
	
@Test(dataProvider="hybridData")

   public void OrmbLogin(String testcaseName, String keyword, String objectName, String objectType, String value) throws Exception {
	
	if (testcaseName!= null&&testcaseName.length()!=0){
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.ANY);
		System.setProperty("webdriver.chrome.driver", "C://Selenium//jars//Chrome//chromedriver.exe");
		webdriver = new ChromeDriver();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	ReadObject object = new ReadObject();
	Properties allObjects = object.getObjectRepository();
	UIOperation operation = new UIOperation(webdriver);
	//Call perform function to perform operation on UI
    operation.perform(allObjects, keyword, objectName, objectType, value);
    
	}

@DataProvider(name="hybridData")

public Object[][] getDataFromDataprovider() throws IOException{
	
	Object[][] object = null;
	
	ReadExcelFile file = new ReadExcelFile();
	//Read keyword sheet
	Sheet sSheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
	//Find number of rows in excel file
	    int rowCount = sSheet.getLastRowNum()-sSheet.getFirstRowNum();
	    object = new Object[rowCount][5];
	    for (int i = 0; i < rowCount; i++) {
	        //Loop over all the rows
	        Row row = sSheet.getRow(i+1);
	        //Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	
	            //Print excel data in console
	        	
	            object[i][j] = row.getCell(j).toString();
	        }
	    }
	    System.out.println("");
		return object;
	    
	  }
}