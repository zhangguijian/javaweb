<%--普通用户的后台模板,添加了左侧导航栏--%>
<%--定义页面的编码类型--%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%--定义当前模板需要传入的变量--%>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="extraHeadContent" fragment="true" required="false" %>
<%--定义扩展使用的传入变量--%>
<%@ attribute name="extraNavigationContent" fragment="true" required="false" %>
<%@ include file="/WEB-INF/jsp/view/base.jspf" %>
<!--template:main表示使用main的模板-->
<template:basic_backend htmlTitle="${htmlTitle}" bodyTitle="${bodyTitle}">
  <jsp:attribute name="headContent">
        <jsp:invoke fragment="extraHeadContent"/>
  </jsp:attribute>

    <jsp:attribute name="sideBar">
        <aside id="sidebar" class="sidebar-nav col-sm-2">
            <nav class="sidebar-nav">
                <ul class="metismenu" id="menu">
                    <li>
                        <a href="dashboard" id="Item0.0">
                            <span class="fa fa-fw fa-github fa-lg"></span>
                            主页
                        </a>

                    </li>
                    <li class="">
                        <a class="has-arrow" id="Item1_0"><span class="fa fa-fw fa-book fa-lg"></span>票据管理</a>
                        <ul class="mm-collapse">
                            <li><a href="${cx}/tickets/create" id="Item1_1">
                                添加票据</a></li>
                            <li><a href="${cx}/tickets/list"
                                   id="Item1_2">查看票据
                            </a></li>
                            <li><a href="${cx}/tickets/comment" id="Item1_3">
                                票据评论</a></li>
                            <li><a href="44#" id="Item1_4">已提交票据</a></li>
                            <a href="6#" id="Item1_5">已审核票据</a>
                        </ul>
                    </li>
                    <li>
                        <a class="has-arrow" href="2#" id="Item2_0">
                            <span class="fa fa-fw fa-user-circle fa-lg"></span>个人中心</a>
                        <ul class="mm-collapse">
                            <li><a href="${cx}/user/yonghu" aria-expanded="false"
                                   id="Item2_1">个人信息</a></li>
                            <li><a href="5#" aria-expanded="false"
                                   id="Item2_2">修改密码</a></li>
                            <li><a href="5#" aria-expanded="false"
                                   id="Item2_3">登陆记录</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </aside>
        <!--该属性暂时没用-->
        <jsp:invoke fragment="extraNavigationContent"/>
    </jsp:attribute>

    <jsp:body>
        <!--页面主体 -->
        <jsp:doBody/>
    </jsp:body>


</template:basic_backend>
