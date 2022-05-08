
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.message != null}">
    <div class="modal show" id="message">
        <div class="modal__dialog">
            <div class="modal__content">
                <div>
                    <div data-messageClose class="modal__close">&times;</div>
                    <div class="flex-ja-center">
                            <div>
                                <p class="rubik-30-500"><c:out value="${message}"/></p>
                                <div class="i-container">
                                    <div class="i-i">
                                        <img class="i-image" src="images/smile.jpg" alt="">
                                    </div>
                                </div>
                            </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <style>
        <%@include file="/styles/common/message.css" %>
    </style>
    <script type="text/javascript">
        <%@include file="/js/messageModal.js" %>
    </script>
    <% session.removeAttribute("message");%>
</c:if>