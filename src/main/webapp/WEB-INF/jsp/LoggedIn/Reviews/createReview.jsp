<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/header.jspf" %>
<%@ include file="../../common/toolbar.jspf" %>

<body>
<h2>Please Submit Your Review</h2>

<c:url var="medReviewUrl" value="/createReview"/>
<p>Now leaving a review for: ${office.officeName} (${office.address})</p>
<p>Your name will display as: ${username} <br> <label for="message">Write your review here</label></p>
<form action="${medReviewUrl}" method="Post">

    <div class="form-action">
<%--       <label for="message">Write your review here</label>--%>
        <textarea name="message" id="message" rows="5" cols="33"></textarea>
    </div>
    <div class="form-action">
        <label for="rating">Rate this office</label>
        <input type="number" min="0" max="10" name="rating" id="rating">
    </div>
    <div>
        <input type="hidden" name="officeId" id="officeId" value="${office.officeId}">
        <input class="formSubmitButton" type="submit">
    </div>

</form>
</body>

<%@ include file="../../common/footer.jspf" %>