$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateInquiryForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidinquiryIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "InquiryApi",
		type : t,
		data : $("#formInquiry").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onInquirySaveComplete(response.responseText, status);
		}
	});
}); 

function onInquirySaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidinquiryIDSave").val("");
	$("#formInquiry")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidinquiryIDSave").val($(this).closest("tr").find('#hidinquiryIDUpdate').val());     
	$("#inquiryName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#inquiryAcc").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#inquiryReason").val($(this).closest("tr").find('td:eq(2)').text());  
    $("#inquiryDate").val($(this).closest("tr").find('td:eq(2)').text());	
	$("#inquirypNo").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "InquiryApi",
		type : "DELETE",
		data : "inquiryID=" + $(this).data("inquiryID"),
		dataType : "text",
		complete : function(response, status)
		{
			onInquiryDeletedComplete(response.responseText, status);
		}
	});
});

function onInquiryDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateInquiryForm() {  
	// NAME  
	if ($("#inquiryName").val().trim() == "")  {   
		return "Insert inquiryName.";  
		
	} 
	
	 // Account 
	if ($("#inquiryAcc").val().trim() == "")  {   
		return "Insert inquiryAcc.";  
	} 
	
	
	//Reason
	if ($("#inquiryReason").val().trim() == "")  {   
		return "Insert inquiryReason."; 
		 
	}
	//Date
	if ($("#inquiryDate").val().trim() == "")  {   
		return "Insert inquiryDate."; 
		 
	}
	 
	 // is numerical value  
	var tmpMobile = $("#inquirypNo").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 

		

	 
	 return true; 
	 
}
