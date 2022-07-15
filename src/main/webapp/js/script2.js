var exp1 = document.querySelector('#exp1');
var exp2 = document.querySelector('#exp2');
var exp2 = document.querySelector('#exp3');

var expButton = document.querySelector('#expButton');

function enableButton(){
	
	console.log("enableButton function called");
	
	if(exp1.checked || exp2.checked || exp3.checked){
		document.getElementById("expButton").style.display = "block";
	
	
		var checkedValue = $('.export:checked').val();
		console.log(checkedValue);
	}
	
}

function exportFile(){
			console.log("exp-button button clicked");
			var checkedValue = $('.export:checked').val();
			console.log(checkedValue);
			document.getElementById("command").value = "SHOW";
			var filterType = document.getElementById("filterType").value;
			var filterLicense = document.getElementById("filterLicense").value;
			
			
			document.getElementById("searchType").value = filterType;
			document.getElementById("searchLicense").value = filterLicense;		
				
			var companySearch = document.getElementById("myInput").value;
			document.getElementById("search").value = companySearch;
			
			if(checkedValue == "pdf") 
			{
				document.getElementById("export").value = "PDF";
				console.log("pdf command received " + document.getElementById("export").value);
				var companySearch = document.getElementById("myInput").value;
				document.getElementById("search").value = companySearch;				
				console.log("search on bar is " + document.getElementById("search").value);
				return true;
			}
			else if(checkedValue == "exl") 
			{
				document.getElementById("export").value = "EXL";
				document.getElementById("search").value = companySearch;
				console.log("exl command received " + document.getElementById("export").value);
				console.log("search on bar is " + document.getElementById("search").value);
				console.log("Hello");
				return true;
			}
			else if(checkedValue == "pd4ml") 
			{
				document.getElementById("export").value = "PD4ML";
				document.getElementById("search").value = companySearch;
				console.log("pd4ml command received " + document.getElementById("export").value);
				console.log("search on bar is " + document.getElementById("search").value);
				console.log("Hello");
				return true;
			}
			return false;

	}


function searchResult(){
	
	var companySearch = document.getElementById("myInput").value;
	var filterType = document.getElementById("filterType").value;
	var filterLicense = document.getElementById("filterLicense").value;
	
	
	
	if(companySearch == "" && filterLicense == "" && filterType == "") 
	{
		console.log("no search");
		return false;
	}
	else
	{
		console.log(companySearch + " " + filterType + "  " + filterLicense);
	
		document.getElementById("search").value = companySearch;
		document.getElementById("searchType").value = filterType;
		document.getElementById("searchLicense").value = filterLicense;
		document.getElementById("command").value = "SHOW";
		console.log(document.getElementById("search").value);
		return true;
	}
	
}
