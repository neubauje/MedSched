<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/header.jspf" %>
<%@ include file="../../common/toolbar.jspf" %>

<body>
<h3>Reviews on my office</h3>
<c:if test="${reviews.size() < 1}">
    <p>There are no reviews on your office. Encourage your patients to leave feedback.</p>
</c:if>

<c:if test="${reviews.size() > 0}">
<c:forEach var="review" items="${reviews}">
    <div class="rating tile">
        <div>
            <c:forEach var="star" begin="1" end="${review.rating}">
                <span class="${star <= review.rating ? 'filled' : ''}">&#9734;</span>
            </c:forEach>
        </div>
        <p class="Message">"${review.message}"</p>
        <c:forEach items="${userList}" var="checkUser">
            <c:if test="${checkUser.id == review.userId}">
                <c:set var="reviewCreator" value="${checkUser}"/>
            </c:if>
        </c:forEach>
        <p>Review left by: ${reviewCreator.username} on ${review.dateSubmitted}</p>
    </div>
    <div>
        <c:if test="${review.doctorResponse == null}">
        <c:url value="/officeFeedback" var="formAction"/>
        <form method="POST" action="${formAction}">
            <input type="hidden" name="reviewId" value="${review.reviewId}"/>
            <input class="forResponse" type="submit" value="Respond to this review">
        </form>
        </c:if>
        <c:if test="${review.doctorResponse != null}">
            <c:forEach items="${doctorList}" var="checkDoctor">
                <c:if test="${checkDoctor.doctorId == review.doctorId}">
                    <c:set var="responseCreator" value="${checkDoctor}"/>
                </c:if>
            </c:forEach>
            <p>Response from ${responseCreator.firstName} ${responseCreator.lastName}:</p>
        <p>"${review.doctorResponse}"</p>
        </c:if>
        <br>
    </div>
</c:forEach>
</c:if>
</body>
<%@ include file="../../common/footer.jspf" %>

