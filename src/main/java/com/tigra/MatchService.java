package com.tigra;

public class MatchService {
    public Match[] matchList() {
        return Match.values();
    }

    public Match match(String name) {
        return Match.valueOf(name);
    }
}
