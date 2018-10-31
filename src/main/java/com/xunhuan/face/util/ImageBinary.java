package com.xunhuan.face.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author tianhuan
 * @date 2018-03-14 21:04
 **/
public class ImageBinary {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) throws Exception {
//        String post = "http://219.237.75.67:9082";
//        String path = "/EB/P2PImageKeyServlet?0.8119297421457894";
//        String method = "GET";
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("type", "en4");
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        //根据API的要求，定义相对应的Content-Type
//        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        Map<String, String> querys = new HashMap<String, String>();
//        HttpResponse httpResponse = HttpUtils.doGet(post, path, method, headers, querys);
//        System.out.println(EntityUtils.toByteArray(httpResponse.getEntity()));
//        File file = new File("D:\\myself\\文字识别\\ceshi\\ceshi.jpg");
//        System.out.println(encoder.encodeBuffer(EntityUtils.toByteArray(httpResponse.getEntity())).trim());
//        FileUtils.writeByteArrayToFile(file,EntityUtils.toByteArray(httpResponse.getEntity()));


//        System.out.println(getImageBinary(""));

        //base64StringToImage(getImageBinary(""));
    }

    public static String getImageBinary(String Imgpath){
        File f = new File(Imgpath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void base64StringToImage(String savePath, String base64String){
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 =ImageIO.read(bais);

            File w2 = new File(savePath);//可以是jpg,png,gif格式

            ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
