package util;

import entity.User;
import entity.UserCombination;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Tool {

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 组合成一个新User
     *
     * @param u
     * @param imagepath
     * @return
     */
    public UserCombination mergeTwoUser(User u, String imagepath) {
        UserCombination usercombination = new UserCombination();
        usercombination.setId(u.getId());
        usercombination.setUsername(u.getUsername());
        usercombination.setPassword(u.getPassword());
        usercombination.setEmail(u.getEmail());
        usercombination.setDelivery_address(u.getDelivery_address());
        usercombination.setMobilephone(u.getMobilephone());
        usercombination.setCity(u.getCity());
        usercombination.setUser_rights(u.getUser_rights());
        usercombination.setRegister_time(u.getRegister_time());
        usercombination.setImagepath(imagepath);

        return usercombination;
    }

    /**
     * 获取随机号(10位)
     *
     * @return
     */
    public static String getRandomReqNo() {
        int length = 10;
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
