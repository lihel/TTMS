<%@ page import="com.ttms.model.Employee" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lmy
  Date: 17-11-19
  Time: 下午2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>user</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="view/CSS/bs.css">
    <link rel="stylesheet" href="view/CSS/index.css">
    <link href="view/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>

<div class="header">
    <jsp:include page="head.jsp"></jsp:include>
</div>

<br>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="/nav.jsp"></jsp:include>
        </div>

        <div class="col-md-9">
            <div class="search" >
                <form class="form-inline" name="myForm" action="EmployeeServlet?method=searchByPage" method="post">
                    <!-- <input type="hidden" name="method" value="search"/> -->
                    <input type="text" class="form-control" name="emp_name" value="${search_emp_name}"/>
                    <input type="submit" class="btn btn-primary" value="查   询" />&nbsp;&nbsp;
                    <input type="button" class="btn btn-danger" value="增   加" onclick="javascript:window.location='add.jsp'"/>
                </form>
            </div>

            <div class="tableUser">

                <div style="padding-top:10px;">
                    <table class="table table-bordered table-hover" style="text-align:center;">
                        <tr>
                            <th>用户ID</th>
                            <th>编号</th>
                            <th>姓名</th>
                            <th>电话</th>
                            <th>地址</th>
                            <th>邮箱</th>
                            <th colspan=2>操作</th>
                        </tr>
                        <%
                            int currentPage=1;  //当前页
                            int allCount=0;     //总记录数
                            int allPageCount=0; //总页数
                            Employee Employee=null;
                            //查看request中是否有currentPage信息，如没有，则说明首次访问该页
                            if(request.getAttribute("allEmployee")!=null)
                            {
                                //获取Action返回的信息
                                currentPage=((Integer)request.getAttribute("currentPage")).intValue();
                                ArrayList<Employee> list=(ArrayList<Employee>)request.getAttribute("allEmployee");
                                allCount=((Integer)request.getAttribute("allCount")).intValue();
                                allPageCount=((Integer)request.getAttribute("allPageCount")).intValue();
                                if(list!=null && list.size()>0)
                                {
                                    for(int i=0;i<list.size();i++)
                                    {
                                        if(i%2==0)
                                            out.println("<tr class='success'>");
                                        else
                                            out.println("<tr class='active'>");
                        %>
                        <th><%=list.get(i).getEmp_id()%></th>
                        <th><%=list.get(i).getEmp_no()%></th>
                        <th><%=list.get(i).getEmp_name()%></th>
                        <th><%=list.get(i).getEmp_tel_num()%></th>
                        <th><%=list.get(i).getEmp_addr()%></th>
                        <th><%=list.get(i).getEmp_email()%></th>
                        <th><a href="EmployeeServlet?method=searchById&emp_id=<%=list.get(i).getEmp_id()%>">修改</a></th>
                        <th><a href="EmployeeServlet?method=delete&emp_id=<%=list.get(i).getEmp_id()%>&emp_name=${search_emp_name}&currentPage=${currentPage}">删除</a></th>
                        </tr>
                        <%
                                    }
                                }
                            }
                        %>
                    </table>
                </div>

            </div>
            <div class="text-center">
                <ul class="pagination">
                    <li><a href="EmployeeServlet?method=searchByPage&currentPage=1&emp_name=${search_emp_name}">首页</a></li>
                    <li><a href="EmployeeServlet?method=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&emp_name=${search_emp_name}">上一页</a></li>
                    <li><a href="EmployeeServlet?method=searchByPage&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&emp_name=${search_emp_name}">下一页</a></li>
                    <li><a href="EmployeeServlet?method=searchByPage&currentPage=<%=allPageCount%>&emp_name=${search_emp_name}">末页</a></li>
                </ul>
            </div>
            <p>
                <input type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#add"
                       value="添加员工">
                <input type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
                       value="编辑员工信息" onclick="editEmp()">
                <input type="button" class="btn btn-danger btn-lg" data-toggle="modal"
                       value="删除员工" onclick="del()">
            </p>
        </div>
    </div>
</div>
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close hwLayer-close" data-dismiss="modal">×</button>
                <h4 class="modal-title">添加新员工</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>员工编号</label>
                    <input type="text" class="form-control" placeholder="员工编号" id="Num" required="required"
                           onblur="checkNum()">
                    <div id="uNumber"></div>
                </div>

                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" placeholder="姓名" id="Name" required="required"
                           onblur="checkName()">
                    <div id="uName"></div>
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <input type="text" class="form-control" placeholder="性别，输入男或女" id="empSex"
                           onblur="checkSex()">
                    <div id="uSex"></div>
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <input type="text" class="form-control" placeholder="年龄" id="empAge" onblur="checkAge()">
                    <div id="uAge"></div>
                </div>

                <div class="form-group">
                    <label>电话号码</label>
                    <input type="text" class="form-control" placeholder="电话号码" id="empphoneNumber"
                           onblur="checkPhoneNumber()">
                    <div id="uPhoneNumber"></div>
                </div>

                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" placeholder="ＱＱ邮箱" id="empEmail"
                           onblur="checkEmail()">
                    <div id="uEmail"></div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default hwLayer-cancel" data-dismiss="modal"><i
                        class="fa fa-remove "></i>关闭
                </button>
                <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="check()"><i
                        class="fa fa-save"></i> 保存
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close hwLayer-close" data-dismiss="modal">×</button>
                <h4 class="modal-title">编辑员工信息</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="userName">员工编号</label>
                    <input type="text" class="form-control" placeholder="员工编号" id="userNum" required="required">

                </div>
                <div class="form-group">
                    <label for="userName">姓名</label>
                    <input type="text" class="form-control" placeholder="姓名" id="userName" required="required">
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <input type="text" class="form-control" placeholder="性别，输入男或女" id="sex">
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <input type="text" class="form-control" placeholder="年龄" id="age">
                </div>
                <div class="form-group">
                    <label>电话号码</label>
                    <input type="text" class="form-control" placeholder="电话号码" id="phoneNumber">
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="text" class="form-control" placeholder="ＱＱ邮箱" id="email">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default hwLayer-cancel" data-dismiss="modal"><i
                        class="fa fa-remove "></i>关闭
                </button>
                <button type="button" class="btn btn-primary hwLayer-ok" data-dismiss="modal"><i
                        class="fa fa-save"></i> 保存
                </button>
            </div>
        </div>
    </div>
</div>
<script src="view/JS/jquery.min.js"></script>
<script src="view/JS/bootstrap.min.js"></script>
<script src="view/JS/user.js"></script>
</body>

</html>
