package com.skt.mvc.repository;

import com.skt.mvc.model.domain.Sample;
import com.skt.mvc.model.entity.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepository {
    private final SampleJpaRepository sampleJpaRepository;

    @Override
    public Sample findById(String id) {
        return sampleJpaRepository.findById(id)
                .orElseThrow()
                .toModel();
    }

    @Override
    public void save(Sample sample) {
        sampleJpaRepository.save(SampleEntity.toEntity(sample));
    }
}
