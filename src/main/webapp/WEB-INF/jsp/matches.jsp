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
        <%-- Match list --%>
        <div class="row margin-top-20">
            <div class="col-xs-12">
                <c:forEach items="${it.matches}" var="match">
                    <c:choose>
                        <c:when test="${not empty match.enemies}">
                            <a href="${it.root}/${match}"><button class="btn btn-info">
                                <fmt:message key="name.${match}" />
                            </button></a>
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

        <%-- Selected match --%>
        <c:if test="${not empty it.match}">
            <%-- Match title --%>
            <div class="row margin-top-20">
                <div class="col-xs-12">
                    <h4><fmt:message key="name.${it.match}" /></h4>
                    <div>
                        <fmt:message key="message.enemies" />:
                        <c:forEach items="${it.match.enemies}" var="enemy" varStatus="status">
                            <c:if test="${not status.first}">, </c:if>
                            <fmt:message key="name.${enemy.key}" />(${enemy.value})
                        </c:forEach>
                    </div>
                    <div>
                        <fmt:message key="message.resources" />:
                        <c:forEach items="${it.condition}" var="resource" varStatus="status">
                            <c:if test="${not status.first}">, </c:if>
                            <fmt:message key="name.${resource.key}" />(${resource.value})
                        </c:forEach>
                    </div>
                </div>
            </div>

            <%-- Resource form --%>
            <c:if test="${not empty it.resources}">
                <div class="row margin-top-20">
                    <div class="col-xs-12">
                        <form role="form" method="POST" action="${it.root}/${it.match}">
                            <c:forEach items="${it.resources}" var="resource" varStatus="status">
                                <div class="form-group col-xs-2">
                                    <label for="res_${status.count}"><fmt:message key="name.${resource}"/>:</label>
                                </div>
                                <div class="form-group col-xs-1">
                                    <input class="form-control resource-input" id="res_${status.count}" name="${resource}">
                                </div>
                            </c:forEach>
                            <div class="form-group col-xs-3">
                                <button type="submit" class="btn btn-info btn-block">
                                    <fmt:message key="message.solve"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>

            <%-- Match solution --%>
            <c:if test="${not empty it.solution}">
                <div class="row margin-top-20">
                    <%-- Solution --%>
                    <div class="col-md-4 col-xs-1">
                        <table>
                            <tr>
                                <th colspan=2><fmt:message key="message.solution"/></th>
                            </tr>
                            <tr>
                                <th><fmt:message key="message.enemy"/></th>
                                <th><fmt:message key="message.weakness"/></th>
                            </tr>
                            <c:forEach items="${it.solution.hits}" var="hit">
                                <tr>
                                    <td><fmt:message key="name.${hit.enemy}"/></td>
                                    <td><fmt:message key="name.${hit.weakness}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <%-- Weakness stats --%>
                    <div class="col-md-4 col-xs-1">
                        <table>
                            <tr>
                                <th colspan=2><fmt:message key="message.weakness_stats"/></th>
                            </tr>
                            <tr>
                                <th><fmt:message key="message.weakness"/></th>
                                <th><fmt:message key="message.amount"/></th>
                            </tr>
                            <c:forEach items="${it.solution.weaknessUsage}" var="weakness">
                                <tr>
                                    <td><fmt:message key="name.${weakness.key}"/></td>
                                    <td><span class="pull-right">${weakness.value}</span></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <%-- Resource stats --%>
                    <div class="col-md-4 col-xs-1">
                        <table>
                            <tr>
                                <th colspan=3><fmt:message key="message.resource_stats"/></th>
                            </tr>
                            <tr>
                                <th><fmt:message key="message.resource"/></th>
                                <th><fmt:message key="message.amount"/></th>
                                <th><fmt:message key="message.remainder"/></th>
                            </tr>
                            <c:forEach items="${it.solution.resourceUsage}" var="resource">
                                <tr>
                                    <td><fmt:message key="name.${resource.key}"/></td>
                                    <td><span class="pull-right">${resource.value}</span></td>
                                    <td><span class="pull-right">${it.solution.resourceRemainder[resource.key]}</span></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                </div>
            </c:if>
        </c:if>

        <%-- Error box --%>
        <c:if test="${not empty it.error}">
            <div class="row margin-top-20">
                <div class="col-xs-10 col-xs-offset-1 alert-danger">
                    <h4><fmt:message key="${error}"/></h4>
                </div>
            </div>
        </c:if>

        <%-- Locale switcher --%>
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

    <%-- JQUERY --%>
    <script src="/static/jquery-1.11.1.min.js"></script>
</body>
</html>
