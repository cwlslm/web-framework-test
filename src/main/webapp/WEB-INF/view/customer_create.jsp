<%--
  Created by IntelliJ IDEA.
  User: JFeng
  Date: 2020/2/8
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>客户管理 - 创建客户</title>
    </head>
    <body>
    <h1>创建客户</h1>

    <form action="${BASE}/customer_create_api" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <th>客户名称</th>
                <th>联系人</th>
                <th>电话号码</th>
                <th>邮箱地址</th>
                <th>照片</th>
                <th>操作</th>
            </tr>
            <tr>
                <td><input type="text" name="name"></td>
                <td><input type="text" name="contact"></td>
                <td><input type="text" name="telephone"></td>
                <td><input type="text" name="email"></td>
                <td><input type="file" name="photo"></td>
                <td>
                    <input type="submit" value="确定">
                </td>
            </tr>
        </table>
    </form>
    </body>
</html>
