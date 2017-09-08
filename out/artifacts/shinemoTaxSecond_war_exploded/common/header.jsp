<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<script type="text/javascript" src="{basePath }js/jquery/jquery-1.10.2.min.js"></script>
<%--引入css,href使用java直接获取--%>
<link href="<%=request.getContextPath()%>/css/skin1.css" type="text/css" rel="stylesheet">


