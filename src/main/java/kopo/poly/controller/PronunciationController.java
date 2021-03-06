//package kopo.poly.controller;
//
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//import java.util.Base64;
//import com.google.gson.Gson;
//
//public class PronunciationController {
//
//    static public void main ( String[] args ) {
//        String openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/Pronunciation"; // 영어
//        //String openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/PronunciationKor";   //한국어
//        String accessKey = "8f051a3b-a3a7-4441-9359-24eeaa8302cd";    // 발급받은 API Key
//        String languageCode = "LANGUAGE_CODE";     // 언어 코드
//        String script = "PRONUNCIATION_SCRIPT";    // 평가 대본
//        String audioFilePath = "AUDIO_FILE_PATH";  // 녹음된 음성 파일 경로
//        String audioContents = null;
//
//        Gson gson = new Gson();
//
//        Map<String, Object> request = new HashMap<>();
//        Map<String, String> argument = new HashMap<>();
//
//        try {
//            Path path = Paths.get(audioFilePath);                 //오류
//            byte[] audioBytes = Files.readAllBytes(path);         //오류
//            audioContents = Base64.getEncoder().encodeToString(audioBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        argument.put("language_code", languageCode);
//        argument.put("script", script);
//        argument.put("audio", audioContents);
//
//        request.put("access_key", accessKey);
//        request.put("argument", argument);
//
//        URL url;
//        Integer responseCode = null;
//        String responBody = null;
//        try {
//            url = new URL(openApiURL);
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.setRequestMethod("POST");
//            con.setDoOutput(true);
//
//            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.write(gson.toJson(request).getBytes("UTF-8"));
//            wr.flush();
//            wr.close();
//
//            responseCode = con.getResponseCode();
//            InputStream is = con.getInputStream();
//            byte[] buffer = new byte[is.available()];
//            int byteRead = is.read(buffer);
//            responBody = new String(buffer);
//
//            System.out.println("[responseCode] " + responseCode);
//            System.out.println("[responBody]");
//            System.out.println(responBody);
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}