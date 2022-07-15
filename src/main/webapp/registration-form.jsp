<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="theLocale"
value="${not empty param.THE_LOCALE ? param.THE_LOCALE : pageContext.request.locale }"
scope="session" />


<fmt:setLocale value="${theLocale}"/>

<fmt:setBundle basename="com.web.i18n.resources.messages" />


<!DOCTYPE html>
 <html>
 <head>
 	<title>Registration Form</title>
 	<link type="text/css" rel="stylesheet" href="css/styles1.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 	<script src = "http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.1.min.js"></script>
 	
 </head>
 
 <body>
 
<script type="text/javascript">
	

	var formFlag;
	document.addEventListener("DOMContentLoaded", function() {
		  
		<% 
		String flag = (String) request.getAttribute("FLAG"); 
		String heading = (String)request.getAttribute("HEADING"); 
		String companyName = (String) request.getAttribute("UPDATE_ENTRY_NAME");
		String date = (String) request.getAttribute("UPDATE_DATE");
		String add1 = (String) request.getAttribute("UPDATE_ADD1");
		String add2 = (String) request.getAttribute("UPDATE_ADD2");
		String country = (String) request.getAttribute("COUNTRY");
		String state = (String) request.getAttribute("STATE");
		String city = (String) request.getAttribute("CITY");
		String entryId = (String) request.getAttribute("ENTRY_ID");
	 	String value = (String)request.getAttribute("CODE_VALUE.get(i).getValue()");
		%>
 		var flag ="<%= flag%>";
		formFlag = "<%= flag%>";
		var button1 = document.getElementById("button").disabled = false;
		var button2 = document.getElementById("cancel").disabled = false;
		
		if(flag === "FORM")
		{
			/* var button3 = document.getElementById("button").disabled = true;
			var button4 = document.getElementById("cancel").disabled = true; */
			
		}
		
		
		if(flag === "UPDATE")
		{
			var functionality = document.getElementById("function").value = flag;
			var id = document.getElementById("entryId").value = <%= entryId%>;

			
			var heading = "<%= heading%>";
			var h1 = document.getElementById("heading");
			h1.innerHTML = heading;
			
			var div1 = document.getElementById("optionalQuestions");
			div1.style.display = 'none';
			
			var element1 = document.getElementById("company_name").value = "<%= companyName%>";
			var element2 = document.getElementById("company_registration_date").value = "<%= date%>";
			var element3 = document.getElementById("company_address_line1").value = "<%= add1%>";
			var element4 = document.getElementById("company_address_line2").value = "<%= add2%>";
			var element5 = document.getElementById("country_city").value = "<%= city%>";
			var element6 = document.getElementById("button").value = "Update";
			
			
			var select = document.getElementById('country_name');
			removeOptions(select);
			var selected = true;
			
			<c:forEach var="countryName" items="${COUNTRY_CODE_VALUE }">
			
   			   				
		   	    opt = document.createElement("option");
			   	opt.value = "${countryName.value}";
			   	opt.textContent = "${countryName.codeLabel}";
			   	if("${countryName.codeLabel}" === "<%= country%>" && selected)
		   		{
			   		opt.selected = true;
			   		selected = false;
		   		}
			   	
			   	select.appendChild(opt);	
			</c:forEach>
			
			<%-- 
			
			
			opt1 = document.createElement("option");
		   	opt1.textContent = "<%= country%>";
		   	opt1.value = "<%= country%>";
/* 		   	opt1.disabled = true;
 */		   	opt1.selected = true;
		   	select.appendChild(opt1); --%>
<%-- 		   	
	   		var state_name = document.getElementById('state_name');
		   	opt2 = document.createElement("option");
		   	opt2.textContent = "<%= state%>";
		   	opt2.value = "<%= state%>";
/* 		   	opt2.disabled = true;
 */		   	opt2.selected = true;
		   	state_name.appendChild(opt2); --%>

		   	var select = document.getElementById('country_name');
			var fn = select.value;
			
			var state_name = document.getElementById('state_name');
	   		removeOptions(state_name);
		   	
	   		var selectedState = true;
	   		
		   	opt1 = document.createElement("option");
		   	opt1.value = "State";
		   	opt1.textContent = "State";
		   	opt1.disabled = true;
		   	state_name.appendChild(opt1);

	   		<c:forEach var="stateName" items="${STATE_NAME }">
			
	   			var parentId= "${stateName.parentId}";
	   			
	   			if(fn === parentId)
	   			{	   				
			   	    opt = document.createElement("option");
				   	opt.value = "${stateName.value}";
				   	opt.textContent = "${stateName.codeLabel}";
				   	if("${stateName.codeLabel}" === "<%= state%>" && selectedState)
			   		{
				   		opt.selected = true;
				   		selectedState = false;
			   		}
				   	state_name.appendChild(opt);
		   		}
	   		
			</c:forEach>
		   	
		   	
		   	

		}
		
		if(flag === "DELETE")
		{
			var functionality = document.getElementById("function").value = "CONFIRM_DELETE";
			var id = document.getElementById("entryId").value = <%= entryId%>;
			
			var delButton = document.getElementById("button").onclick ="if (!(confirm('Are you sure you want to delete the Company Entry ??'))) return false";
			
			var heading = "<%= heading%>";
			var h1 = document.getElementById("heading");
			h1.innerHTML = heading;
			
			var div1 = document.getElementById("optionalQuestions");
			div1.style.display = 'none';
			

			var element1 = document.getElementById("company_name").value = "<%= companyName%>";
			var element2 = document.getElementById("company_registration_date").value = "<%= date%>";
			var element3 = document.getElementById("company_address_line1").value = "<%= add1%>";
			var element4 = document.getElementById("company_address_line2").value = "<%= add2%>";
			var element5 = document.getElementById("country_city").value = "<%= city%>";
			var element6 = document.getElementById("button").value = "Delete";

			
			var element1 = document.getElementById("company_name").readOnly = true;
			var element2 = document.getElementById("company_registration_date").readOnly = true;
			var element3 = document.getElementById("company_address_line1").readOnly = true;
			var element4 = document.getElementById("company_address_line2").readOnly = true;
			var element5 = document.getElementById("country_city").readOnly = true;
			
			
			var select = document.getElementById('country_name');
			
			removeOptions(select);
			
			opt = document.createElement("option");
		   	opt.textContent = "Country";
		   	opt.value = "Country";
		   	opt.disabled = true;
		   	select.appendChild(opt);
		   	
		   	var selected = true;
		   	
		   	<c:forEach var="countryName" items="${COUNTRY_CODE_VALUE }">
			
  				
		   	    
			   	if("${countryName.value}" === "<%= country%>" && selected)
		   		{
			   		opt = document.createElement("option");
				   	opt.value = "${countryName.value}";
				   	opt.textContent = "${countryName.codeLabel}";
			   		opt.selected = true;
			   		selected = false;
		   		}
			   	
			   	select.appendChild(opt);	
			</c:forEach>
			
		<%-- 	opt1 = document.createElement("option");
		   	opt1.textContent = "<%= country%>";
		   	opt1.value = "<%= country%>";
/* 		   	opt1.disabled = true;
 */		   	opt1.selected = true;
		   	select.appendChild(opt1); --%>
		   	
	   		var state_name = document.getElementById('state_name');
		   	opt2 = document.createElement("option");
		   	opt2.textContent = "<%= state%>";
		   	opt2.value = "<%= state%>";
/* 		   	opt2.disabled = true;
 */		   	opt2.selected = true;
		   	state_name.appendChild(opt2);

			
		}
		});
	
	function removeOptions(selectElement) {
		   var i, L = selectElement.options.length - 1;
		   for(i = L; i >= 0; i--) {
		      selectElement.remove(i);
		   }
		}
	
	$('country_name').ready(function(){
		$('#country_name').change(function (){
			 var select = document.getElementById('country_name');
			 var fn = select.value;
	   		 console.log(fn);
	   		 
	   		var state_name = document.getElementById('state_name');
	   		removeOptions(state_name);
	   		
	   		if(formFlag != "DELETE")
   			{
	   			opt1 = document.createElement("option");
			   	opt1.value = "State";
			   	opt1.textContent = "State";
			   	opt1.disabled = true;
			   	opt1.selected = true;
			   	state_name.appendChild(opt1);
	
		   		<c:forEach var="stateName" items="${STATE_NAME }">
				
		   			var parentId= "${stateName.parentId}";
		   			
		   			
		   			if(fn === parentId)
		   			{	   				
				   	    opt = document.createElement("option");
					   	opt.value = "${stateName.value}";
					   	opt.textContent = "${stateName.codeLabel}";
					   	state_name.appendChild(opt);
			   		}
		   		
				</c:forEach>
   			
   			}
	   		
		   		
	   		 
	   		 
	   		 
		});
	}); 

	
	
	function f3(){
		/* var button6 = document.getElementById("button").readOnly = false;
		var button5 = document.getElementById("cancel").readOnly = false; */
		console.log("Cancel button Clicked");
		var cancelButton = document.querySelector('#cancel');
		var functionality = document.getElementById("function").value = "SHOW";
		window.location='FormServlet';
	}
	
