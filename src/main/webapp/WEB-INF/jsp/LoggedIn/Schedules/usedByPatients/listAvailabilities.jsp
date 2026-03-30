<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>


<body>
<h2>Available TimeSlots</h2>
<c:if test="${timeSlots.size() < 1}">
    <h4>None available on this date.</h4>
    <a href="${pageContext.request.contextPath}/selectDate"><button>Go back to pick a different date</button></a>
</c:if>
<c:if test="${timeSlots.size() > 0}">
<c:url value="/listAvailabilities" var="listAvailabilitiesURL"/>
<table class="table">
    <tr>
        <th>Date</th>
        <th>Doctor Name</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Book?</th>
    </tr>
    <c:forEach items="${timeSlots}" var="timeslot">
        <div>
        <form action="${listAvailabilitiesURL}" method="POST">
            <tr>
                <td><c:out value="${timeslot.date}"/></td>
                <td><c:out value="${doctor.firstName} ${doctor.lastName}"/></td>
                <td><c:out value="${timeslot.startTime}"/></td>
                <td><c:out value="${timeslot.endTime}"/></td>
                <td>
                    <button value="${timeslot.timeslotId}" type="submit" name="timeslotId" id="timeslotId">Select Slot
                    </button>
                </td>
            </tr>
        </form>
        </div>
    </c:forEach>
</table>
</c:if>
</body>
<%@ include file="../../../common/footer.jspf" %>