<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 

<script src="components/jquery-3.6.0.js"></script>
<script src="components/main.js"></script>



 


<div class="container"> 
		<div class="row">  
		 <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                       

                        <caption>
                            <h2>
                                Inquiry Management
                            </h2>
                        </caption>
		
			
				<form id="formInquiryId" name="formInquiryId" method="post" action="Inquiry.jsp">  
					Inquiry Name:  
					<input id="InquiryName" name="InquiryName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Inquiry Acc:  
					<input id="InquiryAcc" name="InquiryAcc" type="text" class="form-control form-control-sm">  
					
					<br>
					 Inquiry Reason:  
					 <input id="InquiryReason" name="InquiryReason" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Inquiry Date:  
					 <input id="InquiryDate" name="InquiryDate" type="text" class="form-control form-control-sm">
                        
					<br>	
                     Inquiry pNo:  
					 <input id="InquirypNo" name="InquirypNo" type="text" class="form-control form-control-sm">					 
					 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidinquiryIDSave" name="hidinquiryIDSave" value=""> 
					 
					 
				</form> 
				  </div>
                </div>
            </div>
    
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
					
				
            <div class="row">
               

                <div class="container">
                    <h3 class="text-center">Inquiry Details</h3>
                    <hr>
                    <div class="container text-left">

                      
                    </div>
                    <br>
                
                   <div id="divItemsGrid">   
					<%    
						Inquiry inquiryObj = new Inquiry();
						out.print(inquiryObj.readInquiry());   
					%>  
				
					<br>
					<br>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		</div>    
 		
<br>
	 

</body>

</html>