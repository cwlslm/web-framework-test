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
        <title>客户管理 - 客户详情</title>
    </head>
    <body>
        <h1>客户详情</h1>

        <table>
            <tr>
                <th>客户名称</th>
                <th>联系人</th>
                <th>电话号码</th>
                <th>邮箱地址</th>
                <th>照片</th>
            </tr>
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contact}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td><img src="/asset/upload/${customer.photo}" width="100"></td>
            </tr>
        </table>
    </body>
</html>
