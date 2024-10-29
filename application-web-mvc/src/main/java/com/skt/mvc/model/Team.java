package com.skt.mvc.model;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private String teamId;
    private String teamName;
    private List<Player> players;
}
