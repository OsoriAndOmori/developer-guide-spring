package com.skt.mvc.model.entity;

import com.skt.mvc.model.domain.Sample;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "sample")
public class SampleEntity {
    @Id
    private String id;
    private String name;

    public static SampleEntity toEntity(Sample sample) {
        SampleEntity entiy = new SampleEntity();
        entiy.setId(sample.getId());
        entiy.setName(sample.getName());
        return entiy;
    }

    public Sample toModel() {
        Sample sample = new Sample();
        sample.setId(this.id);
        sample.setName(this.name);
        return sample;
    }
}