package ku.cs.models.verify;

public class Account {
    private String userName;
    private String name;

    // สมัครได้หรือไม่
    // TODO:ทำเพิ่มถ้าเวลาเหลือ ต้องมีตัวเลขกับตัวหนังสือ ยาวอย่างน้อย 6 ตัว เพื่อความปลอดภัย
    public boolean register(String userName, String password, String confirmPassword) {
        if (password.equals("") || confirmPassword.equals("")) {
            System.out.println("x ไม่ได้กรอกรหัสผ่าน");
            return false;
        } else if (!password.equals(confirmPassword)) {
            System.out.println("x รหัสผ่านไม่ตรงกัน");
            return false;
        }
        return true; // ไม่ผิดเงื่อนไขไหนเลย
    }

    public boolean login() {
        return false;
    }
}
