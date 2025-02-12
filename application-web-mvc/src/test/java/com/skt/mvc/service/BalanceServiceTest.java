package com.skt.mvc.service;

import com.skt.mvc.repository.BalanceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {
    @Mock
    private BalanceRepository balanceRepository;
    @InjectMocks
    private BalanceService balanceService;

    @Test
    @DisplayName("잔액조회 1000원 들어있음")
    void balance() {
        String userId = "test";

        when(balanceRepository.selectByUserId(userId)).thenReturn(Balance.newInstance(1000));

        Balance balance = balanceService.fetchAmount(userId);

        assertEquals(balance.getBalance(), 1000);
    }

    @Test
    @DisplayName("입금. 잔액이 1000원이었고, 3000원 넣었을때 4000원 리턴")
    void balance1() {
        //given
        String userId = "test";
        long amount = 3000;

        //when
        when(balanceRepository.selectByUserId(userId)).thenReturn(Balance.newInstance(1000));
        when(balanceRepository.save(any())).thenReturn(Balance.newInstance(4000));

        //then
        Balance balance = balanceService.insert(userId, amount);

        //assert
        assertEquals(balance.getBalance(), 4000);
    }

    @Test
    @DisplayName("출금. 잔액이 4000원이었고, 3000원 빼면 잔액 1000남음")
    void balance2() {
        String userId = "test";
        long amount = 3000;

        //when
        when(balanceRepository.selectByUserId(userId)).thenReturn(Balance.newInstance(4000));
        when(balanceRepository.save(any())).thenReturn(Balance.newInstance(1000));

        //then
        Balance balance = balanceService.withdrawal(userId, amount);

        //assert
        assertEquals(balance.getBalance(), 1000);
    }

    @Test
    @DisplayName("출금. 잔액이 4000원이었고, 5000원 빼면 에러던짐")
    void balance3() {
        String userId = "test";
        long amount = 5000;

        //when = stub
        when(balanceRepository.selectByUserId(userId)).thenReturn(Balance.newInstance(4000));

        //then
        assertThrows(IllegalArgumentException.class, () -> balanceService.withdrawal(userId, amount));
    }

    @Test
    @DisplayName("출금. 잔액이 5000원이었고, 5000원 빼면 에러던짐. 0원 테스트")
    void balance4() {
        String userId = "test";
        long amount = 5000;

        //when
        when(balanceRepository.selectByUserId(userId)).thenReturn(Balance.newInstance(5000));
        when(balanceRepository.save(any())).thenReturn(Balance.newInstance(0));

        //then
        Balance balance = balanceService.withdrawal(userId, amount);

        //assert
        assertEquals(balance.getBalance(), 0);
    }
}