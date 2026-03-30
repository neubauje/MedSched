<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<div class="container">
    <div class="row">
        <h2>Welcome to the Doctor search!</h2>
        <h4>Here are all the doctors in the system:</h4>
    </div>
    <c:forEach items="${doctors}" var="doctor">
        <c:set var="officeId" value="${doctor.officeId}"/>
        <c:forEach items="${offices}" var="officeSearch">
            <c:if test="${officeSearch.officeId == officeId}">
                <c:set var="office" value="${officeSearch}"/>
            </c:if>
        </c:forEach>
        <c:set var="link" value="false"/>
        <c:forEach items="${myDoctors}" var="myDoctor">
            <c:if test="${doctor.doctorId == myDoctor.doctorId}">
                <c:set var="link" value="true"/>
            </c:if>
        </c:forEach>
        <div class="tile">
            <p>Dr. ${doctor.firstName} ${doctor.lastName}</p>
            <p>Practice: ${office.officeName}</p>
            <p>Address: ${office.address}</p>
            <p>Specialty: ${doctor.specialty}</p>
            <c:if test="${link == false}">
                <form action="${pageContext.request.contextPath}/findDoctors" method="post">
                    <button value="${doctor.doctorId}" type="submit" name="doctorId" id="doctorId">Become a new patient
                        of
                        this doctor
                    </button>
                </form>
            </c:if>
            <c:if test="${link == true}">
                <button disabled>You are already a patient of this doctor</button>
                <form action="${pageContext.request.contextPath}/findDoctors" method="post">
                    <button type="submit" value="${doctor.doctorId}" name="doctorId">Drop this doctor</button>
                </form>

            </c:if>
        </div>
        <br/>
    </c:forEach>
</div>
<%@ include file="../../../common/footer.jspf" %>
