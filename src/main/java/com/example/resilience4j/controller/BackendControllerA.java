package com.example.resilience4j.controller;

import com.example.resilience4j.service.BackendServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/backendA")
public class BackendControllerA {
    private final BackendServiceA backendServiceA;

    @Autowired
    public BackendControllerA(BackendServiceA backendServiceA) {
        this.backendServiceA = backendServiceA;
    }

    @GetMapping("failure")
    public String failure() {
        return backendServiceA.failure();
    }

    @GetMapping("success")
    public String success() {
        return backendServiceA.success();
    }

    @GetMapping("successException")
    public String successException() {
        return backendServiceA.successException();
    }

    @GetMapping("ignore")
    public String ignore() {
        return backendServiceA.ignoreException();
    }

    @GetMapping("monoSuccess")
    public Mono<String> monoSuccess() {
        return backendServiceA.monoSuccess();
    }

    @GetMapping("monoFailure")
    public Mono<String> monoFailure() {
        return backendServiceA.monoFailure();
    }

    @GetMapping("fluxSuccess")
    public Flux<String> fluxSuccess() {
        return backendServiceA.fluxSuccess();
    }

    @GetMapping("monoTimeout")
    public Mono<String> monoTimeout() {
        return backendServiceA.monoTimeout();
    }

    @GetMapping("fluxTimeout")
    public Flux<String> fluxTimeout() {
        return backendServiceA.fluxTimeout();
    }

    @GetMapping("futureFailure")
    public CompletableFuture<String> futureFailure() {
        return backendServiceA.futureFailure();
    }

    @GetMapping("futureSuccess")
    public CompletableFuture<String> futureSuccess() {
        return backendServiceA.futureSuccess();
    }

    @GetMapping("futureTimeout")
    public CompletableFuture<String> futureTimeout() {
        return backendServiceA.futureTimeout();
    }

    @GetMapping("fluxFailure")
    public Flux<String> fluxFailure() {
        return backendServiceA.fluxFailure();
    }

    @GetMapping("fallback")
    public String failureWithFallback() {
        return backendServiceA.failureWithFallback();
    }
}