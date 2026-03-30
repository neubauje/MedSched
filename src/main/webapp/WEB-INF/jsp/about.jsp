<%@ include file="common/header.jspf" %>
<c:if test="${not empty user}"><%@ include file="common/toolbar.jspf" %></c:if>
<h2>About this application</h2>

<p>This application is a demonstration of an authentication program that we have implemented, along with database
    integration.</p>

<p>We have built an application that connects doctors and pharmacists to their patients. By allowing doctors and
    patients to connect and schedule times for appointments, patients will be
    easily able to see when their doctors are available, and book an appointment through the app rather than through a
    receptionist.</p>

<p>Until this point, there has been no transparency in scheduling beyond going off of the receptionist time
    recommendations. By opening up scheduling data, making certain doctors available for appointments, and allowing
    complete transparency of staff availability, patients will be more capable of scheduling times
    that fit their lifestyle. The state-of-the-art notification system also alerts patients and doctors alike to changes
    in their schedule.</p>

<p>Patients are also able to leave reviews on an office they have been to, and find other offices to visit. Doctors are
    now able to leave a response to each review, providing more information on the scenario.</p>
<div>
    <img src="${pageContext.request.contextPath}/img/aboutpage.png" alt="Doctor Image"/>
    <img src="${pageContext.request.contextPath}/img/hospitalImg.jpg" alt="Hospital Image"/>
</div>
<%@ include file="common/footer.jspf" %>