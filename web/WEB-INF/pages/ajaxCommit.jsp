<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<html>
<head>
    <title>springmvc 之 ajax和mvc收参数  非常重要</title>
</head>
<body>
 <div>
     <input type="button" value="ajax的01中传参 k-结构或者对象" id="ajax01">
     <input type="button" value="ajax的02中传参 对象" id="ajax02">
     <input type="button" value="ajax的03中传参数 传数组 或者集合" id="ajax03">
     <input type="button" value="ajax的04中传参数 传json对象" id="ajax04">
     <input type="button" value="ajax的05中传参数 传多个对象" id="ajax05">
     <input type="button" value="ajax的06中传参数 传批量对象传参" id="ajax06">
     <input type="button" value="ajax的07中传参数 传 map 传参" id="ajax07">
     <input type="button" value="ajax的08中 对象——常用类型 混合，常见于 带参数的分页查询" id="ajax08">
 </div>
<script>
    $(function () {
        $("#ajax01").click(function () {
            $.ajax({
                url:"/api/admin/regByBean",
                type:"POST",
                data:{
                    'adminName':"张三子",
                    'adminPwd':"12356",
                    'adminTime':"2019-09-08 23:09:29"
                },
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
            })
        })

        $("#ajax02").click(function () {
            var adminInfo={};
            adminInfo.adminName='李四';
            adminInfo.adminPwd='12345';
            adminInfo.adminTime="2019-09-08 23:09:29"
            $.ajax({
                url:"/api/admin/regByBean",
                type:"POST",
                data:adminInfo,
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
            })
        })
        // ajax 传数组，集合  应用的范围是 批量删除 ，比如 ids[]={3,5,7,9}
        $("#ajax03").click(function () {
            var ids=new Array();
            ids.push(3);
            ids.push(6);
            ids.push(8);
            ids.push(80);
            //把数组使用ajax传递
            $.ajax({
                url:"/api/admin/ajax03",
                type:"POST",
                data:{'ids':ids},
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
            })
        })
        // ajax 传json对象
        $("#ajax04").click(function () {
            // json 对象 浏览器中看的请求带颜色的
            var adminInfo={   // var 的对象  给对象赋值
            adminName:"小欣欣",
            adminPwd:"123435",
            adminTime:"2020-09-12 20:09:34",
            loverList:[
                    {
                        name:"大包子",
                        age:19
                    },
                    {
                        name:"小辫",
                        age:22
                    },
                    {
                        name:"黑子",
                        age:28
                    }
                ],
            aiHao:[3,34,19]
            };
            //var 出来的对象，他是个对象，如果直接传输，www.urlxxx 传递的，是黑色的
            //越是复杂的 请求， 越需要 转成json 来请求。
            // 因为是普通的 请求 是很多个 k-v 结构，后台收取 特别麻烦，所以就需要把复杂对象转换成json对象
            //然后  后台就方便多了 前后端 项目， 一般都用json传递！！！
            $.ajax({
                url:'/api/admin/ajax04',
                type:'POST',
               // data:adminInfo,   普通的 k-v 结构 请求方式是：Content-Type: application/x-www-form-urlencoded; charset=UTF-8
                data:JSON.stringify(adminInfo),   //变成json对象后，就需要 Content-Type 更改为：
                contentType:"application/json;charset=UTF-8",
                dataType:"JSON",
                success:function (rs) {
                    console.log(rs)
                }

            })



        });

        // 传多个对象
        $("#ajax05").click(function () {   // 不常见，因为  发货单，上面是收货人的对象 ，下面是产品对象
            $.ajax({
                url:"/api/admin/ajax05",
                type:"POST",
                dataType:"json",
                data:{
                    'lover.name':'白白',
                    'lover.age':20,
                    'dog.dogId':1002,
                    'dog.dogsex':'男'
                },
                success:function (res) {
                    console.log(res)
                }
            });
        })

        // 多个对象批量传参
        $("#ajax06").click(function () {
            var loverList=[];
            for (var i = 0; i <5 ; i++) {
                var lover={
                    name:"落落"+i,
                    age:20
                }
                loverList.push(lover)
            }
            //多个对象，就属于复杂的  ，复杂用 json
           $.ajax({
               url:"/api/admin/ajax06",
               type:"POST",
               data:JSON.stringify(loverList),
               // dataType:"get",  get请求是无法 传输 json 协议 和 json数据的！！！
               // get 是没有方法体的
               dataType:"json",
               contentType:"application/json;charset=UTF-8",
               success:function (r) {
                   console.log(r)
               }
           });
        })

        // map传参   翻版的第一种  不常用，要会 map+json 常用
        $("#ajax07").click(function () {
            var map={
                'adminName':"张三子",
                'adminPwd':"1235622",
                'adminTime':"2019-09-08 23:09:29"
            }
            $.ajax({
                url:"/api/admin/ajax07",
                type:"POST",
                dataType:"json",
                data:JSON.stringify(map),
                contentType:"application/json;charset=UTF-8",
                success:function (res) {
                    console.log(res)
                }

            });
        })

        //常见于 分页  ajax08
        $("#ajax08").click(function () {
            $.ajax({
                url:"/api/admin/ajax08",
                type:"post",
                dataType:'json',
                data:{
                    'name':'米米',   //4 个k-v 是3个参数，name和age 是对象  和其他两个参数
                    'age':20,
                    'pageSize':5,
                    'page':2
                },
                success:function (r) {
                    console.log(r)
                }
            });
        })
    })
</script>


</body>
</html>
