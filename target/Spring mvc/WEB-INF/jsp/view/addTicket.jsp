<template:user_backend htmlTitle="添加新票据" bodyTitle="添加新票据">
    <%--<div class="col-sm-8">--%>
    <%--<form method="POST" action="${cx}/tickets/create"--%>
    <%--enctype="multipart/form-data">--%>
    <%--票据主题<br/>--%>
    <%--<input type="text" name="subject" value="2018年设备采购"><br/><br/>--%>

    <%--<div class="form-group">--%>
    <%--<div class="col-sm-4 label-column">--%>
    <%--<label   class="control-label">Password </label>--%>
    <%--</div>--%>
    <%--<div class="col-sm-6 input-column">--%>
    <%--<input type="password" class="form-control" />--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--总金额<br/>--%>
    <%--<input type="text" name="money" value="20000"><br/><br/>--%>
    <%--类型<br/>--%>
    <%--<select  name="type">--%>
    <%--<option value="银行汇票">银行汇票</option>--%>
    <%--<option value="商业汇票">商业汇票</option>--%>
    <%--<option value="商业本票">商业本票</option>--%>
    <%--<option value="银行本票">银行本票</option>--%>
    <%--<option value="转帐支票">转帐支票</option>--%>
    <%--</select>--%>
    <%--<br/><br/>--%>
    <%--票据内容2<br/>--%>
    <%--<textarea name="body" rows="5" cols="30">购买办公用品、服务器1批，共10000元...--%>
    <%--</textarea><br/><br/>--%>
    <%--<div id="file_upload"><b>附件2</b><br/>--%>
    <%--<input type="file" name="file1" multiple="multiple"/>--%>
    <%--</div>--%>
    <%--<button onclick="addFile()" value=""> 添加文件</button>--%>
    <%--<br/> <br/>--%>
    <%--<input type="submit" value="提交"/>--%>
    <%--</form>--%>
    <%--</div>--%>
    <div class="col-md-11">
        <div class="panel panel-success">
            <div class="panel-heading"
                 style="background-color:#0b7285;color: white">
                <span class="glyphicon glyphicon-book"></span> 添加票据
            </div>
            <div class="panel-body">
                <form action="${cx}/tickets/create"  method="POST" enctype="multipart/form-data">
                    <fieldset>
                        <div class="form-group ">
                            <label class="control-label">票据主题</label>
                            <input type="text" style="width: 340px;"
                                   class="form-control" placeholder="票据主题"
                                   value="2018年设备采购"
                                   name="subject"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">总金额</label>
                            <input type="text" style="width: 240px;"
                                   class="form-control" placeholder="输入总金额"
                                   value="20000" name="money"/>
                        </div>

                        <div class="form-group ">
                            <label class="control-label">票据类型</label>
                            <select class="form-control" name="type"
                                    style="width: 240px;">
                                <option value="银行汇票">银行汇票</option>
                                <option value="商业汇票">商业汇票</option>
                                <option value="商业本票">商业本票</option>
                                <option value="银行本票">银行本票</option>
                                <option value="转帐支票">转帐支票</option>
                            </select>
                        </div>
                        <div class="form-group ">
                            <label class="control-label">票据主要内容</label>
                            <textarea  class="form-control" name="body" rows="6" cols="28">
                                购买办公用品、服务器1批，共10000元...
                        </textarea>
                        </div>

                        <div class="form-group">
                            <div class="fileUpload">
                            <label class="control-label">附件</label>
                            <input type="file" name="file" value=""  multiple="multiple"/>
                            </div>
                            <button id="addFile" type="button" class="btn btn-success" > 继续添加</button>
                        </div>
                            <p class="col-lg-4 col-lg-offset-2">
                                <button class="btn btn-default">重置</button>
                                <button class="btn"  style="background-color:#0b7285;color: white">添加票据</button>
                        </p>
                    </fieldset>
                </form>
            </div>

        </div>
    </div>
    <script>
        //设置页面对应的菜单选项
        var ItemId = "Item1_1";

        $(document).ready(function () {
            //添加文件控件
            $("#addFile").click(function () {
                $(".fileUpload:last").clone().insertBefore($("#addFile"));
                return false;
            });
        });

    </script>
</template:user_backend>
