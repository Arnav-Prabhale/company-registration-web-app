package com.web;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zefer.pd4ml.PD4Constants;

//import com.pd4ml.InvokeException;
//import com.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4Constants;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.*;
import com.pd4ml.InvokeException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class FormDbUtil {
	
	private DataSource dataSource;
	static Logger logger = Logger.getLogger(FormDbUtil.class.getName());  

	  protected Dimension format = PD4Constants.A4;

	   protected boolean landscapeValue = false;

	   protected int topValue = 5;

	   protected int leftValue = 5;

	   protected int rightValue = 5;

	   protected int bottomValue = 5;

	   protected String unitsValue = "mm";

	   protected String proxyHost = "";

	   protected int proxyPort = 0;

	 

	  protected int userSpaceWidth = 1200;
	
	
	public FormDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;

	}


	
	
	public List<CodeValue> getCodeValues(){

		List<CodeValue> codeValue = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			
			
			// create sql statement
			String query = "select * from code_value order by label";
			
			myStmt = myConn.createStatement();
			
			// execute query
			
			myRs = myStmt.executeQuery(query);
			
			// process result set
			 
			while(myRs.next())
			{
				// retrieve data from result set row
				
				int id = myRs.getInt("owner_id");
				String codeSet = myRs.getString("codeset_id");
				String codeLabel = myRs.getString("label");
				String value = myRs.getString("value");
				String parentId = myRs.getString("parent_id");
				
				// create new student object
				CodeValue tempcodeValue = new CodeValue(id, codeSet, codeLabel, value, parentId);
				
				// add it to the list of students
				
				codeValue.add(tempcodeValue);
				//System.out.println(codeLabel);
				
			}
			

			return codeValue;
		}
		catch(Exception e)
		{
			logger.info("Unable to fetch records from getcodevalues " + e.toString());
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		return codeValue;
		
		
		
	}

	public List<CodeValue> getCountryCodeValues() 
	{
		List<CodeValue> countryCodeValue = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			
			
			// create sql statement
			String query = "select * from code_value where codeset_id = 'county' order by label";
			
			myStmt = myConn.createStatement();
			
			// execute query
			
			myRs = myStmt.executeQuery(query);
			
			// process result set
			 
			while(myRs.next())
			{
				// retrieve data from result set row
				
				int id = myRs.getInt("owner_id");
				String codeSet = myRs.getString("codeset_id");
				String codeLabel = myRs.getString("label");
				String value = myRs.getString("value");
				String parentId = myRs.getString("parent_id");
				
				// create new student object
				CodeValue tempcodeValue = new CodeValue(id, codeSet, codeLabel, value, parentId);
				
				// add it to the list of students
				
				
				countryCodeValue.add(tempcodeValue);
				//System.out.println(codeLabel);
				
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			
			System.out.println("In get country code values function implemented by lazy loading." + dtf.format(now));

			return countryCodeValue;
		 
				
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("Unable to fetch records from get Country Code " + e.toString());

		}
		
		return countryCodeValue;
	}

	public List<CodeValue> getStateCodeValues() {

		
		List<CodeValue> stateCodeValue = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			
			
			// create sql statement
			String query = "select * from code_value where codeset_id = 'state' order by parent_id";
			
			myStmt = myConn.createStatement();
			
			// execute query
			
			myRs = myStmt.executeQuery(query);
			
			// process result set
			 
			while(myRs.next())
			{
				// retrieve data from result set row
				
				int id = myRs.getInt("owner_id");
				String codeSet = myRs.getString("codeset_id");
				String codeLabel = myRs.getString("label");
				String value = myRs.getString("value");
				String parentId = myRs.getString("parent_id");
				
				// create new student object
				CodeValue tempcodeValue = new CodeValue(id, codeSet, codeLabel, value, parentId);
				
				// add it to the list of students
				
				
				
				stateCodeValue.add(tempcodeValue);
				//System.out.println(codeLabel);
				
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			
			System.out.println("In get state code values function implemented by lazy loading." + dtf.format(now));
			return stateCodeValue;
		
				
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Unable to fetch records of code values " + e.toString());

		}
		
		return stateCodeValue;
		
		
	}
	
	
	public List<CompanyEntry> getCompanyEntryList(int offset, int recordsPerPage, String search) {

		List<CompanyEntry> EntryList = new ArrayList<>();
		List<CodeValue>  codeValue = this.getCodeValues();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			
			
			// create sql statement
			String cond1 = " limit " + recordsPerPage + " offset " + offset;
			String start = "select * from incorporation_form ";
			
			String query = start + cond1;

			if(search != null && search.length() > 29) 
				query = start + search + cond1;
			
			myStmt = myConn.createStatement();
			
			// execute query
			
			myRs = myStmt.executeQuery(query);
			
			
			// process result set
			 
			while(myRs.next())
			{
				// retrieve data from result set row
				
				int id = myRs.getInt("company_id");
				String name = myRs.getString("company_name");
				String registrationDate = myRs.getString("regestration_date");
				String addressLine1 = myRs.getString("address_line1");
				String addressLine2 = myRs.getString("address_line2");
				String country = myRs.getString("country");
				String state = myRs.getString("state");
				String city = myRs.getString("city");
				String license = myRs.getString("license");
				String type = myRs.getString("type");
				String radio;
				String branchAddress = myRs.getString("branch_address");
				String countryName = "";
				if(branchAddress == null)
				{
					radio = "NO";
				}
				else radio = "Yes";
//				myStmt1 = myConn.prepareStatement(query2);
//				myStmt1.setString(1, myRs.getString("country"));
//				
//				myRs1 = myStmt1.executeQuery();
//				String countryName = myRs1.getString("value");
				
				
				
				for(int i = 0; i < codeValue.size(); i++)
				{
					if(codeValue.get(i).getValue().equals(country) && codeValue.get(i).getCodeSet().equals("country"))
					{
						countryName = codeValue.get(i).getCodeLabel();
					}
				}
				
				
				
				
				
				
				
				// create new student object
				CompanyEntry tempEntry = new CompanyEntry( id,  name,  registrationDate,  addressLine1,  addressLine2,
						 country,  state,  city,  license,  type,  radio, branchAddress, countryName) ;
				
				// add it to the list of students
				
				EntryList.add(tempEntry);
				//System.out.println(tempEntry);
//				close(null, myStmt1, myRs1);
				
			}
			
			System.out.println("in get company entry values function : " + EntryList.size());

			return EntryList;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Unable to fetch records from entity list " + e.toString());

		}
		finally {
			close(myConn, myStmt, myRs);

			
		}
		return EntryList;
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
			logger.info("Unable to fetch records" + ex.toString());

		}
		
	}

	public void addEntry(CompanyEntry newEntry) throws Exception{

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
			
			myStmt.setString(1, newEntry.getName());
			myStmt.setString(2, newEntry.getRegistrationDate());
			myStmt.setString(3, newEntry.getAddressLine1());
			myStmt.setString(4, newEntry.getAddressLine2());
			myStmt.setString(5, newEntry.getCountry());
			myStmt.setString(6, newEntry.getState());
			myStmt.setString(7, newEntry.getCity());
			myStmt.setString(8, newEntry.getLicense());
			myStmt.setString(9, newEntry.getType());
			myStmt.setString(10, newEntry.getBranchAddress());
			
			myStmt.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.warn("Unable to add new record " + e.toString());

		}
		finally
		{
			// clean objects
			close(myConn, myStmt, null);
		}
		
		
		
		
	}

	public void deleteEntry(String id) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try
		{

			
			// convert student id to int
			int entryId = Integer.parseInt(id);
			
			// get connection to the database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from incorporation_form where company_id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, entryId);
			
			// execute sql statement
			myStmt.execute();
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.trace("Unable to delete record" + e.toString());

		}
		finally
		{
			// clean the jdbc objects
			close(myConn, myStmt, null);
		}
		

		
	}

	public CompanyEntry getEntry(String entryId) throws Exception {

		
//		List<CompanyEntry> EntryList = this.getCompanyEntryList();

//		int companyId = Integer.parseInt(entryId);
		
		CompanyEntry updateEntry = null;
		
//		for(int i = 0; i < EntryList.size(); i++)
//		{
//			if(EntryList.get(i).getId() == companyId)
//			{
//				updateEntry = EntryList.get(i);
//			}
//		}
//		
		
		
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try
		{

			
			// convert student id to int
			
			// get connection to the database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "select * from incorporation_form where company_id="+ entryId +" limit 1";
			
			// prepare statement
			myStmt = myConn.createStatement();
			
			// set params
			// execute sql statement
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				int id = myRs.getInt("company_id");
				String name = myRs.getString("company_name");
				String registrationDate = myRs.getString("regestration_date");
				String addressLine1 = myRs.getString("address_line1");
				String addressLine2 = myRs.getString("address_line2");
				String country = myRs.getString("country");
				String state = myRs.getString("state");
				String city = myRs.getString("city");
				String license = myRs.getString("license");
				String type = myRs.getString("type");
				String radio;
				String branchAddress = myRs.getString("branch_address");
				String countryName = "";
				if(branchAddress == null)
				{
					radio = "NO";
				}
				else radio = "Yes";
				
				updateEntry = new CompanyEntry( id,  name,  registrationDate,  addressLine1,  addressLine2,
						 country,  state,  city,  license,  type,  radio, branchAddress, countryName) ;
				
			}
			
			System.out.println("In get entry for updation function implemented by lazy loading.");
			System.out.println(updateEntry.toString());
	
				
			return updateEntry;
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.fatal("Unable to get entry info from db: " + e.toString());

		}
		finally
		{
			// clean the jdbc objects
			close(myConn, myStmt, null);
		}
		return null;
						
	}

	public void updateEntry(CompanyEntry updatedEntry) throws SQLException {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try
		{
			myConn = dataSource.getConnection();
			
			String sql = "update incorporation_form "
						+ "set company_name=?, regestration_date=?, "
						+ "address_line1=?, address_line2=?, state=?, city=? "
						+ "where company_id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, updatedEntry.getName());
			myStmt.setString(2, updatedEntry.getRegistrationDate());
			myStmt.setString(3, updatedEntry.getAddressLine1());
			myStmt.setString(4, updatedEntry.getAddressLine2());
			myStmt.setString(5, updatedEntry.getState());
			myStmt.setString(6, updatedEntry.getCity());
			myStmt.setInt(7, updatedEntry.getId());
			
			myStmt.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in updating entity object: " + e.toString());

		}
		finally
		{
			close(myConn, myStmt, null);
		}
		
		
	}
	
	public int getNoOfRecords(String search) throws SQLException {

		int noOfRecords = 0;
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try
		{

			
			// convert student id to int
			
			// get connection to the database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "SELECT COUNT(company_id) FROM incorporation_form ";
			
			if(search != null)
			{
				sql = sql + search;
			}
			
			sql = sql + ";";
			
			// prepare statement
			myStmt = myConn.createStatement();
			
			// set params
			// execute sql statement
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next())
			{
				noOfRecords = myRs.getInt("COUNT(company_id)");
			} 

			return noOfRecords;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.warn("Unable to get total number of records: " + e.toString());

		}
		finally
		{
			// clean the jdbc objects
			close(myConn, myStmt, null);
		}
		return 0;
		
	}




	
	public void exportFile(String expFormat, String search, String name, String type, String license) throws FileNotFoundException, IOException, DocumentException {

		
		if(expFormat.equals("EXL"))
		{
			generateEXL();
		}
		else if(expFormat.equals("PDF"))
		{
			generatePDF(search, name, type, license);
		}
		else if(expFormat.equals("PD4ML"))
		{
			System.out.println("PD4Ml method called successfully");
			runConverter();
		}
		
		
		
		
		 
	}

	private void runConverter() throws IOException {

		String urlstring = "http://localhost:8080/Registration-form/FormServlet";

		String dirName = "/Users/arnavprabhale/Downloads/";
		String filename = "CompanyEntryList";
		String extension = ".pdf";
		
		File tempFile = new File(dirName + filename + extension);
		
		boolean exists = tempFile.exists();
		int x = 1;
		String tempName = dirName + filename;
		while(exists)
		{
			tempName = filename + "(" + String.valueOf(x++) + ")";
			
			tempFile = new File(dirName + "/" + tempName + extension);
			
			exists = tempFile.exists();
		}
		
		File output = tempFile;
		
		
        if (urlstring.length() > 0) {

               if (!urlstring.startsWith("http://") && !urlstring.startsWith("file:")) {

                             urlstring = "http://" + urlstring;

               }



              java.io.FileOutputStream fos = new java.io.FileOutputStream(output);

              

              if ( proxyHost != null && proxyHost.length() != 0 && proxyPort != 0 ) {

                      System.getProperties().setProperty("proxySet", "true");

                      System.getProperties().setProperty("proxyHost", proxyHost);

                      System.getProperties().setProperty("proxyPort", "" + proxyPort);

               }



               PD4ML pd4ml = new PD4ML();
				 pd4ml.setHtmlWidth(1200);
				 pd4ml.addStyle("css/cssDisplayEntry.css",true);

//               try {                                                              
//
//                      pd4ml.setPageSize((Dimension)1200, 600);
//
//                   } catch (Exception e) {
//
//                      e.printStackTrace();S
//
//                   }

                     

               if ( unitsValue.equals("mm") ) {

                      pd4ml.setPageInsetsMM( new Insets(topValue, leftValue, bottomValue, rightValue) );

               } else {

                      pd4ml.setPageInsets( new Insets(topValue, leftValue, bottomValue, rightValue) );

               }



               pd4ml.setHtmlWidth( userSpaceWidth );

              

              pd4ml.render( urlstring, fos );

        }

  }


