package com.springcloud.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springcloud.webserver.data.exampleXmlData;

@RestController
public class WebServerController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 테스트 매핑 컨트롤러
     * 
     * @param param
     * @param headers
     * @param httpClientType
     * @return
     */
    @GetMapping("/webclient/{param}")
    public String testWebClient(
            @PathVariable String param,
            @RequestHeader HttpHeaders headers,
            @CookieValue(name = "httpclient-type", required = false, defaultValue = "undefined") String httpClientType) {

        log.info(">>>> Cookie 'httpclient-type={}'", httpClientType);

        headers.forEach((key, value) -> {
            log.info(String.format(">>>>> Header '%s' => %s", key, value));
        });

        log.info("### Received: /webclient/" + param);

        String msg = param + " => Working successfully !!!";
        log.info("### Sent: " + msg);
        return msg;
    }

    /**
     * return xml example
     * 
     * @return
     */
    @GetMapping("/getXml")
    public exampleXmlData getXml() {
        exampleXmlData example = new exampleXmlData();

        example.setHeader("200", "success");
        example.setBody(1, 1, 4);
        example.setItem("1", "테스트1");
        example.setItem("2", "테스트2");
        example.setItem("3", "테스트3");
        example.setItem("4", "테스트4");
        example.setItemList();

        return example;
    }

    /**
     * return json example
     * 
     * @return
     */
    @GetMapping("/getJson")
    public JsonObject getJson() {
        JsonObject result = new JsonObject();

        JsonArray list = new JsonArray();
        for (int i = 1; i < 1000; i++) {
            JsonObject e = new JsonObject();
            e.addProperty("id", "test" + i);
            e.addProperty("name", "테스트" + i);
            e.addProperty("email", "test" + i + "@testNet.com");
            e.addProperty("empNo", i);
            list.add(e);
        }
        result.add("data", list);
        result.addProperty("resultCode", "200");
        result.addProperty("resultMsg", "success");
        return result;
    }
}
