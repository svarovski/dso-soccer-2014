package com.tigra;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private final List<Match.Hit> hits = new ArrayList<Match.Hit>();
    private final Map<Match.Enemy, Integer> enemyStats = new LinkedHashMap<Match.Enemy, Integer>();
    private final Map<Match.Weakness, Integer> weaknessUsage = new LinkedHashMap<Match.Weakness, Integer>();
    private final Map<Match.Resource, Integer> resourceUsage = new LinkedHashMap<Match.Resource, Integer>();
    private final Map<Match.Resource, Integer> resourceRemainder = new LinkedHashMap<Match.Resource, Integer>();

    public Solution() {
        for(Match.Resource resource : Match.Resource.values()) {
            resourceUsage.put(resource, 0);
        }
    }

    public void addHit(Match.Hit hit) {
        hits.add(hit);

        add(enemyStats, hit.getEnemy(), 1);
        final Match.Weakness weakness = hit.getWeakness();
        add(weaknessUsage, weakness, 1);

        for(Map.Entry<Match.Resource, Integer> recipePart : weakness.getRecipe().entrySet()) {
            add(resourceUsage, recipePart.getKey(), recipePart.getValue());
        }
    }

    public static <T> void add(Map<T, Integer> map, T key, Integer delta) {
        Integer count = map.get(key);
        if(count == null) count = 0;
        map.put(key, count + delta);
    }

    public static <T> void sub(Map<T, Integer> map, T key, Integer delta) {
        map.put(key, map.get(key) - delta);
    }

    public boolean isComplete(Match match) {
        if(match.getEnemies().size() != enemyStats.size())
            return false;
        for(Match.Enemy enemy : match.getEnemies().keySet()) {
            if(!match.getEnemies().get(enemy).equals(enemyStats.get(enemy)))
                return false;
        }
        return true;
    }

    public int checkCondition(Map<Match.Resource, Integer> condition) {
        int result = 0;
        for(Map.Entry<Match.Resource, Integer> resource : resourceUsage.entrySet()) {
            Integer remainder = condition.get(resource.getKey()) - resource.getValue();
            resourceRemainder.put(resource.getKey(), remainder);
            if(remainder < 0)
                result += remainder;
        }

        return result;
    }

    public List<Match.Hit> getHits() {
        return hits;
    }

    public Map<Match.Weakness, Integer> getWeaknessUsage() {
        return weaknessUsage;
    }

    public Map<Match.Resource, Integer> getResourceUsage() {
        return resourceUsage;
    }

    public Map<Match.Resource, Integer> getResourceRemainder() {
        return resourceRemainder;
    }
}
