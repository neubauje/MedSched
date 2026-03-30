<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<body>
<c:url value="/showOneOffice" var="showOneOfficeUrl"/>
<div id="main-content">
    <h2>Office Info: </h2>
    <div class="offices">
        <div class="office">
            <h4>Name: ${office.officeName}</h4>
            <p>Address: ${office.address}</p>
            <p>Days Opened: ${office.daysOpen}</p>
            <p>Opening Time: ${office.openingHours}</p>
            <p>Closing Time: ${office.closingHours}</p>
            <p>Office Phone Number: ${office.phoneNumber}</p>
            <p>Cost per Hour: ${office.costPerHour}0</p>
            <h2>Our doctors:</h2>
            <c:forEach items="${officeDoctors}" var="officeDoctor">
                <div class="tile">Dr. ${officeDoctor.firstName} ${officeDoctor.lastName}, ${officeDoctor.specialty}</div>
                <br>
            </c:forEach>
            <h2>Reviews from our patients:</h2>
            <c:if test="${reviews.size() < 1}"><p>No reviews on this office yet.</p></c:if>
            <c:if test="${reviews.size() > 0}"><c:forEach items="${reviews}" var="review">
                <div class="rating">
                    <c:forEach var="star" begin="1" end="${review.rating}">
                        <span class="${star <= review.rating ? 'filled' : ''}">&#9734;</span>
                    </c:forEach>
                    <p>"${review.message}"</p>
                    <c:forEach items="${users}" var="user">
                        <c:if test="${user.id == review.userId}">
                            <c:set var="reviewCreator" value="${user}"/>
                        </c:if>
                    </c:forEach>
                    <p>By ${reviewCreator.username} on ${review.dateSubmitted}</p>
                    <c:if test="${review.doctorResponse != null}">
                        <c:forEach items="${doctors}" var="checkDoctor">
                            <c:if test="${checkDoctor.doctorId == review.doctorId}">
                                <c:set var="responseCreator" value="${checkDoctor}"/>
                            </c:if>
                        </c:forEach>
                        <p>Response from Dr. ${responseCreator.firstName} ${responseCreator.lastName}:</p>
                        <p>"${review.doctorResponse}"</p>
                    </c:if>
                </div>
                <br>
            </c:forEach></c:if>
        </div>
    </div>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>