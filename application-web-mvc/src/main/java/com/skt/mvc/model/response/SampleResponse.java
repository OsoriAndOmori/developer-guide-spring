package com.skt.mvc.model.response;

import com.skt.mvc.model.domain.Sample;
import lombok.Data;

@Data
public class SampleResponse {
    private String nickname;

    public static SampleResponse of(Sample sample) {
        SampleResponse response = new SampleResponse();
        response.setNickname(sample.getName() + sample.getId());
        return response;
    }
}