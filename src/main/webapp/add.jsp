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
    <title>Title</title>
</head>
<body>
<p>hi</p>
<form action="add" method="post">
    <input type="hidden" name="action" value="add"/>

    Mã mặt bằng (*) <input type="text" name="ma" required> <br>

    Diện tích (*) <input type="text" name="dientich" required> <br>

    Trạng thái (*) <select name="trangthai">
    <option value="Trống">Trống</option>
    <option value="Hạ Tầng">Hạ Tầng</option>
    <option value="Đầy Đủ">Đầy Đủ</option>
    <br>

    Tầng (*) <select name="tang" >
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    <option value="6">6</option>
    <option value="7">7</option>
    <option value="8">8</option>
    <option value="9">9</option>
    <option value="10">10</option>
    <option value="11">11</option>
    <option value="12">12</option>
    <option value="13">13</option>
    <option value="14">14</option>
    <option value="15">15</option>
</select> <br>

    Loại văn phòng (*) <select name="loai" >
    <option value="chiase">Văn phòng chia sẻ</option>
    <option value="trongoi">Văn phòng trọn gói</option>
</select>
    Giá
            <td>Giá cho thuê:</td>
            <td><input type="number" step="0.01" name="giaChoThue" required/></td>
        </tr>
        <tr>
            <td>Ngày bắt đầu:</td>
            <td><input type="date" name="ngayBatDau" required/></td>
        </tr>
        <tr>
            <td>Ngày kết thúc:</td>
            <td><input type="date" name="ngayKetThuc" required/></td>
        </tr>


    <input type="submit" value="Lưu"/>
    <a href="matbang?action=list">Quay lại danh sách</a>
</form>
</body>
</html>
