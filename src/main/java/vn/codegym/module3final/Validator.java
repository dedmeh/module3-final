package vn.codegym.module3final;

import vn.codegym.module3final.Model.MatBangChoThue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern MA_REGEX = Pattern.compile("^[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}$");

    public static String validate(MatBangChoThue mb) {
        // Mã mặt bằng
        if (mb.getMaMatBang() == null || !MA_REGEX.matcher(mb.getMaMatBang()).matches()) {
            return "Mã mặt bằng phải đúng định dạng XXX-XX-XX (chỉ chữ hoa và số).";
        }

        // Diện tích
        if (mb.getDienTich() <= 20) {
            return "Diện tích phải lớn hơn 20 m².";
        }

        // Tầng
        if (mb.getTang() < 1 || mb.getTang() > 15) {
            return "Tầng phải nằm trong khoảng từ 1 đến 15.";
        }

        // Giá
        if (mb.getGiaChoThue() <= 1000000) {
            return "Giá tiền phải lớn hơn 1.000.000 VNĐ.";
        }

        // Ngày
        LocalDate bd = mb.getNgayBatDau();
        LocalDate kt = mb.getNgayKetThuc();

        if (bd == null || kt == null) {
            return "Ngày bắt đầu và kết thúc không được để trống.";
        }
        if (!bd.isBefore(kt)) {
            return "Ngày bắt đầu phải nhỏ hơn ngày kết thúc.";
        }
        long monthsBetween = ChronoUnit.MONTHS.between(bd, kt);
        if (monthsBetween < 6) {
            return "Ngày kết thúc phải cách ngày bắt đầu ít nhất 6 tháng.";
        }

        // Nếu tất cả hợp lệ
        return null;
    }
}

