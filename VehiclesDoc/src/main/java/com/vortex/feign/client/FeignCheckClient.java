package com.vortex.feign.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-service", url = "${feign.auth-uri}")
public interface FeignCheckClient {
	  @GetMapping("/auth/serverCheck")
	  Map<String, Object> serverCheck();
}
