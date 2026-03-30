<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>

<body>
<div class="container">
    <div class="row">
        <h2>My Alerts</h2>
    </div>
<c:if test="${newAlerts.size() > 0}"><h4>Unread:</h4>
    <c:forEach items="${newAlerts}" var="newAlert">
            <div class="tile">
                <p>${newAlert.contents}</p>
                <form method="post" action="${pageContext.request.contextPath}/patientAlerts">
                    <button value="${newAlert.notifId}" type="submit" name="notifId" id="notifId">Acknowledge</button>
                </form>
        </div>
        <br>
    </c:forEach></c:if>

<c:if test="${oldAlerts.size() > 0}"><h4>Acknowledged:</h4>
    <c:forEach items="${oldAlerts}" var="oldAlert">
        <div class="tile">
            <p>${oldAlert.contents}</p>
        </div>
        <br>
    </c:forEach></c:if>

    <c:if test="${oldAlerts.size() < 1 && newAlerts.size() < 1}">
        <h4>No alerts to show.</h4>
    </c:if>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>
