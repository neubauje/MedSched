<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "../../../common/header.jspf" %>

<c:url var="registerUrl" value="/doctorRegister"/>
<body>
<form:form action="${registerUrl}" method="POST" modelAttribute="doctor">
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
        <label for="specialty">Specialty</label>
        <form:input cssClass="form-control" path="specialty" placeholder="e.g. Dermatology or LGBTQ or Trauma"/>
        <form:errors path="specialty" cssClass="bg-danger"/>
    </div>
        <div class="form-group">
            <label for="officeId">Office/Practice Name</label>
            <form:select path="officeId">
                <c:forEach items="${offices}" var="office">
                    <form:option value="${office.officeId}">${office.officeName} (${office.address})</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="officeId" cssClass="bg-danger"/>
        </div>
    <button type="submit" class="btn btn-default">Save Doctor Profile</button>
</form:form>
</body>
<%@ include file = "../../../common/footer.jspf" %>