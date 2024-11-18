package com.skt.mvc.repository;

import com.skt.mvc.model.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, String> {

}
