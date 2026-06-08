package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataPrvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating object for ExcelUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //created for two dimensional array
		
		for(int i=1;i<=totalrows;i++)  //1 //reada data from excel storing  two dimensional array
		{
			for(int j=0;j<totalcols;j++)  //0 //i is row j is column
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
				
			}
			
		}
		return logindata;  //returning two dimensional array
		
		
	}
 
}