</script>
 
 	<div style="width: 100%;">
 	
 		<div align="center">
 			<h1 align="center" id="heading">In-Corporation Form</h1>
 			
 		</div>
 		<div style="">
 			<p style="padding-left: 1rem;"><span><fmt:message key="80000"/></span></p>
 			
 		</div>
 		
 	</div>
 	
 	
 	<form action="FormServlet" method="POST">
 					
 		<input id="function" type="hidden" name="command" value="ADD"/>
 		<input id="entryId" type="hidden" name="entryId"/>
 	
	 	
	 	<table class="t1" cellspacing="22" cellpadding="10" align="center">
	 	
	 		<tr class="company_description">
	 			<td style="padding-inline-start: 5rem; padding-inline-end: 2rem;vertical-align: top;"><h5>Company Description</h5></td>
	 			<td class="second_column" style="padding-left: 8rem;">Name</td>
	 			<td><input id="company_name" class="input" type="text" name="company_name" placeholder="Name of Company or Business" size="70" required/></td>
	 		</tr>
	 		<tr class="company_description" >
	 			<td></td>
	 			<td class="second_column" style="padding-left: 8rem;">Registration Date</td>
	 			<td><input id="company_registration_date" class="input" type="date" name="company_registration_date" placeholder="Company Registration Date" size="25" required/></td>
	 		</tr>
	 		<tr class="address_div">
	 			<td style="padding-inline-start: 5rem; padding-inline-end: 2rem;vertical-align: top;"><h5>Company Address</h5></td>
	 			<td style="padding: 0;">
	 				<table class="t1" cellspacing="22" cellpadding="10" align="center">
	 					<tr><td class="second_column">Address Line 1</td></tr>
		 				<tr><td class="second_column">Address Line 2</td></tr>
		 				<tr><td class="second_column">Country</td></tr>
		 				<tr><td class="second_column">State</td></tr>
		 				<tr><td class="second_column">City</td></tr>
		 				
	 				</table>
	 			
	 			</td>
	 			<td>
	 				<input id="company_address_line1" class="input" type="text" name="company_address_line1" placeholder="Address Line 1" size="70" required/><br/>
	 				<input id="company_address_line2" class="input" type="text" name="company_address_line2" placeholder="Address Line 2" size="70"/><br/>
	 				
					<select id="country_name" class="country_name" name="country" required>
						<option selected disabled>Country</option>
						
						<c:forEach var="tempNames" items="${COUNTRY_CODE_VALUE }">
						
								<option value="${tempNames.value}"> ${tempNames.codeLabel } </option>
							
						</c:forEach>
					</select>

					</br>
					
					<select id="state_name" class="country_name" name="state" >
						<option selected disabled>State</option>
						
						
					</select>
					
					</br>
	 				<input style="width: 137px;" id="country_city" class="input" type="text" name="address_city" placeholder="City" size="10" required/><br/>
	 			</td>
	 		</tr>
	 		<tr class="company_description">
	 			<td style="padding-inline-start: 5rem; padding-inline-end: 2rem;vertical-align: top;"><h5>Services By Company</h5></td>
	 			<td class="second_column" style="padding-left: 8rem;">License</td>
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
	 		</tr>
	 		<tr class="company_description">
	 			<td></td>
	 			<td class="second_column" style="padding-left: 8rem;">Type</td>
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
				 			<input style="left: -6px; margin-top: 0.5rem; position: relative;" class="input" type="text" 
				 			name="branch_address" placeholder="Address of Branch" size=68/>
				 		</div>
				 		
				 		
				 	</div>
					
				</td>
	 		</tr>
	 	
	 	</table>
 	
 	
	 	<div align="center">
	 		<input class="navigation-button" id="button" class="input" type="submit" value="Submit"/>
			<button class="navigation-button" id="cancel" class="input" onclick='f3();'>Cancel</button>	 		
	 		
	 	</div>
	</form>
	
	<div id="footer" style="position: fixed; bottom: 15px; width: 100%;">
		<a href="FormServlet">Back to List of Entries</a>
	</div>
	<div style="position: absolute; right: 0; bottom: 2px; width: max-content; padding-bottom: 1rem; padding-right: 2rem;">
	
		<c:url var="locale_en_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="THE_LOCALE" value="en_IN"/>	
		</c:url>
		
		<c:url var="locale_ma_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="THE_LOCALE" value="ma_IN"/>	
		</c:url>
		
		<c:url var="locale_hi_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="THE_LOCALE" value="hi_IN"/>	
		</c:url>
	
		<a href="${locale_en_IN }">English (IND)</a>
		|
		<a href="${locale_ma_IN }">Marathi</a>
		|
		<a href="${locale_hi_IN }">Hindi</a>
	
	
	</div>
	
