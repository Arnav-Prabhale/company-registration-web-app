<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
 <html>
 <head>
 	<title>Registration Form</title>
 	<link type="text/css" href="style.css" rel="stylesheet"/>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 	<script src = "http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.1.min.js"></script>
 	
 </head>
 
 <body>
 

 
 
 	<h1 align="center">Entity Updation Form (InCorporation Form)</h1>
 	
 	<form action="FormServlet" method="POST">
 					
 		<input type="hidden" name="command" value="UPDATE"/>
 		<input type="hidden" name="entryId" value="${UPDATE_ENTRY.id }"/>
 		
 	
	 	
	 	<table class="t1" cellspacing="8" cellpadding="8" align="center">
	 		<tr>
	 			<td>Name</td>
	 			<td><input value="${UPDATE_ENTRY.name }" class="input" type="text" id="company_name"
	 			name="company_name" placeholder="Name of Company or Business" size="70" required/></td>
	 		</tr>
	 		<tr>
	 			<td>Company Registration Date</td>
	 			<td><input value="${UPDATE_ENTRY.registrationDate }" class="input" type="text" 
	 			name="company_registration_date" placeholder="Company Registration Date" size="25" required/></td>
	 		</tr>
	 		<tr>
	 			<td>Address</td>
	 			<td>
	 				<input value="${UPDATE_ENTRY.addressLine1 }" class="input" type="text" 
	 				name="company_address_line1" placeholder="Address Line 1" size="70" required/><br/>
	 				
	 				
	 				<input value="${UPDATE_ENTRY.addressLine2 }" class="input" type="text" 
	 				name="company_address_line2" placeholder="Address Line 2" size="70"/><br/>
	 				
					<select id="country_name" class="country_name" name="country" required>
						<option selected disabled>Country</option>
						
						<c:forEach var="tempNames" items="${COUNTRY_CODE_VALUE }">
						
								<option value="${tempNames.value}"> ${tempNames.codeLabel }</option>
							
						</c:forEach>
					</select>

					</br>
	 				<input value="${UPDATE_ENTRY.state }" class="input" type="text" name="state" 
	 				placeholder="State" size="10" required/><br/>
	 				
	 				
	 				<input value="${UPDATE_ENTRY.city }" class="input" type="text" 
	 				name="address_city" placeholder="City" size="10" required/><br/>
	 			</td>
	 		</tr>
	 		<!-- <tr>
	 			<td>License</td>
	 			<td>
	 				
	 				<table>
	 					<tr>
	 						<td><input type="checkbox" name="license" class="license" value="E-Commerce"> E-Commerce</td>
	 						<td><input type="checkbox" name="license" class="license" value="Telecom"> Telecom</td>
	 						<td><input type="checkbox" name="license" value="Health-Care"> Health Care</td>
	 					</tr>
	 					<tr>
	 						<td><input type="checkbox" name="license" value="Education"> Education</td>
	 						<td><input type="checkbox" name="license" value="Hospitality"> Hospitality</td>
	 						<td><input type="checkbox" name="license" value="Banking-Finance"> Banking & Finance</td>
	 					</tr>
	 					<tr>
	 						<td><input type="checkbox" name="license" value="Government-Services"> Government Services</td>
	 						<td><input type="checkbox" name="license" value="Energy-Resources"> Energy Resources</td>
	 					</tr>
	 				
	 				</table>	
	 			</td>
	 		</tr> -->
	 		<!-- <tr>
	 			<td>Type</td>
		 		<td>
					<input type="radio" name="type" value="Company" id="radio1" onclick='f1()'> Company
					<input type="radio" name="type" value="Business" id="radio2" onclick='f1()'> Business
					
					<div id="optionalQuestions" style="margin:15px">
				 		<div id="askBranch" >
				 			 Do the Business has Branches:
				 			<input class="rb1" type="radio" name="branch" value="yes" id="radio3" onclick='f2()'> Yes 
							<input type="radio" name="branch" value="no" id="radio4" onclick='f2()'> No
								
				 		</div>
				 		
				 		<div id="askAddress" style="margin-top:20px;">
				 			Address of Branch: <br/>
				 			<input class="input" type="text" name="branch_address" placeholder="Address of Branch" size=50/>
				 		</div>
				 		
				 		
				 	</div>
					
				</td>
	 		</tr> -->
	 	
	 	</table>
 	
 	
	 	<div align="center">
	 		<input class="input" type="submit" value="Update"/>
	 		
	 	</div>
	</form>
	
	<div id="footer" style="position: fixed; bottom: 15px; width: 100%;">
		<a href="FormServlet">Back to List of Entries</a>
	</div>
	
	<div style="clear: both;"></div>

	
	
  	
 </body>
 
 
 </html>
 
 
 
 
 
 
 
 
 
 