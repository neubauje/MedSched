<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<body>
<h2>My Scheduled Time Slots</h2>
<c:if test="${timeSlots.size() < 1}">
    <p>You have no upcoming appointments</p>
</c:if>

<c:if test="${timeSlots.size() > 0}">
    <c:url value="/viewMyAgenda" var="agendaURL"/>
    <table class="table">
        <tr>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Patient Name</th>
            <th>Reason for visit</th>
            <th>Cancel appointment?</th>
        </tr>
        <c:forEach items="${timeSlots}" var="timeslot">
            <c:forEach items="${patients}" var="patient">
                <c:if test="${timeslot.patientId == patient.patientId}">
                    <c:set var="bookedPatient" value="${patient}"/>
                </c:if>
            </c:forEach>
            <div>
            <tr>
                <td><c:out value="${timeslot.date}"/></td>
                <td><c:out value="${timeslot.startTime}"/></td>
                <td><c:out value="${timeslot.endTime}"/></td>
                <td><c:out value="${bookedPatient.firstName} ${bookedPatient.lastName}"/></td>
                <td><c:out value="${timeslot.description}"/></td>
                <td>
                    <form name="cancel" action="${pageContext.request.contextPath}/viewMyAgenda" method="post">
                    <button value="${timeslot.timeslotId}" type="submit" name="timeslotId" id="timeslotId">Cancel</button></form>
                </td>
            </tr>
            </div>
        </c:forEach>
    </table>
</c:if>
</body>
<%@ include file="../../../common/footer.jspf" %>