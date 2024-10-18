package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//Data Provider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\LoginData.xlsx"; //taking xl file from testData
		ExcelUtility xlutil=new ExcelUtility(path);  //creating an object for XLUtility
				
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getcellCount("Sheet1",1);  //1 is row number
		String loginData[][]=new String [totalrows][totalcols]; //created for 2 dimension array which can store
		
		for(int i=1;i<=totalrows;i++) //1 //read the data from xl storing in 2 dimensional array
		{
			for (int j=0;j<totalcols;j++) //0
			{
				loginData[i-1][j]=xlutil.getcellData("Sheet1", i, j);
			}
		}
		return loginData;  //returning 2 dimensional array
	}
	
	//Data Provider 2
	//Data Provider 3
}
