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
		<title>Entry List</title>
		<link type="text/css" rel="stylesheet" href="css/cssDisplayEntry.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 		<script src = "http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.1.min.js"></script>
 	
 	
	</head>
<body>
	<script type="text/javascript">

	</script>
	
	
	
	
	
	<div id="wrapper">
		<div id="header">
			<h2><fmt:message key="80001"/></h2>
		</div>
	</div>
	
	<form action="FormServlet" method="POST">
	
		 <input type="hidden" id="command" name="command" value="FORM"/>
		 <input type="hidden" id="export" name="export" />
		 <input type="hidden" id="search" name="search" value='<c:out value="${search}"/>' />
		 <input type="hidden" id="searchType" name="searchType" value='<c:out value="${filterType}"/>' />
		 <input type="hidden" id="searchLicense" name="searchLicense" value='<c:out value="${filterLicense}"/>' />
		<div>
		
			<div style="display: inline-flex;">
				<input type="submit" value="<fmt:message key="80011"/>" class="add-entry-button" style="margin-bottom: 1rem;"/>	
				
							
				<div class="exportDiv">
					<div>
						<input type="radio" class="export" name="export" value="pdf" id="exp1" onclick='enableButton()'> PDF
						<input type="radio" class="export" name="export" value="exl" id="exp2" onclick='enableButton()'> EXCEL
						<input type="radio" class="export" name="export" value="pd4ml" id="exp3" onclick='enableButton()'> PD4ML
					</div>
					
					
					
					<button id="expButton" type="submit" class="expButton" onclick='return exportFile()'>Export</button>
<!-- 					<button id="searchButton" type="submit" class="searchButton" onclick='return searchResult()'>Search</button>
 -->					
					
					<%-- <c:choose>
					    <c:when test="${search != null}">
					         
							<button  id="expButton" type="submit"  class="exp-button" onclick='return exportFile()' style="margin-left: 1.4rem; display: none;">Export</button>
							<input type="hidden" id="search" name="search" value='<c:out value="${search}"/>' />
					    </c:when>    
					    <c:otherwise>
					        
					        <button  id="expButton" type="submit"  class="exp-button" onclick='return exportFile()' style="margin-left: 1.4rem; display: none;">Export</button>
					
					    </c:otherwise>
					</c:choose> --%>
					
					
					
					
				</div>
			</div>	
				
			
			<div style="float:right; justify-content:center; display:inline-flex">
				<p style="justify-content:center;"><c:out value="${DATE}"/></p>
				<p style="padding-left: 1rem;"><span><fmt:message key="80000"/></span></p>
			</div>
		
		</div>

	<div class="search">
	<!-- onkeyup="myFunction()" -->
		<%-- <input type="text" id="myInput"  placeholder="Search for Company.." title="Type in a name">
		
		<c:if test = "${search != null}">
			<input type="text" id="myInput"  placeholder="Search for Company.." title="Type in a name" value='<c:out value="${search}"/>'>
		</c:if>
		 --%>
		<c:choose>
		    <c:when test="${search != null}">
		         
		        <input class ="myInput" type="text" id="myInput"  placeholder="Search for Company.." title="Type in a name" value='<c:out value="${search}"/>'>
		        <input type="hidden" id="search" name="search" value='<c:out value="${search}"/>' />
		    </c:when>    
		    <c:otherwise>
		        
		        <input class ="myInput" type="text" id="myInput"  placeholder="Search for Company.." title="Type in a name">
		        
		    </c:otherwise>
		</c:choose>
		
		<div>
			<button id="searchButton" type="submit" class="searchButton" onclick='return searchResult()'>Search</button>
			
		</div>
	</div>
	
	
	<div class="filter search">
		
		<c:choose>
		    <c:when test="${searchType != null }">
		         
        		<input class ="myInput" type="text" id="filterType"  placeholder="Type" title="Type in a Type" value='<c:out value="${searchType}"/>'>
		        <input type="hidden" id="filterType" name="filterType" value='<c:out value="${searchType}"/>' />
		        
		    </c:when>    
		    <c:otherwise>
		        
        		<input class ="myInput" type="text" id="filterType"  placeholder="Type not found" title="Type in a Type">
		        
		    </c:otherwise>
		</c:choose>
		
		<c:choose>
		    <c:when test="${searchLicense != null }">
		         
        		<input class ="myInput" type="text" id="filterLicense"  placeholder="License" title="Type in a License" value='<c:out value="${searchLicense}"/>'>
		        <input type="hidden" id="filterLicense" name="filterLicense" value='<c:out value="${searchLicense}"/>' />
		        
		    </c:when>    
		    <c:otherwise>
		        
        		<input class ="myInput" type="text" id="filterLicense"  placeholder="License not found" title="Type in a License" >
		        
		    </c:otherwise>
		</c:choose>
		<%-- 
        <input class ="myInput" type="text" id="filterType"  placeholder="Type" title="Type in a Type" value='<c:out value="${filterType}"/>'>
        <input class ="myInput" type="text" id="filterLicense"  placeholder="License" title="Type in a License" value='<c:out value="${filterLicense}"/>'>
		 --%>
		<div>
			<button class ="myInput" id="searchButton" type="submit" class="searchButton" onclick='return searchResult()'>Filter</button>	
		</div>
	</div>



		
	</form>
	
	
	
			
	
	
	<table>
		<tr>
			<th><fmt:message key="80002"/></th>
			<th><fmt:message key="80003"/></th>
			<th><fmt:message key="80004"/></th>
			<th><fmt:message key="80005"/></th>
			<!-- <th>Company Address 2</th>
			<th>Company Address 3</th> -->
			<th><fmt:message key="80006"/></th> 
			<th><fmt:message key="80007"/></th>
			<th><fmt:message key="80008"/></th>
			
		</tr>
	
	<%-- <c:forEach begin="20" end="49" var="i">
    <option value="${i}">${i}</option>
