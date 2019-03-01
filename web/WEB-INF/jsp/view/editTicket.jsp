<template:user_backend htmlTitle="编辑票据" bodyTitle="编辑票据">
    <form method="POST" action="${cx}/tickets/update/"
          enctype="multipart/form-data">
        <input type="hidden" name="ticketId" value="${ticket.ticketId}"/>
        创建人<br/>
        <input type="text" name="subject"
               disabled value="${ticket.userId}"><br/><br/>
        票据主题<br/>
        总金额<br/>
        <input type="text" name="money" value="${ticket.money}"><br/><br/>
        类型<br/>

        <select  name="type">
            <option value="银行汇票" ${ticket.type eq "银行汇票" ? "selected" : ""}>银行汇票
            </option>
            <option value="商业汇票" ${ticket.type eq "商业汇票" ? "selected" : ""}>
                商业汇票</option>
            <option value="商业本票" ${ticket.type eq "商业本票" ? "selected" : ""}>商业本票</option>
            <option value="银行本票" ${ticket.type eq "银行本票" ? "selected" : ""}>银行本票</option>
            <option value="转帐支票" ${ticket.type eq "转帐支票" ? "selected" : ""}>转帐支票</option>
        </select>
        <br/><br/>

        <input type="text" name="subject" value="${ticket.subject}"><br/><br/>
        票据内容<br/>
        <textarea name="body" rows="5" cols="30">${ticket.body}
            </textarea><br/><br/>
        创建时间:<br/>
        <fmt:formatDate value="${ticket.dateCreated}"
                        pattern="yyyy年MM月dd日 E hh时mm分ss秒"/>
        <br/><br/>

        <input type="submit" value="提交"/>
    </form>
    <script>
        //设置页面对应的菜单选项
        var ItemId ="Item1_0";
    </script>
</template:user_backend>
