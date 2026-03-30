<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<body>
<c:url value="/addDetails" var="addDetailsURL"/>
<form action="${addDetailsURL}" method="POST">
    <h2>Enter reason for appointment, then submit to book an appointment</h2>
    <br>
    <div class="timeslot">
        <p>Booking appointment with Dr. ${doctor.firstName} ${doctor.lastName}</p>
        <p>On ${timeslot.date} at ${timeslot.startTime} <br> <label>Reason for Visit</label></p>

        <%--<input path="description" type="textarea" name="description" class="form-control"/>--%>
        <textarea name="description" id="description" rows="5" cols="33"></textarea>
    </div>
    <input class="formSubmitButton" type="submit" value="Submit" class="btn btn-primary"/>
</form>
</body>
<%@ include file="../../../common/footer.jspf" %>