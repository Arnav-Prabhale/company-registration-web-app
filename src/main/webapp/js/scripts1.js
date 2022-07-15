var div1 = document.querySelector('#askBranch');
var div2 = document.querySelector('#askAddress');

var mainDiv = document.querySelector('#optionalQuestions');

var rd1 = document.querySelector('#radio1');
var rd2 = document.querySelector('#radio2');
var rd3 = document.querySelector('#radio3');

document.addEventListener("DOMContentLoaded", function() {
	div1.style.display = 'none';
	div2.style.display = 'none';
	           // Hide

	
	
	});

function f1(){
	
	console.log("f1 function called");
	
	if(rd1.checked){
		div1.style.display = 'none';
		
		div2.style.display = 'none';
	}
	else
	{
		div1.style.display = 'block';
	}
}

function f2(){
	console.log("f2 function called");
	if(rd3.checked)
	{
		div2.style.display = 'block';
	}
	else{
		div2.style.display = 'none';
	}
}



/*
function selectCountry(){
	let countryName = document.getElementById("country_name").value;
	console.log(countryName);
}




 $(country_name).find('option:selected').each(function() {
  
  var fn = document.getElementById('country_name').value;
    console.log(fn);
  
  
  });


$('country_name').change(function() {
   var fn = document.getElementById('country_name').value;
    console.log(fn);
  };



$('country_name').ready(function(){
	$('#country_name').change(function (){
		 var fn = document.getElementById('country_name').value;
   		 console.log(fn);
	});
});



//var element1 = document.querySelector('#optionalQuestions');
//		 element1.style.display='none';



$(document).ready(function(){
	$('#country_name').change(function (){
		console.log($('#country_name option:selected').text())
	});
});


function selectCountry(){
	let countryName = document.getElementById("country_name").value;
	console.log(countryName);
}

  */
  
 
 function f3(){
		/* var button6 = document.getElementById("button").readOnly = false;
		var button5 = document.getElementById("cancel").readOnly = false; */
		console.log("Cancel button Clicked");
		var cancelButton = document.querySelector('#cancel');
		var functionality = document.getElementById("function").value = "SHOW";
		window.location='FormServlet';
	}
  
  
   