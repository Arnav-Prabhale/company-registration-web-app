package com.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Resource(name="jdbc/countryNames")
	private DataSource dataSource;
	
	private FormDbUtil formDbUtil;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			formDbUtil = new FormDbUtil(dataSource);
		}
		catch(Exception ex)
		{
			throw new ServletException(ex);
		}
		
		
//		FillEntry newEntry = new FillEntry(dataSource);
//		try {
//			newEntry.addEntries(100);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try
		{
			
			
			String theCommand = request.getParameter("command");
			
			
			
			if(theCommand == null) 
			{
				theCommand = "SHOW";
			}
			
			switch(theCommand)
			{
			case "FORM":
				registrationForm(request, response);
				break;
				
			case "SHOW":
            	showAllEntry(request, response);
            	break;
            	
			case "DELETE":
            	loadDeleteEntry(request, response);
            	break;
            
            case "CONFIRM_DELETE":
            	deleteEntry(request, response);
            	break;
            	
			 case "UPDATE":
	            	updateEntry(request, response);
	            	break;
	            	
			 case "LOAD":
	            	loadEntry(request, response);
	            	break;
				
			default:
				registrationForm(request, response);
			}
		}
		catch (Exception e) 
		{
			throw new ServletException(e);
		}
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		try 
        {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
			
			if(theCommand == null) 
			{
				theCommand = "SHOW";
			}
			       
            // route to the appropriate method
            switch (theCommand) {
                            
            case "ADD":
                addEntry(request, response);
                break;
                
            case "SHOW":
            	showAllEntry(request, response);
            	break;
            	
            case "DELETE":
            	loadDeleteEntry(request, response);
            	break;
            
            case "CONFIRM_DELETE":
            	deleteEntry(request, response);
            	break;
            
            case "UPDATE":
            	updateEntry(request, response);
            	break;
            	
            case "LOAD":
            	loadEntry(request, response);
            	break;
                                
            default:
            	registrationForm(request, response);
            }
                
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
		
		
	}
	

	private void loadEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ready entry from the data
		String entryId = request.getParameter("entryId");
		
		// get entry from db
		CompanyEntry updateEntry = formDbUtil.getEntry(entryId);
		
		request.setAttribute("HEADING", "Update the Entry");
		request.setAttribute("FLAG", "UPDATE");
		request.setAttribute("ENTRY_ID", entryId);
		
		List<CodeValue> countryCodeValue = formDbUtil.getCountryCodeValues();
		List<CodeValue> stateCodeValue = formDbUtil.getStateCodeValues();
		request.setAttribute("COUNTRY_CODE_VALUE", countryCodeValue);
		request.setAttribute("STATE_NAME", stateCodeValue);

		
		// set attribute
		String name = updateEntry.getName();
		request.setAttribute("UPDATE_ENTRY_NAME", name);
		request.setAttribute("UPDATE_DATE", updateEntry.getRegistrationDate());
		request.setAttribute("UPDATE_ADD1", updateEntry.getAddressLine1());
		request.setAttribute("UPDATE_ADD2", updateEntry.getAddressLine2());
		
		request.setAttribute("COUNTRY", updateEntry.getCountryName());
		request.setAttribute("STATE", updateEntry.getState());
		request.setAttribute("CITY", updateEntry.getCity());
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy ");  
	    String formattedDate = myDateObj.format(myFormatObj); 				
	    request.setAttribute("DATE", formattedDate);
		
		// send  to the update registration form jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/registration-form.jsp");
		dispatcher.forward(request, response);
	}


	private void loadDeleteEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ready entry from the data
				String entryId = request.getParameter("entryId");
				
				// get entry from db
				CompanyEntry deleteEntry = formDbUtil.getEntry(entryId);
				
				request.setAttribute("HEADING", "Confirm the Deletion of Entry");
				request.setAttribute("FLAG", "DELETE");
				request.setAttribute("ENTRY_ID", entryId);

				
				List<CodeValue> countryCodeValue = formDbUtil.getCountryCodeValues();
				List<CodeValue> stateCodeValue = formDbUtil.getStateCodeValues();
				
				
				// add country names to the drop down
				request.setAttribute("COUNTRY_CODE_VALUE", countryCodeValue);
				request.setAttribute("STATE_CODE_VALUE", stateCodeValue);
				
				
				// set attribute
				String name = deleteEntry.getName();
				request.setAttribute("UPDATE_ENTRY_NAME", name);
				request.setAttribute("UPDATE_DATE", deleteEntry.getRegistrationDate());
				request.setAttribute("UPDATE_ADD1", deleteEntry.getAddressLine1());
				request.setAttribute("UPDATE_ADD2", deleteEntry.getAddressLine2());

				request.setAttribute("COUNTRY", deleteEntry.getCountry());
				request.setAttribute("STATE", deleteEntry.getState());
				request.setAttribute("CITY", deleteEntry.getCity());
				
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy ");  
			    String formattedDate = myDateObj.format(myFormatObj); 				
			    request.setAttribute("DATE", formattedDate);	
				
				// send  to the update registration form jsp page
				RequestDispatcher dispatcher = request.getRequestDispatcher("/registration-form.jsp");
				dispatcher.forward(request, response);
		
		
	}
	
	
	private void deleteEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("entryId");
		System.out.println("Delete the entry with id " + id);
		String name = request.getParameter("company_name");
		String date = request.getParameter("company_registration_date");
		String add1 = request.getParameter("company_address_line1");
		String add2 = request.getParameter("company_address_line2");
		
		System.out.println(name+date+add1+add2);
		
		
		formDbUtil.deleteEntry(id);
		
		
		showAllEntry(request, response);
	}

	private void registrationForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<CodeValue> countryCodeValue = formDbUtil.getCountryCodeValues();
		List<CodeValue> codeValue = formDbUtil.getCodeValues();
		List<CodeValue> stateCodeValue = formDbUtil.getStateCodeValues();

		String countryName = "";
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy ");  
	    String formattedDate = myDateObj.format(myFormatObj); 				
	    request.setAttribute("DATE", formattedDate);
		
		// add country names to the drop down
		request.setAttribute("COUNTRY_CODE_VALUE", countryCodeValue);
		request.setAttribute("CODE_VALUE", codeValue);
		request.setAttribute("COUNTRY_NAME", countryName);
		request.setAttribute("STATE_NAME", stateCodeValue);

		request.setAttribute("FLAG", "FORM");
		
		
		// end to jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/registration-form.jsp");
		dispatcher.forward(request, response);
	}
	

	private void showAllEntry(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		int page = 1;
		int recordsPerPage = 20;
		String search = null;
		String name = null, type = null, license = null;
		if(request.getParameter("search") != null)
		{
			name = request.getParameter("search");
			search = " where company_name like '%" + name +"%'";
		}else{search = " where company_name like '%%' ";}
		
		if(request.getParameter("searchType") != null)
		{
			type = request.getParameter("searchType");
			search = search + " and `type` like '%" + type +"%'";
		}else{search = search + " and `type` like '%%' ";}
		
		if(request.getParameter("searchLicense") != null)
		{
			license = request.getParameter("searchLicense");
			search = search + " and license like '%" + license +"%'";
		}else{search = search + " and license like '%%' ";}
		
		if(request.getParameter("export") != null)
		{
			String expFormat = request.getParameter("export");
			System.out.println("Export format received in showAllEntry() is " + expFormat);
			formDbUtil.exportFile(expFormat, search, name, type, license);
		}
		
		if(request.getParameter("PAGE") != null)
		{
			page = Integer.parseInt(request.getParameter("PAGE"));
			request.setAttribute("PAGE", page);
		}
		
		if(request.getParameter("THE_LOCALE") != null)
		{
			String theLocale = request.getParameter("THE_LOCALE");
			request.setAttribute("THE_LOCALE", theLocale);

		}
		
		
		List<CompanyEntry> EntryList = formDbUtil.getCompanyEntryList((page-1)*recordsPerPage, recordsPerPage, search);
		
		
		
		request.setAttribute("COMPANY_ENTRY_LIST", EntryList);
		

		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy ");  
	    String formattedDate = myDateObj.format(myFormatObj); 				
	    request.setAttribute("DATE", formattedDate);
		
		
		int noOfRecords = formDbUtil.getNoOfRecords(search);
		
		int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		
		request.setAttribute("NO_OF_PAGES", noOfPages);	
		request.setAttribute("CURRENT_PAGE", page);	
		request.setAttribute("search", name);	
		request.setAttribute("searchType", type);
		request.setAttribute("searchLicense", license);
		
		
		
		int startIndex = (page - 1) * 20 + 1;	
		request.setAttribute("START_INDEX", startIndex);
		System.out.println("start index on display page is " + startIndex);	
		
		// end to jsp page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/display-entry-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void updateEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("entryId"));
		String name = request.getParameter("company_name");
		String date = request.getParameter("company_registration_date");
		String add1 = request.getParameter("company_address_line1");
		String add2 = request.getParameter("company_address_line2");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String city = request.getParameter("address_city");
		
		
		CompanyEntry updatedEntry = new CompanyEntry(id, name, date, add1, add2, country, state, city);
		
		formDbUtil.updateEntry(updatedEntry);
		
		showAllEntry(request, response);
			
	}

	
	private void addEntry(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
        try {
        	// read info from jsp page
    		String companyName = request.getParameter("company_name");
    		String companyRegistrationDate = request.getParameter("company_registration_date");
    		String companyAddressLine1 = request.getParameter("company_address_line1");
    		String companyAddressLine2 = request.getParameter("company_address_line2");
    		String companyCountry = request.getParameter("country");
    		String companyState = request.getParameter("state");
    		String companyCity = request.getParameter("address_city");
    		String companyLicense = request.getParameter("license");
    		String companyType = request.getParameter("type");
    		String companyRadio = request.getParameter("radio");
    		String companyBranchAddress = request.getParameter("branch_address");

    		
    		
    		LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy ");  
		    String formattedDate = myDateObj.format(myFormatObj); 				
		    request.setAttribute("DATE", formattedDate);	
    		
	
    		   // create new entry object
    		CompanyEntry newEntry = new CompanyEntry(companyName, companyRegistrationDate, companyAddressLine1,companyAddressLine2,
    				companyCountry,companyState,companyCity,companyLicense,companyType,companyRadio,companyBranchAddress,"");
    		
    		// add it to the database
    		formDbUtil.addEntry(newEntry);
    		
    		// send back to the main page
        	
        	
			response.sendRedirect(request.getContextPath() + "/FormServlet?command=SHOW");
		} catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
	}

}
















