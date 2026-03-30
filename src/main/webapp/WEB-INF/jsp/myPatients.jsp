<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<body>
<div class="container">
    <div class="row">
        <h2>My Patients</h2>
    </div>
    <c:if test="${patients.size() < 1}">
        <p>You do not currently have any patients.</p>
    </c:if>
    <c:if test="${patients.size() > 0}">
        <h4>Here are all the patients who have claimed you as their doctor:</h4>
        <c:forEach items="${patients}" var="patient">
            <div class="tile">
                <p>${patient.firstName} ${patient.lastName}</p>
                <p>Phone number: ${patient.phoneNumber}</p>
                <c:url var="patientURL" value="/myPatients"/>
                <form action="${patientURL}" method="POST">
                    <button value="${patient.patientId}" type="submit" name="patientId" id="patientId">See full patient info</button>
                </form>
            </div>
            <br>
        </c:forEach></c:if>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>
