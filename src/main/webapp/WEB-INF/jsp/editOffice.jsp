<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" value="MedSched"/>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/common/toolbar.jspf" %>
<c:url var="cssUrl" value="/css/site.css"/>
<link rel="stylesheet" href="${cssUrl}"/>

<body>
    <h2>Update the info for this office</h2>
    <c:url var="formAction" value="/editOffice"/>
    <form:form method="POST" action="${formAction}" modelAttribute="office">
        <form:hidden path="officeId" id="officeId" value="${office.officeId}"/>
        <form:hidden path="officeName" id="officeName" value="${office.officeName}"/>
        <div class="showOneOffice">
            <label for="address">Address</label>
            <form:input type="text" name="address" id="address" path="address" placeholder="${office.address}"/>
        </div>
        <div class="showOneOffice">
            <label for="openingHours">Opening Hours</label>
            <form:input type="time" name="openingHours" id="openingHours" path="openingHours" placeholder="${office.openingHours}"/>
        </div>
        <div class="showOneOffice">
            <label for="closingHours">Closing Hours</label>
            <form:input type="time" name="closingHours" id="closingHours" path="closingHours" placeholder="${office.closingHours}"/>
        </div>
        <div class="showOneOffice">
            <label for="phoneNumber">Phone Number</label>
            <form:input type="text" name="phoneNumber" id="phoneNumber" path="phoneNumber" placeholder="${office.phoneNumber}"/>
        </div>
        <div class="showOneOffice">
            <label for="costPerHour">Cost Per Hour</label>
            <form:input type="number" name="costPerHour" id="costPerHour" path="costPerHour" placeholder="${office.costPerHour}"/>
        </div>
        <input class="formSubmitButton" type="submit" value="Submit"/>
    </form:form>
</body>
<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>