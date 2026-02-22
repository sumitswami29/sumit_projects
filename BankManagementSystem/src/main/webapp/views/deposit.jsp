<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit Amount</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card shadow">
                <div class="card-body">

                    <h3 class="text-center mb-4">Deposit Amount</h3>

                    <form action="deposit">
                        <div class="mb-3">
                            <label class="form-label">Account Number</label>
                            <input type="text" name="accountNumber" value="${account.accountNumber}" class="form-control" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Current Balance</label>
                            <input type="text" value="${account.balance}" class="form-control" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Deposit Amount</label>
                            <input type="text" name="amount" class="form-control">
                        </div>

                        <div class="d-grid">
                            <input type="submit" value="Deposit" class="btn btn-success">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
