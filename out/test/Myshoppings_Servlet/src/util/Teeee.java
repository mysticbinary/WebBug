package util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Teeee {

    public static void main(String args[]) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Tool tool = new Tool();
        String s = tool.EncoderByMd5("1");

        System.out.println(s);

    }
}