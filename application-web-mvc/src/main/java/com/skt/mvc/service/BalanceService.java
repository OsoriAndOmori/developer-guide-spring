package com.skt.mvc.service;

import com.skt.mvc.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Balance fetchAmount(String userId) {
        return balanceRepository.selectByUserId(userId);
    }

    public Balance insert(String userId, long amount) {
        Balance balance = balanceRepository.selectByUserId(userId);
        Balance result = balance.plusMoney(amount);
        return balanceRepository.save(result);
    }

    public Balance withdrawal(String userId, long amount) {
        Balance balance = balanceRepository.selectByUserId(userId);
        Balance result = balance.minusMoney(amount);
        return balanceRepository.save(result);
    }
}
