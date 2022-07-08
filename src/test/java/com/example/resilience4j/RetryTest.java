package com.example.resilience4j;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class RetryTest extends AbstractRetryTest {

    @Test
    public void backendAshouldRetryThreeTimes() {
        // When
        float currentCount = getCurrentCount(FAILED_WITH_RETRY, BACKEND_A);
        produceFailure(BACKEND_A);

        checkMetrics(FAILED_WITH_RETRY, BACKEND_A, currentCount + 1);
    }

    @Test
    public void backendBshouldRetryThreeTimes() {
        // 获取当前已经重试的次数
        float currentCount = getCurrentCount(FAILED_WITH_RETRY, BACKEND_B);
        // 发起一次失败的请求，造成一次重试
        produceFailure(BACKEND_B);
        // 从Prometheus中获取数据，进行验证
        checkMetrics(FAILED_WITH_RETRY, BACKEND_B, currentCount + 1);
    }

    @Test
    public void backendAshouldSucceedWithoutRetry() {
        float currentCount = getCurrentCount(SUCCESS_WITHOUT_RETRY, BACKEND_A);
        produceSuccess(BACKEND_A);

        checkMetrics(SUCCESS_WITHOUT_RETRY, BACKEND_A, currentCount + 1);
    }

    @Test
    public void backendBshouldSucceedWithoutRetry() {
        float currentCount = getCurrentCount(SUCCESS_WITHOUT_RETRY, BACKEND_B);
        produceSuccess(BACKEND_B);

        checkMetrics(SUCCESS_WITHOUT_RETRY, BACKEND_B, currentCount + 1);
    }

    private void produceFailure(String backend) {
        ResponseEntity<String> response = restTemplate.getForEntity("/" + backend + "/failure", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void produceSuccess(String backend) {
        ResponseEntity<String> response = restTemplate.getForEntity("/" + backend + "/success", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
