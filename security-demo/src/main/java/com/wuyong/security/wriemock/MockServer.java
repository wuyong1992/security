package com.wuyong.security.wriemock;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * created by JianGuo
 * on 2018/1/18
 * description: wire mock 服务 方便前后端分离
 * 1.下载http://wiremock.org/docs/running-standalone/
 * 2.按照站点提示执行启动server
 * 3.启动下面的main函数
 */
public class MockServer {


    public static void main(String[] args) throws IOException {
        myMock("/order/1","order");
        // 需要别的服务继续调用方法就可以了
    }

    private static void myMock(String url, String filename) throws IOException {
        // 配置端口
        WireMock.configureFor("localhost", 9090);
        // 清楚之前的映射
        WireMock.removeAllMappings();

        // 读取mock json
        ClassPathResource resource = new ClassPathResource("/mock/response/" + filename + ".json");
        String content = FileUtils.readFileToString(resource.getFile(), "UTF-8");
        // 构建测试路劲
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
