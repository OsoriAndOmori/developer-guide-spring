package com.skt.mvc.repository;

import com.skt.mvc.model.domain.Sample;
import com.skt.mvc.model.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository {
    Sample findById(String id);

    void save(Sample sample);
}
