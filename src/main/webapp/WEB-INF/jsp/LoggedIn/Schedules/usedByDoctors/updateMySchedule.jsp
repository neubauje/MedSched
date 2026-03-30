<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<body>
<c:url value="/updateMySchedule" var="updateScheduleURL"/>

<br>
<a href="${pageContext.request.contextPath}/createSlots"><button>Create a new available timeslot</button></a>
<br>
<form action="${updateScheduleURL}" method="POST">
    <h2>Your current scheduled timeslots</h2>
    <br>
    <table class="table">
        <thead>
        <th>Date</th>
        <th>Time</th>
        <th>Patient</th>
        <th>Reason for visit</th>
        <th>Delete?</th>
        </thead>
    <c:forEach items="${timeslots}" var="timeslot">
        <c:forEach items="${patients}" var="patient">
            <c:if test="${patient.patientId == timeslot.patientId}">
                <c:set var="bookedPatient" value="${patient}"/>
            </c:if>
        </c:forEach>
        <div>
        <tr><td>${timeslot.date}</td>
            <td>${timeslot.startTime}</td>
            <td><c:if test="${timeslot.assigned == false}">None</c:if>
                <c:if test="${timeslot.assigned == true}">${bookedPatient.firstName} ${bookedPatient.lastName}</c:if></td>
            <td><c:if test="${timeslot.assigned == false}">N/A</c:if>
                <c:if test="${timeslot.assigned == true}">${timeslot.description}</c:if></td>
            <td><button value="${timeslot.timeslotId}" type="submit" name="timeslotId" id="timeslotId">Delete this timeslot</button></td>
        </tr>
        </div>
    </c:forEach>
    </table>
</form>
</body>
<%@ include file="../../../common/footer.jspf" %>