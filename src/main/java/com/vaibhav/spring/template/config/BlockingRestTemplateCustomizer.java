package com.vaibhav.spring.template.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnection;
    private final Integer defaultMaxPerRoute;
    private final Integer requestTimeOut;
    private final Integer socketTimeOut;

    public BlockingRestTemplateCustomizer(@Value("${vs.rest-template.connectionManager.maxTotal}") Integer maxTotalConnection,
                                          @Value("${vs.rest-template.connectionManager.maxPerRoute}") Integer defaultMaxPerRoute,
                                          @Value("${vs.rest-template.requestConfig.timeout.request}") Integer requestTimeOut,
                                          @Value("${vs.rest-template.requestConfig.timeout.socket}") Integer socketTimeOut) {
        this.maxTotalConnection = maxTotalConnection;
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.requestTimeOut = requestTimeOut;
        this.socketTimeOut = socketTimeOut;
    }


    public ClientHttpRequestFactory getRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnection);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(requestTimeOut)
                .setSocketTimeout(socketTimeOut)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.getRequestFactory());
    }
}