</c:forEach> --%>
		<c:set var="index" value="${0 }"></c:set>
		<c:set var="printList" value="${COMPANY_ENTRY_LIST}"/>
		<c:forEach begin="0" end="100" var="entry" items="${COMPANY_ENTRY_LIST}">
			
			
			<c:url var="deleteLink" value="FormServlet">
				<c:param name="command" value="DELETE"/>
				<c:param name="entryId" value="${entry.id }"/>	
				<c:param name="THE_LOCALE" value= "${theLocale}"/>
			</c:url>
			
			<c:url var="updateLink" value="FormServlet">
				<c:param name="command" value="LOAD"/>
				<c:param name="entryId" value="${entry.id }"/>
				<c:param name="THE_LOCALE" value= "${theLocale}"/>	
			</c:url>
			<c:set var="serialNumber" value="${index + START_INDEX }"></c:set>
  
			<tr>
			
				
			
 				<td style="text-align: right; padding-right: 1.5rem;">${serialNumber}</td>
				<td style="text-align: left; padding-left: 1.5rem;">${entry.name }</td>
				<td>${entry.registrationDate }</td>
				<td style="text-align: left; padding-left: 1.5rem;">${entry.completeAddress }</td>
				<%-- <td>${entry.addressLine2 }</td>
				<td>${entry.city}, ${entry.state}, ${entry.country} </td> --%>
				<td>${entry.license }</td>
				<td>${entry.type }</td>
				<td>
					 <button class="function-button" type="button" onclick="window.location.href = '${updateLink }'"><fmt:message key="80009"/></button>
				 
					<%-- <a href="${updateLink }">Update</a>
					|
					<a href="${deleteLink }"
					onclick="if (!(confirm('Are you sure you want to delete the Company Entry ??'))) return false">
					 Delete</a> --%>
					<button class="function-button" type="button" onclick="window.location.href = '${deleteLink }'"><fmt:message key="80010"/></button>
					
				</td>
			
			
			
			
			</tr>
			
		
		<c:set var="index" value="${index+1}"></c:set>
		</c:forEach>
		
	
	</table>
	
	
	<div class="navigation-dock">
	
		<div>
			<%--For displaying Previous link except for the 1st page --%>
		    <c:if test="${CURRENT_PAGE != 1}">
		    	<c:url var="pageLink" value="FormServlet">
					<c:param name="command" value="SHOW"/>
					<c:param name="PAGE" value="${CURRENT_PAGE - 1}"/>
					<c:param name="THE_LOCALE" value= "${theLocale}"/>		
					<c:param name="search" value= "${search}"/>			
					<c:param name="searchType" value= "${searchType}"/>			
					<c:param name="searchLicense" value= "${searchLicense}"/>	
				</c:url>
		        <td>
		        	<button class="navigation-button navigator" type="button" onclick="window.location.href = '${pageLink }'"><fmt:message key="80013"/></button>
		        </td>
		    </c:if>
		</div>
		
		
	 
	 	<div>
	 		<%--For displaying Page numbers. 
		    The when condition does not display a link for the current page--%>
		    <table class="page-table" cellpadding="5" cellspacing="5" >
		        <tr>
		            <c:forEach begin="1" end="${NO_OF_PAGES}" var="i">
		                <c:choose>
		                    <c:when test="${CURRENT_PAGE eq i}">
		                        <td>${i}</td>
		                    </c:when>
		                    <c:otherwise>
		                    	<c:url var="pageLink" value="FormServlet">
									<c:param name="command" value="SHOW"/>
									<c:param name="PAGE" value="${i}"/>	
									<c:param name="THE_LOCALE" value= "${theLocale}"/>	
									<c:param name="search" value= "${search}"/>			
									<c:param name="searchType" value= "${searchType}"/>			
									<c:param name="searchLicense" value= "${searchLicense}"/>
								</c:url>
						                
		                        <td>
		                        	<button class="navigation-button" type="button" onclick="window.location.href = '${pageLink }'">${i}</button>
		                        </td>
		                    </c:otherwise>
		                </c:choose>
		            </c:forEach>
		        </tr>
		    </table>
	 	
	 	</div>
	    
	    
	    	
	    
	    
	    
	    <div>
	    	<%--For displaying Next link --%>
		    <c:if test="${CURRENT_PAGE lt NO_OF_PAGES}">
		    
		    	<c:url var="pageLink" value="FormServlet">
					<c:param name="command" value="SHOW"/>
					<c:param name="PAGE" value="${CURRENT_PAGE + 1}"/>	
					<c:param name="THE_LOCALE" value= "${theLocale}"/>	
					<c:param name="search" value= "${search}"/>			
					<c:param name="searchType" value= "${searchType}"/>			
					<c:param name="searchLicense" value= "${searchLicense}"/>
				</c:url>
		    
		        <td>
		       		<button class="navigation-button navigator" type="button" onclick="window.location.href = '${pageLink }'"><fmt:message key="80012"/></button>
		    	</td>
		    </c:if>
	    
	    </div>
		     
	    
	    
		     
	    
	</div>
	
	
	<div style="position: relative; right: 0; bottom: 2px; width: max-content; padding-bottom: 1rem; padding-right: 2rem;">
	
		<c:url var="locale_en_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="THE_LOCALE" value="en_IN"/>
			<c:param name="PAGE" value="${CURRENT_PAGE}"/>		
		</c:url>
		
		<c:url var="locale_ma_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="THE_LOCALE" value="ma_IN"/>	
			<c:param name="PAGE" value="${CURRENT_PAGE}"/>	
		</c:url>
		
		<c:url var="locale_hi_IN" value="FormServlet">
			<c:param name="command" value="SHOW"/>
			<c:param name="PAGE" value="${CURRENT_PAGE}"/>	
			<c:param name="THE_LOCALE" value="hi_IN"/>	
		</c:url>
	
		<a href="${locale_en_IN }">English (IND)</a>
		|
		<a href="${locale_ma_IN }">Marathi</a>
		|
		<a href="${locale_hi_IN }">Hindi</a>
	
	
	</div>
	
	<script src="js/script2.js"></script>
</body>

</html>