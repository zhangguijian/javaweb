<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/3 0003
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<template:user_backend htmlTitle="添加新票据" bodyTitle="添加新票据">
    <c:choose>
        <c:when test="${picture eq null}">
            <form action="/user/img">
                <input type="file" name="file">
                <input type="submit">
            </form>
        </c:when>
        <c:otherwise><img src="${cx}/user/lookimg/${picture}">
        </c:otherwise>
    </c:choose>
</template:user_backend>