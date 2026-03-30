<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<body>
<div class="container">
    <div class="row">
        <h2>My doctor's offices</h2>
        <h4>Select an office to leave a review:</h4>
    </div>
    <c:forEach items="${offices}" var="office">
        <div class="tile">
            <p>${office.officeName}</p>
            <p>Address: ${office.address}</p>
            <form action="${pageContext.request.contextPath}/myOffices" method="post">
                <button value="${office.officeId}" type="submit" name="officeId" id="officeId">Leave a review on this office</button>
            </form>
        </div>
        <br/>
    </c:forEach>
</div>
</body>
<%@ include file="../../../common/footer.jspf" %>
