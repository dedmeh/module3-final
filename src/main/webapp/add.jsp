<%--
  Created by IntelliJ IDEA.
  User: ductr
  Date: 9/5/2025
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<meta charset="UTF-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String error = (String) request.getAttribute("errorMessage"); %>
<% if (error != null) { %>
<div style="color:red"><%= error %></div>
<% } %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="TComplex?action=list">Quay lại danh sách</a>

    <h2>Thêm mặt bằng mới</h2>

    <form action="TComplex" method="post">
    <input type="hidden" name="action" value="add"/>

    <table>
        <tr>
            <td>Mã mặt bằng (*):</td>
            <td><input type="text" name="ma" required/></td>
        </tr>
        <tr>
            <td>Diện tích (*):</td>
            <td><input type="number" name="dientich" min="20" required/></td>
        </tr>
        <tr>
            <td>Trạng thái (*):</td>
            <td>
                <select name="trangthai" required>
                    <option value="Trống">Trống</option>
                    <option value="Hạ tầng">Hạ tầng</option>
                    <option value="Đầy đủ">Đầy đủ</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Tầng (*):</td>
            <td>
                <select name="tang" required>
                    <% for (int i = 1; i <= 15; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Loại văn phòng (*):</td>
            <td>
                <select name="loai" required>
                    <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
                    <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Mô tả chi tiết:</td>
            <td><textarea name="chitiet" rows="3" cols="40"></textarea></td>
        </tr>
        <tr>
            <td>Giá cho thuê (*):</td>
            <td><input type="number" name="gia" min="1000000" required/> VNĐ</td>
        </tr>
        <tr>
            <td>Ngày bắt đầu (*):</td>
            <td><input type="date" name="ngaybd" required/></td>
        </tr>
        <tr>
            <td>Ngày kết thúc (*):</td>
            <td><input type="date" name="ngaykt" required/></td>
        </tr>
    </table>

    <input type="submit" value="Lưu"/>
    <a href="/TComplex?action=list"><button type="button">Hủy</button></a>
</form>
</body>
</html>
