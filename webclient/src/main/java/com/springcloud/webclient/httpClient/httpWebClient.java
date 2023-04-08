package com.springcloud.webclient.httpClient;

import java.nio.charset.Charset;
import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * @author DK
 * @since 2022.06.07
 * @apiNote webClient Module
 */
public class httpWebClient {

        // httpClient config
        private static HttpClient httpClient = HttpClient.create()
                        // keepalive option
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        // 연결 활성화 유지 시간
                        .option(EpollChannelOption.TCP_KEEPIDLE, 300)
                        // 연결 활성화 유효성 검사 주기
                        .option(EpollChannelOption.TCP_KEEPINTVL, 60)
                        // 연결 종료 전 검사 횟수
                        .option(EpollChannelOption.TCP_KEEPCNT, 8)
                        // 연결 대기하는 시간
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                        // 응답 대기하는 시간
                        .responseTimeout(Duration.ofSeconds(30));

        // Memory 조정: 2M (default 256KB)
        private static ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                        .build();

        /**
         * XML Data get
         * 
         * @param url       ex) http://localhost:8080
         * @param uri       ex) /getXmlData
         * @param parameter ex) { name , "123" }
         * @return ex) Mono<T>
         * @apiNote
         *          XML 형식 데이터를 받기 위해서는 해당 형식과 동일하게 Data Class를 작성해야 한다.
         *          자세한 내용은 getXmlDataEample.java 참고.
         */
        public static Mono<getXmlDataExample> getResponseXML(String url, String uri,
                        MultiValueMap<String, String> parameter) {

                // Encoding Mode
                DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
                factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

                WebClient client = WebClient.builder()
                                .uriBuilderFactory(factory)
                                .baseUrl(url)
                                .clientConnector(new ReactorClientHttpConnector(httpClient))
                                // filter 를 이용한 Request / Response Control
                                /*
                                 * .filter(
                                 * (req, next) -> next.exchange(
                                 * // 예시 작업 : request 헤더 추가
                                 * ClientRequest.from(req).header("from", "webclient")
                                 * .build()))
                                 * .filter(
                                 * ExchangeFilterFunction.ofRequestProcessor(
                                 * // 예시 작업 : request 헤더 로깅
                                 * clientRequest -> {
                                 * log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
                                 * log.info("Request: {} {}",
                                 * clientRequest.method(),
                                 * clientRequest.url());
                                 * clientRequest.headers().forEach(
                                 * (name, values) -> values
                                 * .forEach(value -> log
                                 * .info("{} : {}", name,
                                 * value)));
                                 * return Mono.just(clientRequest);
                                 * }))
                                 * .filter(
                                 * // 예시 작업 : response 헤더 로깅
                                 * ExchangeFilterFunction.ofResponseProcessor(
                                 * clientResponse -> {
                                 * log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
                                 * clientResponse.headers().asHttpHeaders()
                                 * .forEach((name, values) -> values
                                 * .forEach(value -> log
                                 * .info("{} : {}", name,
                                 * value)));
                                 * return Mono.just(clientResponse);
                                 * }))
                                 */
                                // 메모리 전략 수립
                                .exchangeStrategies(exchangeStrategies)
                                // default Header 추가
                                .defaultHeader("user-agent",
                                                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
                                // default Cookie 추가
                                .defaultCookie("httpclient-type", "webclient")
                                .build();

                Mono<getXmlDataExample> response = client.get().uri(
                                uriBuilder -> uriBuilder.path(uri)
                                                .queryParams(parameter).build())
                                .accept(MediaType.APPLICATION_XML)
                                .acceptCharset(Charset.forName("UTF-8"))
                                .retrieve()
                                .bodyToMono(getXmlDataExample.class);
                return response;
        }

        /**
         * Json Data get
         * 
         * @param url       ex) http://localhost:8080
         * @param uri       ex) /getJson
         * @param parameter ex) { name , "123" }
         * @return
         *         JSON 데이터를 리턴받는 요청에 사용한다.
         *         String으로 받아오므로 따로 파싱이 필요하다
         */
        public static String getResponseJSON(String url, String uri, MultiValueMap<String, String> parameter) {

                // Encoding Mode
                DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(url);
                factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

                WebClient client = WebClient.builder()
                                .uriBuilderFactory(factory)
                                .baseUrl(url)
                                .clientConnector(new ReactorClientHttpConnector(httpClient))
                                // filter 를 이용한 Request / Response Control
                                /*
                                 * .filter(
                                 * (req, next) -> next.exchange(
                                 * // 예시 작업 : request 헤더 추가
                                 * ClientRequest.from(req).header("from", "webclient")
                                 * .build()))
                                 * .filter(
                                 * ExchangeFilterFunction.ofRequestProcessor(
                                 * // 예시 작업 : request 헤더 로깅
                                 * clientRequest -> {
                                 * log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
                                 * log.info("Request: {} {}",
                                 * clientRequest.method(),
                                 * clientRequest.url());
                                 * clientRequest.headers().forEach(
                                 * (name, values) -> values
                                 * .forEach(value -> log
                                 * .info("{} : {}", name,
                                 * value)));
                                 * return Mono.just(clientRequest);
                                 * }))
                                 * .filter(
                                 * // 예시 작업 : response 헤더 로깅
                                 * ExchangeFilterFunction.ofResponseProcessor(
                                 * clientResponse -> {
                                 * log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
                                 * clientResponse.headers().asHttpHeaders()
                                 * .forEach((name, values) -> values
                                 * .forEach(value -> log
                                 * .info("{} : {}", name,
                                 * value)));
                                 * return Mono.just(clientResponse);
                                 * }))
                                 */
                                // 메모리 전략 수립
                                .exchangeStrategies(exchangeStrategies)
                                // default Header 추가
                                .defaultHeader("user-agent",
                                                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
                                // default Cookie 추가
                                .defaultCookie("httpclient-type", "webclient")
                                .build();

                String response = client.get().uri(
                                uriBuilder -> uriBuilder.path(uri)
                                                .queryParams(parameter).build())
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();

                return response;
        }
}