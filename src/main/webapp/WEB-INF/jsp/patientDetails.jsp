<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<body>
<c:url value="/patientDetails" var="patientURL"/>
<div id="main-content">
    <h2>Patient Info:</h2>
    <div class="patients">
        <div class="patient">
            <p>Patient name: ${patient.firstName} ${patient.lastName}</p>
            <p>Date of birth: ${patient.dateOfBirth}</p>
            <p>Phone number: ${patient.phoneNumber}</p>
            <p>Email address: ${patient.email}</p>
            <p>Insurance: ${patient.insurance}</p>
            <br>
            <p>Emergency Contact: ${patient.emergencyName}</p>
            <p>Emergency phone: ${patient.emergencyContact}</p>
        </div>
    </div>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>