<!-- 	<form action="FormServlet" method="POST">
	
		 <input type="hidden" name="command" value="SHOW"/>
		
		<div align="center">
			<input class="input" type="submit" value="Show"/>
		
		</div>
		
	</form>
	
	<form action="delete-entry.jsp">
	
		
		<div align="center">
			<input class="input" type="submit" value="Delete"
			onclick="if (!(confirm('Are you sure you want to delete the Company Entry ??'))) return false"/>
			
		
		</div>
		
	</form> -->
	
	
   	<script src="js/scripts1.js"></script>
  	
 </body>
 
 
 </html>
 
 
 
 <!--  <script type="text/javascript">
 
 		
		 
 
 		
 		element2.style.display = 'none';
 		element3.style.display = 'none'; 
 		
 		optionalQuestions.style.display = 'block';
 		askBranch.style.display = 'block';
 		askAddress.style.display = 'block';
 		 
  		function selectCountry(){
			var countryName = String(document.getElementById("country_name").value);
			console.log(countryName);
			
			
 			window.location.replace("registration-form.jsp?COUNTRY_NAME=" + countryName);
 
		    }
  		 var sThisVal;
  		$('input:checkbox.license').each(function () {
  	       var sThisVal = (this.checked ? $(this).val() : "");
  	     console.log(sThisVal);
  	  });
 	
 </script>  -->
 
 
 
 
 
 