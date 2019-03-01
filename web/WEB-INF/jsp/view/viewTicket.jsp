<template:user_backend htmlTitle="${ticket.subject}"
                       bodyTitle="票据详情">
    <i>Ticket #${ticket.ticketId}: ${ticket.subject}</i><br/><br/>
    <i><strong>客户姓名:</strong> ${ticket.user.username}</i>
    <p>
        <strong>创建时间:</strong> <fmt:formatDate value="${ticket.dateCreated}"
                               pattern="yyyy年MM月dd日 E hh时mm分ss秒"/>

    <p>
    <p><strong>票据种类:</strong>  ${ticket.type}
    <p><strong>状态:</strong>  <c:if test="${ticket.status eq 0}">未审核</c:if>
    <c:if test="${ticket.status eq 1}">审核通过</c:if>
    <p>
    <p><strong>总金额:</strong>  ${ticket.money}<p>

    <b><strong>内容:</strong> ${ticket.body}<br/><br/>
    <c:if test="${ticket.attachments.size() > 0}">
        <c:forEach items="${ticket.attachments}" varStatus="v"
                   var="attachment">
            <li>
                附件${v.count}:
                文件名: ${attachment.fileName} <br>
                下载地址:<a href="${cx}${attachment.fileUrl}">下载</a><br>
                附件类型:${attachment.mimeType}<br>
            </li>
        </c:forEach>
    </c:if>
    <a href="${cx}/tickets/">返回票据列表 </a>

    <script>
        //设置页面对应的菜单选项
        var ItemId ="Item1_0";
    </script>
</template:user_backend>
