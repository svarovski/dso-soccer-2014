package com.tigra;

import javax.inject.Singleton;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class MatchService {
    private static final Logger LOG = Logger.getLogger(MatchService.class.getName());

    public Match[] matchList() {
        return Match.values();
    }

    public Match match(String name) {
        try {
            return Match.valueOf(name);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.SEVERE, "Unknown match: " + name);
            return null;
        }
    }

    public Match.Resource[] resourceList() {
        return Match.Resource.values();
    }

    public Solution solve(Match match, Map<Match.Resource, Integer> condition) {
        Solution solution = new Solution();
        solution.addHit(new Match.Hit(Match.Enemy.DIVER, Match.Weakness.CORNER_KICK));
        solution.addHit(new Match.Hit(Match.Enemy.DRIBBLE, Match.Weakness.HEAD_STRIKE));
        solution.addHit(new Match.Hit(Match.Enemy.MAN_MARKING, Match.Weakness.PENALTY));
        solution.addHit(new Match.Hit(Match.Enemy.MAN_MARKING, Match.Weakness.CORNER_KICK));
        solution.addHit(new Match.Hit(Match.Enemy.ZONE_DEFENSE, Match.Weakness.HEAD_STRIKE));
        solution.checkCondition(condition);
        return solution;
    }

    public Map<Match.Weakness, Integer> weaknessStats(List<Match.Hit> solution) {
        Map<Match.Weakness, Integer> stats = new LinkedHashMap<Match.Weakness, Integer>();
        for(Match.Hit hit : solution) {
            Integer count = stats.get(hit.getWeakness());
            stats.put(hit.getWeakness(), count == null ? 1 : count + 1);
        }
        return stats;
    }

    public Map<Match.Resource, Integer[]> resourceStats(Map<Match.Weakness, Integer> weaknessStats, Map<Match.Resource, Integer> condition) {
        Map<Match.Resource, Integer[]> stats = new LinkedHashMap<Match.Resource, Integer[]>();
        for(Map.Entry<Match.Weakness, Integer> entry : weaknessStats.entrySet()) {
            for(Map.Entry<Match.Resource, Integer> recipePart : entry.getKey().getRecipe().entrySet()) {
                Integer[] count = stats.get(recipePart.getKey());
                if(count == null) count = new Integer[]{0, 0};
                count[0] += recipePart.getValue() * entry.getValue();
                count[1] = condition.get(recipePart.getKey()) - count[0];
                stats.put(recipePart.getKey(), count);
            }
        }
        return stats;
    }

}
