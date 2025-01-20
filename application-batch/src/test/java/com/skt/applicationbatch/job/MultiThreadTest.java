package com.skt.applicationbatch.job;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MultiThreadTest {
    @Test
    void name() throws InterruptedException {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        ThreadNotSafeCounter threadNotSafeCounter = new ThreadNotSafeCounter();
        LockCounter lockCounter = new LockCounter();

        // 테스트할 쓰레드 수
        int numThreads = 100;
        int numIncrements = 1000;

        // 여러 개의 쓰레드를 생성하여 카운터를 증가시킴
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIncrements; j++) {
                    synchronizedCounter.increment();  // 카운터 증가
                    threadNotSafeCounter.increment();  // 카운터 증가
                    lockCounter.increment();  // 카운터 증가
                }
            });
            threads[i].start();  // 쓰레드 시작
        }

        // 모든 쓰레드가 작업을 마칠 때까지 기다림
        for (Thread thread : threads) {
            thread.join();
        }
        // 최종 카운트 출력
        System.out.println("SynchronizedCounter Final Count: " + synchronizedCounter.getCount());
        System.out.println("ThreadNotSafeCounter Final Count: " + threadNotSafeCounter.getCount());
        System.out.println("LockCounter Final Count: " + lockCounter.getCount());
    }

    public static class SynchronizedCounter {
        private int count = 0;

        public synchronized void increment() {
            count = count + 1;
        }

        public synchronized int getCount() {
            return count;
        }
    }

    public static class LockCounter {
        private int count = 0;
        private final Lock lock = new ReentrantLock();  // Lock 객체 생성

        // 카운터 증가 메서드
        public void increment() {
            lock.lock();  // 락을 획득
            try {
                count++;  // 카운터 증가
            } finally {
                lock.unlock();  // 반드시 unlock()을 호출하여 락을 해제
            }
        }

        public int getCount() {
            return count;
        }
    }


    public static class ThreadNotSafeCounter {
        private int count = 0;

        public void increment() {
            count = count + 1;
        }

        public int getCount() {
            return count;
        }
    }
}