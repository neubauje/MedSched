<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>


<body>
<c:if test="${bookedTimeSlots.size() < 1}">
    <p>You have no appointments scheduled.</p>
    <a href="${pageContext.request.contextPath}/myDoctors">
        <button>Choose a doctor to make an appointment with</button>
    </a>
</c:if>
<c:if test="${bookedTimeSlots.size() > 0}">
<c:url value="myAppointments" var="myAppointmentURL"/>
<form action="${myAppointmentURL}" method="POST">
    <h2>Your Booked Time Slots</h2>

    <table class="table">
        <tr>
            <th>Date</th>
            <th>Doctor</th>
            <th>Start Time</th>
            <th>End Time</th>
                <th>Reason For Visit</th>
                <th>Cancel?</th>
            </tr>
            <c:forEach items="${bookedTimeSlots}" var="timeslot">
                <c:forEach items="${doctors}" var="doctor">
                    <c:if test="${timeslot.doctorId == doctor.doctorId}">
                        <c:set var="bookedDoctor" value="${doctor}"/>
                    </c:if>
                </c:forEach>
                <div>
                <tr>
                    <td>${timeslot.date}</td>
                    <td>${bookedDoctor.firstName} ${bookedDoctor.lastName}</td>
                    <td>${timeslot.startTime}</td>
                    <td>${timeslot.endTime}</td>
                    <td>${timeslot.description}</td>
                    <td>
                        <button value="${timeslot.timeslotId}" type="submit" name="timeslotId" id="timeslotId">Cancel
                            Appointment
                        </button>
                    </td>
                </tr>
                </div>
            </c:forEach>
        </table>
    </c:if>
</form>
</body>
<%@ include file="../../../common/footer.jspf" %>