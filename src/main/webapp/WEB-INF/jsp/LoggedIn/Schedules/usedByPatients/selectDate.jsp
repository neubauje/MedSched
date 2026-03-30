<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/common/toolbar.jspf" %>

<body>
<c:url value="/selectDate" var="selectDateURL"/>
<form action="${selectDateURL}" method="POST">
    <h2>Enter Information To Book An Appointment</h2>
    <br>
    <div class="timeslot">
        <p>Searching for available appointments with Dr. ${doctor.firstName} ${doctor.lastName}</p>
        <label class="createdatelabel" for="date">Select a Date to search for availability (or leave blank to see all)</label>
        <input type="date" name="date" id="date" class="form-control"/>
        <%--<form:errors path="date" cssClass="error"/>--%>
        <br>
    </div>
    <input class="formSubmitButton"  type="submit" value="Submit" class="btn btn-primary"/>
</form>
</body>
<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>