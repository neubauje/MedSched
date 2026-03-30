<%@ include file="../../common/header.jspf" %>
<%@ include file="../../common/toolbar.jspf" %>
<div class="container">
    <div class="row">
        <h2>My profile</h2>
        <h4>You are logged in as: ${user.capitalizedUsername}</h4>
    </div>
    <c:if test="${user.role == 'Doctor'}">
        <div class="doctor-info">
            <p>First Name: ${doctor.firstName}</p>
            <p>Last Name: ${doctor.lastName}</p>
            <p>Office/Practice: ${office.officeName} (${office.address})</p>
            <p>Specialty: ${doctor.specialty}</p>
            <a href="${pageContext.request.contextPath}/editDoctor">
                <button>Update my information</button>
            </a>
            <br>
            <c:if test="${alert == true}"><p class="alert"><--- YOU HAVE NEW NOTIFICATIONS TO READ</p></c:if>
        </div>
    </c:if>
    <c:if test="${user.role == 'Patient'}">
        <div class="patient-info">
            <p>First Name: ${patient.firstName}</p>
            <p>Last Name: ${patient.lastName}</p>
            <p>Date of Birth: ${patient.dateOfBirth}</p>
            <p>Phone number: ${patient.phoneNumber}</p>
            <p>Email address: ${patient.email}</p>
            <p>Emergency Contact name: ${patient.emergencyName}</p>
            <p>Emergency Contact phone number: ${patient.emergencyContact}</p>
            <p>Insurance provider: ${patient.insurance}</p>
             <a href="${pageContext.request.contextPath}/editPatient">
                <button>Update my information</button>
            </a>
            <br>
            <c:if test="${alert == true}"><p class="alert"><--- YOU HAVE NEW NOTIFICATIONS TO READ</p></c:if>
        </div>
    </c:if>
</div>
<%@ include file="../../common/footer.jspf" %>
