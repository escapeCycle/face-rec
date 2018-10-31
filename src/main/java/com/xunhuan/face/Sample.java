package com.xunhuan.face;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.xunhuan.face.util.ImageBinary;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author tianhuan
 * @date 2018-10-31 11:56
 **/
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "14626077";
    public static final String API_KEY = "qfmnnDCEdrbsyABGjz40CW4u";
    public static final String SECRET_KEY = "DtxPxEVZouHg2gQXdoh8KIgxPWKxPmaX";

    public static void main(String[] args) {
        Sample sample = new Sample();
        /*
            人脸检测
         */
//        sample.detect();
        /*
            照片比对  返回相似度信息
         */
        sample.match();

    }


    public void detect(){
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        /*
            face_field   age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype
         */
        options.put("face_field", "age,beauty");
        /*
            最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸；最大值10，检测图片中面积最大的几张人脸
         */
        options.put("max_face_num", "2");
        /*
            人脸的类型 LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片 WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图 CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片 默认LIVE
         */
        options.put("face_type", "LIVE");

//        String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String imageBinary = ImageBinary.getImageBinary("D:\\myself\\faceRec\\3.png");
        JSONObject res = client.detect(imageBinary, imageType, options);
        System.out.println(res.toString(2));
    }

    public void match(){
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        String imageBinary1 = ImageBinary.getImageBinary("D:\\myself\\faceRec\\2.png");
        String imageBinary2 = ImageBinary.getImageBinary("D:\\myself\\faceRec\\3.png");
        MatchRequest req1 = new MatchRequest(imageBinary1, "BASE64");
        req1.setFaceType("LIVE");
        MatchRequest req2 = new MatchRequest(imageBinary2, "BASE64");
        req2.setFaceType("LIVE");
        List<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);
        JSONObject match = client.match(requests);
        System.out.println(match.toString(2));
    }
}
