<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<c:url var="editPatientUrl" value="/editPatient"/>
<body>
<form:form action="${editPatientUrl}" method="POST" modelAttribute="patient">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <form:input cssClass="form-control" path="firstName" placeholder="${patient.firstName}"/>
            <form:errors path="firstName" cssClass="bg-danger"/>
        </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <form:input cssClass="form-control" path="lastName" placeholder="${patient.lastName}"/>
        <form:errors path="lastName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="dateOfBirth">Date of Birth</label>
        <form:input type="date" cssClass="form-control" path="dateOfBirth" placeholder="${patient.dateOfBirth}"/>
        <form:errors path="dateOfBirth" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="phoneNumber">Phone Number</label>
        <form:input path="phoneNumber" cssClass="form-control" type="numeric" placeholder="${patient.phoneNumber}"/>
        <form:errors path="phoneNumber" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="email">Email Address</label>
        <form:input cssClass="form-control" path="email" placeholder="${patient.email}"/>
        <form:errors path="email" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="emergencyName">Emergency contact name</label>
        <form:input cssClass="form-control" path="emergencyName" placeholder="${patient.emergencyName}"/>
        <form:errors path="emergencyName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="emergencyContact">Emergency contact phone number</label>
        <form:input type="numeric" cssClass="form-control" path="emergencyContact" placeholder="${patient.emergencyContact}"/>
        <form:errors path="emergencyContact" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="insurance">Insurance provider</label>
        <form:input cssClass="form-control" path="insurance" placeholder="${patient.insurance}"/>
        <form:errors path="insurance" cssClass="bg-danger"/>
    </div>
        <button type="submit" class="btn btn-default">Save Profile</button>
    </form:form>
</body>
<%@ include file = "../../../common/footer.jspf" %>