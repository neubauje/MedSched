<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="All Offices"/>
<c:url value="/showAllOffices" var="showAllOfficesUrl"/>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<body>
<div id="main-content">
    <h2>All Our Offices (sorted by cost): </h2>
    <div class="offices">
        <c:forEach var="office" items="${offices}">
            <c:set var="reviewed" value='false'/>
            <c:forEach items="${reviews}" var="review">
                <c:if test="${review.officeId == office.officeId}">
                    <c:set var="reviewed" value='true'/>
                </c:if>
            </c:forEach>
            <div class="tile">
                <h4>Name: ${office.officeName}</h4>
                <p>Address: ${office.address}</p>
                <p>Cost per Hour: ${office.costPerHour}0 dollars</p>
<%--                <c:if test="${reviewed == true}">--%><p>Average rating: ${office.averageRating} / 10</p><%--  </c:if>--%>
<%--                <c:if test="${reviewed == false}"><p>No reviews on this office yet</p></c:if>--%>
                <c:url value="/showAllOffices" var="officeDetailsURL"/>
                <form action="${officeDetailsURL}" method="POST">
                    <button value="${office.officeId}" type="submit" name="officeId" id="officeId">See more info
                    </button>
                </form>
            </div>
            <br>
        </c:forEach>
    </div>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>
