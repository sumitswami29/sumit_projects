<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow mb-4">
                <div class="card-body">
                    <h3 class="text-center mb-4">Dashboard</h3>

                    <p><strong>Account Number:</strong> ${account.accountNumber}</p>
                    <p><strong>Name:</strong> ${account.name}</p>
                    <p><strong>Email:</strong> ${account.email}</p>
                    <p><strong>Balance:</strong> ${account.balance}</p>
                </div>
            </div>

            <div class="d-grid gap-2">
                <a href="depositPage?accNo=${account.accountNumber}" class="btn btn-success">Deposit</a>
                <a href="withdrawPage?accNo=${account.accountNumber}" class="btn btn-warning">Withdraw</a>
                <a href="history" class="btn btn-primary">Transaction History</a>
                <a href="logout" class="btn btn-danger">Logout</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>
