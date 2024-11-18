package com.skt.mvc.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "sample")
public class SampleEntity {
    @Id
    private String id;
    private String name;
}