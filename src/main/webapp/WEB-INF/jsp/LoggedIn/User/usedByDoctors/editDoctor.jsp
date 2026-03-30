<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<c:url var="editProfileUrl" value="/editDoctor"/>
<body>
<form:form action="${editProfileUrl}" method="POST" modelAttribute="doctor">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <form:input cssClass="form-control" path="firstName" placeholder="${doctor.firstName}"/>
            <form:errors path="firstName" cssClass="bg-danger"/>
        </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <form:input cssClass="form-control" path="lastName" placeholder="${doctor.lastName}"/>
        <form:errors path="lastName" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="specialty">Specialty</label>
        <form:input cssClass="form-control" path="specialty" placeholder="${doctor.specialty}"/>
        <form:errors path="specialty" cssClass="bg-danger"/>
    </div>
    <div class="form-group">
        <label for="officeId">Office</label>
        <form:select cssClass="form-control" path="officeId" placeholder="${doctor.officeId}">
            <c:forEach items="${offices}" var="office">
                <form:option value="${office.officeId}">${office.officeName} (${office.address})</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="officeId" cssClass="bg-danger"/>
    </div>
        <button type="submit" class="btn btn-default">Save Profile</button>
    </form:form>
</body>
<%@ include file = "../../../common/footer.jspf" %>