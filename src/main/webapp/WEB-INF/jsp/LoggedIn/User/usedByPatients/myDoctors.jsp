<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<div class="container">
    <div class="row">
        <h2>My Doctors</h2>
        <c:if test="${doctors.size() < 1}">
            <p>You are not yet a patient of any doctors.</p>
            <a href="${pageContext.request.contextPath}/findDoctors">
                <button>Find a doctor</button>
            </a>
        </c:if>
    </div>
    <c:if test="${doctors.size() > 0}">
        <h4>Here are all the doctors you are a patient of:</h4>
        <c:forEach items="${doctors}" var="doctor">
            <c:set var="officeId" value="${doctor.officeId}"/>
            <c:forEach items="${offices}" var="officeSearch">
                <c:if test="${officeSearch.officeId == officeId}">
                    <c:set var="office" value="${officeSearch}"/>
                </c:if>
            </c:forEach>
            <div class="tile">
                <p>Dr. ${doctor.firstName} ${doctor.lastName}</p>
                <p>Practice: ${office.officeName}</p>
                <p>Address: ${office.address}</p>
                <p>Specialty: ${doctor.specialty}</p>
                <span>
            <form action="${pageContext.request.contextPath}/myDoctors" method="POST">
                <button value="${doctor.doctorId}" type="submit" name="doctorId" id="doctorId">Book an appointment with this doctor</button>
            </form>
            <form action="${pageContext.request.contextPath}/findDoctors" method="post">
                <button type="submit" value="${doctor.doctorId}" name="doctorId">Drop this doctor</button>
            </form>

                </span>
            </div>
            <br/>
        </c:forEach>
    </c:if>
</div>
<%@ include file="../../../common/footer.jspf" %>
