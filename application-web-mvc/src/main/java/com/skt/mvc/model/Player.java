package com.skt.mvc.model;

import lombok.Data;

@Data
public class Player {
    private String playerId;
    private String playerName;
    private Team team;
}
