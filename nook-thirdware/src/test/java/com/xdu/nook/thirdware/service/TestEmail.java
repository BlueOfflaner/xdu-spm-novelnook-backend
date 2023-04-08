package com.xdu.nook.thirdware.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xdu.nook.thirdware.utils.ISBNUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEmail {

    public static final String url="https://www.googleapis.com/books/v1/volumes?q=isbn:9787540365707";

    public static final String json_obj="{\n" +
            "  \"kind\": \"books#volumes\",\n" +
            "  \"totalItems\": 1,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"kind\": \"books#volume\",\n" +
            "      \"id\": \"UKZMzwEACAAJ\",\n" +
            "      \"etag\": \"T9aw6qa61Dw\",\n" +
            "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/UKZMzwEACAAJ\",\n" +
            "      \"volumeInfo\": {\n" +
            "        \"title\": \"三国演义\",\n" +
            "        \"subtitle\": \"历史考证版\",\n" +
            "        \"authors\": [\n" +
            "          \"罗贯中\"\n" +
            "        ],\n" +
            "        \"publishedDate\": \"2022\",\n" +
            "        \"description\": \"本书采取分栏批注的形式,对三国演义正文中需加考证的部分予以标注,并直接在侧栏考证分析,眉目清晰,方便阅读.\",\n" +
            "        \"industryIdentifiers\": [\n" +
            "          {\n" +
            "            \"type\": \"ISBN_10\",\n" +
            "            \"identifier\": \"7540365706\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"type\": \"ISBN_13\",\n" +
            "            \"identifier\": \"9787540365707\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"readingModes\": {\n" +
            "          \"text\": false,\n" +
            "          \"image\": false\n" +
            "        },\n" +
            "        \"pageCount\": 0,\n" +
            "        \"printType\": \"BOOK\",\n" +
            "        \"maturityRating\": \"NOT_MATURE\",\n" +
            "        \"allowAnonLogging\": false,\n" +
            "        \"contentVersion\": \"preview-1.0.0\",\n" +
            "        \"panelizationSummary\": {\n" +
            "          \"containsEpubBubbles\": false,\n" +
            "          \"containsImageBubbles\": false\n" +
            "        },\n" +
            "        \"language\": \"zh-CN\",\n" +
            "        \"previewLink\": \"http://books.google.com.hk/books?id=UKZMzwEACAAJ&dq=isbn:9787540365707&hl=&cd=1&source=gbs_api\",\n" +
            "        \"infoLink\": \"http://books.google.com.hk/books?id=UKZMzwEACAAJ&dq=isbn:9787540365707&hl=&source=gbs_api\",\n" +
            "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/%E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89.html?hl=&id=UKZMzwEACAAJ\"\n" +
            "      },\n" +
            "      \"saleInfo\": {\n" +
            "        \"country\": \"HK\",\n" +
            "        \"saleability\": \"NOT_FOR_SALE\",\n" +
            "        \"isEbook\": false\n" +
            "      },\n" +
            "      \"accessInfo\": {\n" +
            "        \"country\": \"HK\",\n" +
            "        \"viewability\": \"NO_PAGES\",\n" +
            "        \"embeddable\": false,\n" +
            "        \"publicDomain\": false,\n" +
            "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
            "        \"epub\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"pdf\": {\n" +
            "          \"isAvailable\": false\n" +
            "        },\n" +
            "        \"webReaderLink\": \"http://play.google.com/books/reader?id=UKZMzwEACAAJ&hl=&source=gbs_api\",\n" +
            "        \"accessViewStatus\": \"NONE\",\n" +
            "        \"quoteSharingAllowed\": false\n" +
            "      },\n" +
            "      \"searchInfo\": {\n" +
            "        \"textSnippet\": \"本书采取分栏批注的形式,对三国演义正文中需加考证的部分予以标注,并直接在侧栏考证分析,眉目清晰,方便阅读.\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Resource
    JavaMailSender javaMailSender;

    @Resource
    RestTemplate restTemplate;
    @Test
    public void test() throws MessagingException {
        String from="2114568838@qq.com";
        String to="1765017394@qq.com";
        String context ="测试数据";

        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("验证码");
        helper.setText(context,false);
        javaMailSender.send(message);
    }

    @Test
    public void testHttpClient() throws IOException {
        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject);

    }

    public static void main(String[] args) {
//        JSONObject jsonObject = JSONObject.parseObject(json_obj);
//        JSONArray items_arr = (JSONArray)jsonObject.get("items");
//        System.out.println("------------------");
//        System.out.println(items_arr);
//        System.out.println("------------------");
//        JSONObject item = (JSONObject) items_arr.get(0);
//        System.out.println(item);
//        System.out.println(jsonObject);
        String selfLink = ISBNUtils.parseSelfLink(json_obj);
        System.out.println(selfLink);

    }



}
