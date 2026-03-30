<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" value="Create Your New Office"/>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/common/toolbar.jspf" %>
<c:url var="cssUrl" value="/css/site.css"/>
<link rel="stylesheet" href="${cssUrl}"/>

<div id="main-content">
    <h2>New Office Information Form</h2>

    <c:url var="formAction" value="/addNewOffice"/>

    <form:form method="POST" action="${formAction}" modelAttribute="office">
        <div class="showOneOffice">
            <label for="officeName">Practice Name</label>
            <form:input type="text" name="officeName" id="officeName" path="officeName"
                        placeholder="E.g. 'Healing Hearts'"/>
            <form:errors path="officeName" cssClass="bg-danger"/>
        </div>
        <div class="showOneOffice">
            <label for="address">Address</label>
            <form:input type="text" name="address" id="address" path="address"
                        placeholder="1111 Example Avenue, Columbus, OH, 43081"/>
            <form:errors path="address" cssClass="bg-danger"/>
        </div>
        <div class="showOneOffice">
            <label for="address">Days Open</label>
            <form:input type="text" name="address" id="address" path="daysOpen"
                        placeholder="E.g. Monday through Friday"/>
        </div>
        <div class="showOneOffice">
            <label for="openingHours">Opening Hours</label>
            <form:input type="time" name="openingHours" id="openingHours" path="openingHours" placeholder="08:00"/>
            <form:errors path="openingHours" cssClass="bg-danger"/>
        </div>
        <div class="showOneOffice">
            <label for="closingHours">Closing Hours</label>
            <form:input type="time" name="closingHours" id="closingHours" path="closingHours" placeholder="18:00"/>
            <form:errors path="closingHours" cssClass="bg-danger"/>
        </div>
        <div class="showOneOffice">
            <label for="phoneNumber">Phone Number</label>
            <form:input type="text" name="phoneNumber" id="phoneNumber" path="phoneNumber"
                        placeholder="E.g. 6147854658"/>
            <form:errors path="phoneNumber" cssClass="bg-danger"/>
        </div>
        <div class="showOneOffice">
            <label for="costPerHour">Cost Per Hour</label>
            <form:input type="number" name="costPerHour" id="costPerHour" path="costPerHour" placeholder="0"/>
            <form:errors path="costPerHour" cssClass="bg-danger"/>
        </div>
        <input class="formSubmitButton" type="submit" value="Submit"/>
    </form:form>
</div>


<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>