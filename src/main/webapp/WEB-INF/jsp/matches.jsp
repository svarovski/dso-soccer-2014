<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<c:if test="${not empty param['locale']}">
    <fmt:setLocale value="${fn:toLowerCase(param['locale'])}" scope="session"/>
</c:if>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="The Settlers Online soccer event 2014 adventure helper">
    <meta name="author" content="GS">

    <title>DSO Soccer 2014</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/app.css" />

</head>
<body>
    <div class="container">
        <%--Match list --%>
        <div class="row margin-top-20">
            <div class="col-xs-12">
                <c:forEach items="${it.matches}" var="match">
                    <c:choose>
                        <c:when test="${not empty match.enemies}">
                            <a href="${it.root}/${match}">
                                <button class="btn btn-info">
                                    <fmt:message key="name.${match}" />
                                </button>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default disabled">
                                <fmt:message key="name.${match}" />
                            </button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>

        <%--Selected match --%>
        <c:if test="${not empty it.match}">
            <div class="row margin-top-20">
                <div class="col-xs-12">
                    <h4><fmt:message key="name.${it.match}" /></h4>
                </div>
            </div>
        </c:if>

        <%--Locale switcher --%>
        <div class="margin-top-20">
            <fmt:message key="locale.list" var="locale_list"/>
            <fmt:message key="locale.code" var="locale_code"/>
            <a href="<c:url value="${it.root}"/>"><fmt:message key="message.home"/></a>
            <c:forEach items="${fn:split(locale_list,',')}" var="locale">
                |
                <c:choose>
                    <c:when test="${locale == locale_code}">${locale}</c:when>
                    <c:otherwise>
                        <a href="<c:url value="${it.root}?locale=${locale}"/>">${locale}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>

    <%--JQUERY --%>
    <script src="/static/jquery-1.11.1.min.js"></script>
</body>
</html>
