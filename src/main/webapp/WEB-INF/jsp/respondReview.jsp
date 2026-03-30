<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../common/header.jspf" %>
<%@ include file="../../common/toolbar.jspf" %>


<body>
<h2>Respond to this review:</h2>
<p>${review.message}</p>
<p>Reviewed on ${review.dateSubmitted} <br> <br> <label for="doctorResponse">Respond To Patient Message</label></p>
<c:url var="respondReviewUrl" value="/respondReview"/>
<form action="${respondReviewUrl}" method="post">
    <div class="text-box">
<%--        <label for="doctorResponse">Respond To Patient Message</label>--%>
        <textarea id="doctorResponse" name="doctorResponse" rows="5" cols="33"></textarea>
    </div>
    <div>
        <input class="formSubmitButton" type="submit">
    </div>
</form>
</body>

<%@ include file="../../common/footer.jspf" %>