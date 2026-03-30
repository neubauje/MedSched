<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="My Office"/>s
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/common/toolbar.jspf" %>
<c:url var="cssUrl" value="/css/site.css"/>
<link rel="stylesheet" href="${cssUrl}"/>
<body>
    <h2>My Office:</h2>

    <c:if test="${office.officeId == 0}">
        <p>You selected the placeholder office.</p>
        <a href="${pageContext.request.contextPath}/addNewOffice">
            <button>Create your new office/practice</button>
        </a>
    </c:if>

    <c:if test="${office.officeId != 0}"><div class="offices">
        <div class="office">
            <h4>Name: ${office.officeName}</h4>
            <p>Address: ${office.address}</p>
            <p>Days Opened: ${office.daysOpen}</p>
            <p><time>Opening Time: ${office.openingHours}</time></p>
            <p><time>Closing Time: ${office.closingHours}</time></p>
            <p>Office Phone Number: ${office.phoneNumber}</p>
            <p>Cost per Hour: ${office.costPerHour}0</p>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/editOffice">
                <button>Update this office's details</button>
            </a>
        </div>
    </div></c:if>
</body>
<c:import url="/WEB-INF/jsp/common/footer.jspf"/>