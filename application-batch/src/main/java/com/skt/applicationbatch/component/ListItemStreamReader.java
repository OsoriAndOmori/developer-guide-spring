package com.skt.applicationbatch.component;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.ItemStream;
import java.util.List;
import java.util.Iterator;

public class ListItemStreamReader <T> implements ItemStreamReader<T> {

    private Iterator<T> iterator;
    private List<T> items;

    // 생성자: List<String>을 받아 Iterator로 변환
    public ListItemStreamReader(List<T> items) {
        this.items = items;
        this.iterator = items.iterator();
    }

    @Override
    public T read() throws Exception {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;  // 더 이상 아이템이 없으면 null을 반환
        }
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // 이 메서드는 아이템 스트림을 열 때 호출됩니다. 필요시 초기화 작업 수행
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // 아이템 스트림 업데이트 시 호출됩니다. 필요시 상태 저장
    }

    @Override
    public void close() throws ItemStreamException {
        // 스트림을 닫을 때 호출됩니다.
    }
}
