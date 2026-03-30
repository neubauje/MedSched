<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "../../../common/header.jspf" %>

<c:url var="registerUrl" value="/patientRegister"/>
<form:form action="${registerUrl}" method="POST" modelAttribute="patient">
    <div class="form-group">
        <label for="firstName">First Name</label>
        <form:input cssClass="form-control" path="firstName" placeholder="First Name"/>
        <form:errors path="firstName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <form:input cssClass="form-control" path="lastName" placeholder="Last Name"/>
        <form:errors path="lastName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Date of Birth</label>
        <form:input type="date" path="dateOfBirth" cssClass="form-control" placeholder="YYYY-MM-DD"/>
        <form:errors path="dateOfBirth" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="phoneNumber">Phone Number</label>
        <form:input path="phoneNumber" cssClass="form-control" placeholder="numbers only"/>
        <form:errors path="phoneNumber" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="email">Email address</label>
        <form:input path="email" cssClass="form-control" placeholder="example@mail.com"/>
        <form:errors path="email" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="emergencyName">Name of your emergency contact</label>
        <form:input path="emergencyName" cssClass="form-control" placeholder="Name of contact"/>
        <form:errors path="emergencyName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="emergencyContact">Phone number of your emergency contact</label>
        <form:input path="emergencyContact" cssClass="form-control" placeholder="numbers only"/>
        <form:errors path="emergencyContact" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="insurance">Name of your health insurance carrier</label>
        <form:input path="insurance" cssClass="form-control" placeholder="e.g. Aetna or BCBS"/>
        <form:errors path="insurance" cssClass="bg-danger"/>
    </div>
    <button type="submit" class="btn btn-default">Save Patient Profile</button>
</form:form>

<%@ include file = "../../../common/footer.jspf" %>