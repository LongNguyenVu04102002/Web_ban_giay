package com.example.datn.config;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/ghn/")
@CrossOrigin("*")
public class GiaoHangNhanhController {
    private static final String API_BASE_URL = "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/";
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public GiaoHangNhanhController() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.set("token", "c00660e2-2e4f-11ef-8f55-4ee3d82283af");
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private String makeGetRequest(String endpoint) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(this.headers);
            ResponseEntity<String> response = this.restTemplate.exchange(API_BASE_URL + endpoint, HttpMethod.GET, entity, String.class);
            return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
        } catch (HttpClientErrorException e) {
            // Handle or log exception
            return null;
        }
    }

    @GetMapping("/provinces")
    public String getAllProvinces() {
        return makeGetRequest("province");
    }

    @GetMapping("/districts")
    public String getDistrictsByProvince(@RequestParam("province_id") Integer provinceId) {
        return makeGetRequest("district?province_id=" + provinceId);
    }

    @GetMapping("/wards")
    public String getWardsByDistrict(@RequestParam("district_id") Integer districtId) {
        return makeGetRequest("ward?district_id=" + districtId);
    }
}
