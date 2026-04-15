<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2 style="color: red;">Student Information Form</h2>
  
     <form action="saveStudent">
     
    <!--    <input type="number" placeholder="Enter Your Id" name="studentId"><br><br> -->
       <input type="text" placeholder="Enter Your Name" name="studentFullName"><br><br>
       <input type="text" placeholder="Enter Your Email" name="studentEmail"><br><br>
       <input type="number" placeholder="Enter Your age" name="studentAge"><br><br>
       <input type="text" placeholder="Enter Your College name" name="studentCollageName"><br><br>
       
       <input type="number" placeholder="Enter  pincode" name="address.pincode"><br><br>
       <input type="text" placeholder="Enter  city" name="address.city"><br><br>
       <input type="text" placeholder="Enter  state" name="address.state"><br><br>
       
       <input type="submit" value="SUBMIT">
        
     
     </form>
  	<br>
  		Already have account? <a href="login">LOGIN HERE</a>
</body>
</html>