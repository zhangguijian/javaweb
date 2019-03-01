<template:user_backend htmlTitle="添加新票据" bodyTitle="添加新票据">
    <form method="POST" action="${cx}/tickets/create"
          enctype="multipart/form-data">
        票据主题<br/>
        <input type="text" name="subject" value="2018年设备采购"><br/><br/>
        总金额<br/>
        <input type="text" name="money" value="20000"><br/><br/>
        类型<br/>
        <select  name="type">
                <option value="银行汇票">银行汇票</option>
                <option value="商业汇票">商业汇票</option>
                <option value="商业本票">商业本票</option>
                <option value="银行本票">银行本票</option>
                <option value="转帐支票">转帐支票</option>
        </select>
        <br/><br/>
        票据内容<br/>
        <textarea name="body" rows="5" cols="30">购买办公用品、服务器1批，共10000元...
            </textarea><br/><br/>

        <label class="control-label">文件上传</label>
        <input  id="file-select" type="file" name="file"
               class="form-control file" />
        <div id="errorBlock"></div>
        <input type="submit" value="提交"/>
    </form>

    <%--使用fileinput组件--%>



<script>
    //设置页面对应的菜单选项
    var ItemId ="Item1_1";
    <!-- 文件上传组件属性设置 -->
    $('#file-select').fileinput({
        language : 'zh', //中文
        uploadUrl : '${cx}/tickets/uploadAttachement', //上传路径
        'elErrorContainer' : '#errorBlock',
        showUpload : false, //是否显示上传文本框里的上传按钮
        showRemove : false, //是否显示上传文本框里的移除按钮
        dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        maxFileCount : 3, //表示允许同时上传的最大文件个数
        enctype : 'multipart/form-data',
        allowedFileTypes : [ 'image','doc' ],//配置允许文件上传的类型
        allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
        allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ]//控制被预览的所有mime类型
    });
</script>
</template:user_backend>
