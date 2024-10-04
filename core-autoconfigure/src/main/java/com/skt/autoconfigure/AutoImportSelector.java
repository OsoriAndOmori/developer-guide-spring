package com.skt.autoconfigure;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AutoImportSelector {

    public String[] 자동설정목록가져와() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        // lets.auto.configure 읽어서 리스트 넘기자
        // META-INF 디렉토리에 있는 파일 경로
        String filePath = "classpath:META-INF/lets.auto.configure"; // 예: myfile.txt

        // Resource 객체 가져오기
        Resource resource = resourceLoader.getResource(filePath);
        List<String> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results.toArray(new String[0]);
    }
}