//	private void generatePD4ML() throws InvokeException, MalformedURLException, IOException {
//
//		PD4ML pd4ml = new PD4ML();
//
//       	pd4ml.setHtmlWidth(1200); // render HTML in a virtual frame 900px wide
//    	pd4ml.addStyle( 
//    					// specify TTF font file for "Consolas" font face (only "plain" style, in the case). 
//    			
//    					// Here we use free FiraMono-Regular instead of the original Consolas.
//    					// Other font faces to be mapped to PDF viewer standard built-in fonts.
//    			
//    					// In the resulting PDF you can see '?' symbols instead of some character glyphs.
//    					// That means the missing glyphs are not defined by any of the available fonts.
//    			
//    					// As a workaround create a font dir, place a set of fonts there to cover the 
//    					// desired language or character range, index fonts and refer to the dir 
//    					// with pd4ml.useTTF() API call. Optionally the font dir can be packed to
//    					// a fonts.jar
//    			"@font-face {\n" + 
//    			"  font-family: \"Consolas\";\n" + 
//    			"  src: url(\"java:/html/rc/FiraMono-Regular.ttf\") format(\"ttf\"),\n" + 
//    			"}\n", false);
//
//    	// read and parse HTML
//    	pd4ml.readHTML(new URL("http://localhost:8080/Registration-form/FormServlet"));
//    	
//    	
//    	String dirName = "/Users/arnavprabhale/Downloads/";
//		String filename = "CompanyEntryList";
//		String extension = ".pdf";
//		
//		File tempFile = new File(dirName + filename + extension);
//		
//		boolean exists = tempFile.exists();
//		int x = 1;
//		String tempName = dirName + filename;
//		while(exists)
//		{
//			tempName = filename + "(" + String.valueOf(x++) + ")";
//			
//			tempFile = new File(dirName + "/" + tempName + extension);
//			
//			exists = tempFile.exists();
//		}
//		System.out.println(tempFile);
//    	
//    	
//    	
//    	
//    	File pdf = File.createTempFile("CompanyEntryList", ".pdf");
//    	FileOutputStream fos = new FileOutputStream(pdf);
//    	
//    	// render and write the result as PDF
//    	pd4ml.writePDF(fos);
//    	// alternatively or additionally: 
//    	// pd4ml.writeRTF(rtfos, false);
//    	// BufferedImage[] images = pd4ml.renderAsImages();
//    	
//    	// open the just-generated PDF with a default PDF viewer
//		if (true) {
//			Desktop.getDesktop().open(pdf);
//		
//		}
//	}




	private void generatePDF(String search, String name, String type, String license) throws FileNotFoundException, DocumentException {
		
		System.out.println("Function to export in utility class is called successfully and command received is to PDF");
		
		String dirName = "/Users/arnavprabhale/Downloads/";
		String filename = "CompanyEntryList";
		String extension = ".pdf";
		
		File tempFile = new File(dirName + filename + extension);
		
		boolean exists = tempFile.exists();
		int x = 1;
		String tempName;
		while(exists)
		{
			tempName = filename + "(" + String.valueOf(x++) + ")";
			
			tempFile = new File(dirName + "/" + tempName + extension);
			
			exists = tempFile.exists();
		}
		System.out.println(tempFile);
		
		Document document = new Document(PageSize.LETTER.rotate());

		
		PdfWriter.getInstance(document, new FileOutputStream(tempFile));
        document.open();
        
		if(!exists)
		{
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
		
			try 
			{
				
				
				
				
		
				// get a connection
				myConn = dataSource.getConnection();
				
				// create sql statement
				String orderBy = " order by company_id ";
				String select = "select * from incorporation_form ";
				String query = select + orderBy;
				if(search != null)
				{
					query = select + search + " order by company_name ";
				}
				
				
				
				myStmt = myConn.createStatement();
				
				// execute query
				
				myRs = myStmt.executeQuery(query);
				
				String[] headers = {"Sr. No", "Name", "Date", "Address Line 1", "Address Line 2", 
						"Country", "State", "City", "License", "Type"};
				
				ArrayList<ArrayList<String>> list = new ArrayList<>();
				
//				"Address Line 1", 
//				"Address Line 2", , "License", "Type"
				// process result set
				int i = 1;
				
				while(myRs.next())
				{
					ArrayList<String> temp = new ArrayList<>();
					
					temp.add(String.valueOf(i++));
					temp.add(myRs.getString("company_name"));
					temp.add(myRs.getString("regestration_date"));
					temp.add(myRs.getString("address_line1"));
					temp.add(myRs.getString("address_line2"));
					temp.add(myRs.getString("country"));
					temp.add(myRs.getString("state"));
					temp.add(myRs.getString("city"));
					temp.add(myRs.getString("license"));
					temp.add(myRs.getString("type"));
					
					
					list.add(temp);
						
					
			}
//				Iterator<ArrayList<String>> itr = list.iterator();	
				
				
				
				
				
	            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	            Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

	            
	            
	            
	            PdfPTable table = new PdfPTable(headers.length);
	            for (String header : headers) {
	                PdfPCell cell = new PdfPCell();
	                cell.setGrayFill(0.9f);
	                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
	                table.addCell(cell);
	            }
	            table.completeRow();
	            
	            

	            for (ArrayList<String> row : list) {
	                for (String data : row) {
	                    Phrase phrase = new Phrase(data, fontRow);
	                    table.addCell(new PdfPCell(phrase));
	                }
	                table.completeRow();
	            }


	            document.addTitle("PDF Table Demo");
	            if(name != null && name != "")
	            {
	            	document.add(new Paragraph("  "));
	            	document.add(new Paragraph("*)	Search"));
	            	document.add(new Paragraph("	Name:- " + name));
	            	document.add(new Paragraph("  "));
	            }
	            if(type != null && type != "")
	            {
	            	document.add(new Paragraph("*)	Filters"));
	            	document.add(new Paragraph("	Type :- " + type));
	            }
	            if(license != null && license != "")
	            {
	            	document.add(new Paragraph("	License :- " + license));
	            	document.add(new Paragraph("  "));
	            }
	            int number = getNoOfRecords(search);
            	document.add(new Paragraph("Number of Entries:- " + number));
            	document.add(new Paragraph("  "));
	            
	            document.add(table);
	            
	            
		        System.out.println("Table created successfully..");
		        
		        
		        
				
			}
				
			catch (Exception e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("Unable to print records in pdf " + e.toString());
			}
			finally {
				
				document.close();
				close(myConn, myStmt, myRs);

				
			}
		
		}
		
		
	}




	private void generateEXL() throws IOException {
		
		System.out.println("Function to export in utility class is called successfully and command received is to EXL");
		
		String dirName = "/Users/arnavprabhale/Downloads/";
		String filename = "CompanyEntryList";
		String extension = ".xls";
		
		File tempFile = new File(dirName + filename + extension);
		
		boolean exists = tempFile.exists();
		int x = 1;
		while(exists)
		{
			String tempName = filename + "(" + String.valueOf(x++) + ")";
			
			tempFile = new File(dirName + "/" + tempName + extension);
			
			exists = tempFile.exists();
		}
		System.out.println(tempFile);
		
		if(!exists)
		{
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			HSSFSheet sheet = workbook.createSheet("List of Entity");
			
			HSSFRow rowhead = sheet.createRow((short)0);  
			
			rowhead.createCell(0).setCellValue("S.No.");  
			rowhead.createCell(1).setCellValue("Company Name");  
			rowhead.createCell(2).setCellValue("Registration Date");  
			rowhead.createCell(3).setCellValue("Address Line 1");  
			rowhead.createCell(4).setCellValue("Address Line 2");  
			rowhead.createCell(5).setCellValue("Country");  
			rowhead.createCell(6).setCellValue("State");  
			rowhead.createCell(7).setCellValue("City");  
			rowhead.createCell(8).setCellValue("License");  
			rowhead.createCell(9).setCellValue("Type");  

			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			
			try {
				// get a connection
				myConn = dataSource.getConnection();

				
				
				// create sql statement
				String query = "select * from incorporation_form order by company_id ";
				
				
				myStmt = myConn.createStatement();
				
				// execute query
				
				myRs = myStmt.executeQuery(query);
				
				
				// process result set
				int i = 1;
				HSSFRow row;
				
				while(myRs.next())
				{
					// retrieve data from result set row
					row = sheet.createRow((short)i);
					
					row.createCell(0).setCellValue(i++); 
					row.createCell(1).setCellValue(myRs.getString("company_name"));  
					row.createCell(2).setCellValue(myRs.getString("regestration_date"));  
					row.createCell(3).setCellValue(myRs.getString("address_line1"));  
					row.createCell(4).setCellValue(myRs.getString("address_line2"));  
					row.createCell(5).setCellValue(myRs.getString("country"));  
					row.createCell(6).setCellValue(myRs.getString("state"));  
					row.createCell(7).setCellValue(myRs.getString("city"));  
					row.createCell(8).setCellValue(myRs.getString("license"));  
					row.createCell(9).setCellValue(myRs.getString("type"));  
					
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.info("Unable to print records in xls " + e.toString());

			}
			finally {
				close(myConn, myStmt, myRs);

				
			}		
			
			
			FileOutputStream fileOut = new FileOutputStream(tempFile); 
			
			workbook.write(fileOut);  
			
			//closing the Stream  
			fileOut.close();  
			
			//closing the workbook  
			workbook.close();  
			
			//prints the message on the console  
			System.out.println("Excel file has been generated successfully."); 
			
			
		}
		else
		{
			System.out.println("File with similar name exists");
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
