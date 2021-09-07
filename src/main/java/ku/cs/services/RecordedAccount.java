package ku.cs.services;

import ku.cs.models.verify.Account;

import java.io.*;

public class RecordedAccount extends Account {
    // เขียนไฟล์
    public String recordRegister(String name, String userName, String password, String confirmPassword) {
        if (name.equals("")) {
            return "ยังไม่ได้กรอกชื่อ";
        } else if (userName.equals("")) {
            return "ยังไม่ได้กรอก Username";
        } else if (checkUsernameAlreadyHave(userName)) {
            return "Username นี้ถูกใช้แล้ว";
        } else if (!register(userName, password, confirmPassword)) {
            return "กรอกรหัสผ่านไม่ครบหรือไม่ตรงกัน";
        } try {
            // สร้างตัวเขียนไฟล์
            FileWriter fileWriter = new FileWriter("data/userCSV.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(userName + "," + password + "," + name);
            printWriter.flush();
            printWriter.close();
            System.out.println("New account has been recorded!");
            return "Pass";
        } catch (IOException e) {
            System.err.println("บันทึกเกิดข้อผิดพลาด");
        }
        return "บันทึกไม่สำเร็จ";
    }

    public boolean checkUsernameAlreadyHave(String username) {
        try {
            FileReader fileReader = new FileReader("data/userCSV.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.startsWith(username)) {
                    return true;
                } line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Can't open file");
        }
        return false;
    }

    public boolean checkUsernameAlreadyHave(String username, String password) {
        try {
            FileReader fileReader = new FileReader("data/userCSV.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.startsWith(username)) {
                    String[] data = line.split(",");
                    String recordedPassword = data[1].trim();
                    if (password.equals(recordedPassword))
                        return true;
                } line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Can't open file");
        }
        return false;
    }
}
