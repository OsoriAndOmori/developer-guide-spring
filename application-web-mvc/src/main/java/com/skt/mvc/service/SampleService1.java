package com.skt.mvc.service;

import com.skt.mvc.model.domain.Sample;
import com.skt.mvc.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService1 {
    private final SampleService2 sampleService2;

    private final SampleRepository sampleRepository;
    private final TransactionManager transactionManager;

    public String sample() {
        return "sample";
    }

    public Sample sample2(String id) {
        return sampleRepository.findById(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void sample3(String success) throws InterruptedException {
        sampleRepository.save(new Sample(System.currentTimeMillis(), "테스트1"));
        Thread.sleep(10);
        sampleRepository.save(new Sample(System.currentTimeMillis(), "테스트2"));
        Thread.sleep(10);

        if (!success.equals("success")) {
            throw new RuntimeException("test");
        }
        sampleService2.saveSamples();
    }

    public void sample4(String success) throws SQLException, InterruptedException {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/dev_guide";
        Connection con = DriverManager.getConnection(dbUrl, "dev", "dev.password");
        try {
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            String SQL = String.format("insert into sample values ('%s', '%s');", System.currentTimeMillis(), "테스트5");
            stmt.executeUpdate(SQL);
            Thread.sleep(10);
            String SQL2 = String.format("insert into sample values ('%s', '%s');", System.currentTimeMillis(), "테스트6");
            stmt.executeUpdate(SQL2);

            if (!success.equals("success")) {
                throw new RuntimeException("test");
            }

            con.commit();

            log.info("record adding Success!");
        } catch (Exception e) {
            log.error("ERROR!", e);
            con.rollback();
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
}
