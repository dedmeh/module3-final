<%--
  Created by IntelliJ IDEA.
  User: ductr
  Date: 9/5/2025
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mat Bang List</title>
</head>
<body>
<h1>Mat Bang List</h1>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.codegym.module3final.Model.MatBangChoThue" %>
<html>
<head>
    <title>Danh sách Mặt Bằng</title>
    <script>
        function confirmDelete(ma) {
            return confirm("Bạn có chắc chắn muốn xóa mặt bằng với mã số " + ma + " không?");
        }
    </script>
</head>
<body>
<h2>Danh sách Mặt Bằng</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Mã</th>
        <th>Loại</th>
        <th>Trạng thái</th>
        <th>Diện tích</th>
        <th>Tầng</th>
        <th>Mô tả chi tiết</th>
        <th>Giá</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Hành động</th>
    </tr>
    <%
        List<MatBangChoThue> list = (List<MatBangChoThue>) request.getAttribute("list");
        if (list != null) {
            for (MatBangChoThue mb : list) {
    %>
    <tr>
        <td><%= mb.getMaMatBang() %></td>
        <td><%= mb.getLoaiVanPhong() %></td>
        <td><%= mb.getTrangThai() %></td>
        <td><%= mb.getDienTich() %></td>
        <td><%= mb.getTang() %></td>
        <td><%= mb.getMoTaChiTiet()%></td>
        <td><%= mb.getGiaChoThue() %></td>
        <td><%= mb.getNgayBatDau() %></td>
        <td><%= mb.getNgayKetThuc() %></td>
        <td>
            <a href="/TComplex?action=delete&ma=<%= mb.getMaMatBang() %>"
               onclick="return confirmDelete('<%= mb.getMaMatBang() %>')">Xóa</a>
        </td>
    </tr>
    <%      }
    }
    %>
</table>

<p><a href="/TComplex?action=add">Thêm mới mặt bằng</a></p>
</body>
</html>


</body>
</html>
