<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/common/toolbar.jspf" %>

<body>
<c:url value="/createSlots" var="createURL"/>
<form action="${createURL}" method="POST">
    <h2>Populate your schedule - Create a new timeslot</h2>
    <br>
    <div>
        <label class="createSlotlabel" for="date">Choose a Date</label>
        <input name="date" type="date" id="date"/>
    </div>
    <div>
        <label class="createSlotlabel" for="startTime">Starting time</label>
        <input name="startTime" type="time" id="startTime"/>
    </div>
    <div>
        <label class="createSlotlabel" for="endTime">Ending time</label>
        <input name="endTime" type="time" id="endTime"/>
    </div>
    <button type="submit" name="submit" id="submit">Create This Timeslot</button>
</form>
</body>
<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>