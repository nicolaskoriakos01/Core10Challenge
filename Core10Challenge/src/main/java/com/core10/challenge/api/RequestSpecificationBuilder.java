package com.core10.challenge.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {

    private RequestSpecification request = RestAssured.given();

    public RequestSpecification build(){
        return this.request;
    }
}
