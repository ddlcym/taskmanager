<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>应用市场后台管理系统</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/uniform.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/maruti-style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/maruti-media.css" class="skin-color"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/module.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/baseattribute.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/maruti.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwr/engine.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwr/util.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/dwr/interface/SystemDWRHandler.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.8.16.custom.min.js"
            type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui/jquery-ui-1.8.22.custom.css"
          type="text/css"/>


</head>

<body>

<%--开头菜单部分***********************************************************--%>

<jsp:include page="/WEB-INF/jsp/backend/common/header.jsp"/>

<%--内容部分***********************************************************--%>
<div>
     <jsp:include page="/WEB-INF/jsp/backend/common/organization.jsp"/>
    <div id="maincontent">
        <div id="add">
            <button class="button">添加部门</button>
            <button class="button">添加用户</button>

            <table class="table table-bordered position-tab">
                <thead >
                <tr>
                    <th class="no" >序号</th>
                    <th class="org" >部门/用户</th>
                    <th class="position" >职位</th>
                    <th class="email" >email</th>
                    <th class="address" >地址</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

</body>
</html>