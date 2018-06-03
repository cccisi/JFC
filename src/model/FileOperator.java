package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: cccis
 * @Date: 6/3/2018 09:42
 * @Description:
 */
public class FileOperator {

    // 公共域
    public static int number = 0;
    public static int letter= 0;
    public static int numlet= 0;

    // 读口令存储文件
    public void readFile(String filePath) {

        try {
            // Create BufferedReader
            BufferedReader myReader = new BufferedReader(new FileReader(filePath));

            // Set value of first line of reader
            String read = myReader.readLine();

            while (read != null) {
                statistics(read);
                read = myReader.readLine();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void statistics(String userinfo){
        String password = getPassword(userinfo);
        if(isNumeric(password)){
            number += 1;
        }else if(isLetter(password)){
            letter += 1;
        }else {
            numlet += 1;
        }
    }

    private String getPassword(String userinfo){
//        String password = null;
        String[] result = userinfo.split(":");
        return result[2];
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param password
     * @return
     */
    public boolean isNumeric(String password){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(password);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 利用正则表达式判断字符串是否是字母
     * @param password
     * @return
     */
    public boolean isLetter(String password){
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher isNum = pattern.matcher(password);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}

