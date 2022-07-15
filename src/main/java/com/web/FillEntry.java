package com.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class FillEntry {
	
	@Resource(name="jdbc/countryNames")
	private static DataSource dataSource;

	FillEntry(DataSource theDataSource){
		
		dataSource = theDataSource;
	}
	
 void addEntries(int n) throws Exception
	{
			System.out.println("Enter number of entries you want to add");
			
			String fName[] = {"Raja", "TechLead", "Versatalia", "Tata", "Mahindra ", "Birla ", "Reliance", "Adani"};
			String lName[] = {"Systems", "Data Labs", "Enterprise", "Data Solutions", "Infocomm", "Informations", "Softwares", "Technologies", "Autocom"};
			String addressLine1 = "add line 1";
			String addressLine2 = "add line 1";
			String country[] = {"IN", "CN", "CH", "USA"};
			String IN[] = {"Bihar", "Kashmir", "Maharashtra"};
			String CN[] = {"Hawaii", "Michigan", "Vermont"};
			String CH[] = {"Hebai", "Hunan"};
			String USA[] = {"Alaska", "Florida", "Texas"};
			String state;
			String city = "city";
			String license[] = {"E-Commerce", "Telecom" , "Health Care", "Education", 
					"Hospitality", "Banking & Finance", "Government Services", "Energy Resources"};
			String type[] = {"Company", "Business"};
			String branchAddress = " ";
			
			int in = 0, ch = 0, cn = 0, usa = 0;
			int j = 0;
			
			for(int i = 0; i < n; i++)
			{
				int day = (i % 30) + 1, month=(i % 12) + 1, year=1900;
				String companyName = fName[i % 8] + " " + lName[i % 9];
				String date = (year+i)+"-" + month +"-"+ day;
				
				
				if(country[i%4] == "IN") state = IN[in++ % 3];
				else if(country[i%4] == "CN") state = CN[cn++ % 3];
				else if(country[i%4] == "CH") state = CH[ch++ % 2];
				else state = USA[usa++ % 3];
				
				System.out.println("Entry added" + i);
				
				addEntry(companyName, date, addressLine1, addressLine2, country[i%4], state,
						city, license[i%8], type[i%2], branchAddress);
				
				
			}
		}
		
		
	
	
	
	public static void addEntry(String companyName, String date, String addressLine1, String addressLine2, 
			String country, String state, String city, String license, String type, String branchAddress) throws Exception{

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			String sql = "insert into incorporation_form "
						+ "(company_name, regestration_date, address_line1, address_line2,"  
						+ " country, state, city, license, type, branch_address)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, companyName);
			myStmt.setString(2, date);
			myStmt.setString(3, addressLine1);
			myStmt.setString(4, addressLine2);
			myStmt.setString(5, country);
			myStmt.setString(6, state);
			myStmt.setString(7, city);
			myStmt.setString(8, license);
			myStmt.setString(9, type);
			myStmt.setString(10, branchAddress);
			
			myStmt.execute();
		}
		finally
		{
			// clean objects
			close(myConn, myStmt, null);
		}
		
		
		
		
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try
		{
			if(myRs != null)
			{
				myRs.close();
			}
			if(myStmt != null)
			{
				myStmt.close();
			}
			if(myConn != null)
			{
				myConn.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
