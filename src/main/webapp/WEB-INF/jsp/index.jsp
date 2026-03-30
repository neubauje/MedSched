<%@ include file = "common/header.jspf" %>
<c:if test="${not empty user}"><%@ include file="common/toolbar.jspf" %></c:if>

<h2>Welcome to the site!</h2>
<img src="${pageContext.request.contextPath}/img/hospitalImg.jpg" alt="Hospital Image"/>
<section class="designBy">
    <span><h4>Designed By</h4></span>
    <p><a href="https://www.linkedin.com/in/neubauje/" target="_blank">Jesse Neubauer</a></p>
    <p><a href="https://www.linkedin.com/in/ndeye-sene-764712237/" target="_blank">Ndeye "Maty" Sene</a></p>
    <p><a href="https://www.linkedin.com/in/francis-nyamekye-junior-649680217/" target="_blank">Nyamekye "Jr" Francis</a></p>
    <p><a href="https://www.linkedin.com/in/phajindra/" target="_blank">Phajindra Bajgain</a></p>
    <p>Special mention: <a href="https://www.linkedin.com/in/destane-lozada-195990153" target="_blank">Destane Lozada</a></p>
</section>

<%@ include file = "common/footer.jspf" %>