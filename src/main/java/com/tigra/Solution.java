package com.tigra;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private final List<Soccer.Hit> hits = new ArrayList<Soccer.Hit>();
    private final Map<Soccer.Enemy, Integer> enemyStats = new EnumMap<Soccer.Enemy, Integer>(Soccer.Enemy.class);
    private final Map<Soccer.Weakness, Integer> weaknessUsage = new EnumMap<Soccer.Weakness, Integer>(Soccer.Weakness.class);
    private final Map<Soccer.Resource, Integer> resourceUsage = new EnumMap<Soccer.Resource, Integer>(Soccer.Resource.class);
    private final Map<Soccer.Resource, Integer> resourceRemainder;
    private int shortage = 0;

    public Solution(Soccer.Match match, Map<Soccer.Resource, Integer> condition, int start) {
        this.resourceRemainder = new EnumMap<Soccer.Resource, Integer>(condition);
        for(Soccer.Resource resource : Soccer.Resource.values()) {
            resourceUsage.put(resource, 0);
        }

        for(Map.Entry<Soccer.Enemy, Integer> enemy : match.getEnemies().entrySet()) {
            for (int i = 0; i < enemy.getValue(); i++) {
                addHit(new Soccer.Hit(enemy.getKey(), enemy.getKey().getWeaknesses().get(start)));
            }
        }
    }

    protected void addHit(Soccer.Hit hit) {
        hits.add(hit);

        add(enemyStats, hit.getEnemy(), 1);
        final Soccer.Weakness weakness = hit.getWeakness();
        add(weaknessUsage, weakness, 1);

        for(Map.Entry<Soccer.Resource, Integer> recipePart : weakness.getRecipe().entrySet()) {
            add(resourceUsage, recipePart.getKey(), recipePart.getValue());
            int result = sub(resourceRemainder, recipePart.getKey(), recipePart.getValue());
            if(result < 0)
                shortage += Math.min(-result, recipePart.getValue());
        }
    }

    public void changeHit(int i, Soccer.Weakness weakness) {
        final Soccer.Hit hit = hits.get(i);
        Soccer.Weakness oldWeakness = hit.getWeakness();
        hit.setWeakness(weakness);

        sub(weaknessUsage, oldWeakness, 1);
        add(weaknessUsage, weakness, 1);

        for(Map.Entry<Soccer.Resource, Integer> recipePart : oldWeakness.getRecipe().entrySet()) {
            sub(resourceUsage, recipePart.getKey(), recipePart.getValue());
            int result = resourceRemainder.get(recipePart.getKey());
            if(result < 0)
                shortage -= Math.min(-result, recipePart.getValue());
            add(resourceRemainder, recipePart.getKey(), recipePart.getValue());
        }
        for(Map.Entry<Soccer.Resource, Integer> recipePart : weakness.getRecipe().entrySet()) {
            add(resourceUsage, recipePart.getKey(), recipePart.getValue());
            int result = sub(resourceRemainder, recipePart.getKey(), recipePart.getValue());
            if(result < 0)
                shortage += Math.min(-result, recipePart.getValue());
        }
    }

    public static <T> int add(Map<T, Integer> map, T key, Integer delta) {
        Integer count = map.get(key);
        if(count == null) count = 0;
        final int result = count + delta;
        map.put(key, result);
        return result;
    }

    public static <T> int sub(Map<T, Integer> map, T key, Integer delta) {
        final int result = map.get(key) - delta;
        map.put(key, result);
        return result;
    }

    public double getWeight(Map<Soccer.Resource, Integer> condition) {
        Map<Soccer.Resource, Double> percents = new EnumMap<Soccer.Resource, Double>(Soccer.Resource.class);
        double average = 0;
        for(Soccer.Resource resource : condition.keySet()) {
            final double percent = resourcePercent(resource, condition);
            average += percent;
            percents.put(resource, percent);
        }
        average /= Soccer.Resource.values().length;

        double weight = 0;
        for(Soccer.Resource resource : percents.keySet()) {
            final double dev = percents.get(resource) - average;
            weight += dev*dev;
        }
        return Math.sqrt(weight);
    }

    private double resourcePercent(Soccer.Resource resource, Map<Soccer.Resource, Integer> condition) {
        return resourceRemainder.get(resource) >= 0 && condition.get(resource) > 0
                ? (double) resourceUsage.get(resource) / condition.get(resource) : 1.0;
    }

    public List<Soccer.Hit> getHits() {
        return hits;
    }

    public Map<Soccer.Weakness, Integer> getWeaknessUsage() {
        return weaknessUsage;
    }

    public Map<Soccer.Resource, Integer> getResourceUsage() {
        return resourceUsage;
    }

    public Map<Soccer.Resource, Integer> getResourceRemainder() {
        return resourceRemainder;
    }

    public int getShortage() {
        return shortage;
    }
}
