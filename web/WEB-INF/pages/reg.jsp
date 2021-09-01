<%--
  Created by IntelliJ IDEA.
  User: TJYSB
  Date: 2021/8/31
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="/res/layui-v2.5.6/layui/css/layui.css">
    <script src="/res/layui-v2.5.6/layui/layui.js"></script>
</head>
<body>
 <%--<button class="layui-btn layui-btn-danger">测试静态资源是否能够访问</button>--%>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>注册</legend>
    </fieldset>
    <form class="layui-form" action="" onsubmit="return false">
        <div class="layui-form-item">
         <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="adminName"  class="layui-input">
            </div>
        </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="adminPwd"  class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">重复密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="adminPwdR"  class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">入职时间</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="adminTime" name="adminTime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn" lay-submit="" lay-filter="regBtn">立即提交</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form','layer','jquery','laydate'],function () {
        var form=layui.form;
        var layer=layui.layer;
        var $=layui.jquery;
        var laydate=layui.laydate;

        //日期时间选择器
        laydate.render({
            elem: '#adminTime'
            ,type: 'datetime'
        });
        //监听提交按钮
        form.on('submit(regBtn)',function (data) {
           // layer.msg(JSON.stringify(data.field))
            $.ajax({
                url:'/api/admin/regByBean',
                type:'POST',
                data:data.field,
                dataType:'json',
                success:function (res) {
                    console.log(res)
                    if(res.code==0){
                        window.location.href="/pages/login"
                    }
                    if(res.code==4401) {
                        layer.msg("你输入的密码和重复密码不一致，注册失败")
                    }
                    if(res.code==4202) {
                        layer.msg("填写完整表单")
                    }

                }

            })
        })

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
        });

    })
</script>


</body>
</html>
