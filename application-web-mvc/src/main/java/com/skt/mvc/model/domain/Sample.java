package com.skt.mvc.model.domain;

import lombok.Data;

@Data
public class Sample {
    private String id;
    private String name;

    public Sample() {
    }

    public Sample(long id, String name) {
        this.id = String.valueOf(id);
        this.name = name;
    }
}