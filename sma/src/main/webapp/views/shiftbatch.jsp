<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Shift Batch</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow-sm border-0">
                
                <div class="card-header bg-primary text-white text-center">
                    <h5 class="mb-0">Shift Batch</h5>
                </div>

                <div class="card-body">

                    <form action="updateBatch" method="post">

                        <input type="hidden" name="studentId" value="${student.studentId}" />

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Student Name</label>
                            <input type="text" class="form-control" 
                                   value="${student.studentFullName}" readonly>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Batch Mode</label>
                            <select name="batchMode" class="form-select">
                                <option value="Online">Online</option>
                                <option value="Offline">Offline</option>
                            </select>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Batch Number</label>
                            <select name="batchNumber" class="form-select">
                                <option value="FDJ-160">FDJ-160</option>
                                <option value="REG-199">REG-199</option>
                                <option value="FDJ-199">FDJ-199</option>
                                <option value="REG-162">REG-162</option>
                            </select>
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-success w-100">
                                Update Batch
                            </button>
                            
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
