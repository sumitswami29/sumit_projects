<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">

            <h3 class="text-center mb-4">Transaction History</h3>

            <div class="mb-3">
                <strong>Account Number:</strong> ${acc.accountNumber}<br>
                <strong>Name:</strong> ${acc.name}
            </div>

            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>Transaction ID</th>
                        <th>Type</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${acc.transaction}" var="tr">
                        <tr>
                            <td>${tr.trid}</td>
                            <td>${tr.type}</td>
                            <td>${tr.amount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>
