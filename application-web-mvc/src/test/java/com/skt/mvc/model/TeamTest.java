package com.skt.mvc.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {
    @Test
    void stackOverFlow1() {
        Team team = new Team();
        team.setTeamId("testTeam");
        team.setTeamName("김기훈팀");

        Player player1 = new Player();
        player1.setTeam(team);
        player1.setPlayerId("player1");
        player1.setPlayerName("player1");
        Player player2 = new Player();
        player2.setTeam(team);
        player2.setPlayerId("player2");
        player2.setPlayerName("player2");

        team.setPlayers(List.of(player1, player2));
        //System.out.println(team);
    }

    @Test
    void stackOverFlow() {
        StackOverflowError exception = assertThrows(StackOverflowError.class, () -> {
            Team team = new Team();
            team.setTeamId("testTeam");
            team.setTeamName("김기훈팀");

            Player player1 = new Player();
            player1.setTeam(team);
            player1.setPlayerId("player1");
            player1.setPlayerName("player1");
            Player player2 = new Player();
            player2.setTeam(team);
            player2.setPlayerId("player2");
            player2.setPlayerName("player2");

            team.setPlayers(List.of(player1, player2));

            System.out.println(team);
        });

        // 예외 메시지 검증
        assert (exception.getClass() == StackOverflowError.class);
    }
}