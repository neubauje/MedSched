<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="One Office"/>

<%@ include file="../../../common/header.jspf" %>
<%@ include file="../../../common/toolbar.jspf" %>
<body>
<div id="main-content">
    <h2>This office</h2>

    <c:url var="formAction" value="/showOneOffice"/>

        <form method="GET" action="${formAction}">
            <div class="tile">
                <label for="officeName">Officename</label>
                <input type="text" name="officeName" id="officeName"/>
            </div>
            <input class="formSubmitButton" type="submit" value="Submit"/>
        </form>

</div>
</body>
<%@ include file="../../../common/footer.jspf" %>
