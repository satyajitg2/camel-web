package com.web.camel.camelweb;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class BasicResponse {

    private String code;
    private String shortDescription;
    private String longDescription;
}
