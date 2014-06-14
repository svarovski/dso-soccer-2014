package com.tigra;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Match {
    TRAINING(Arrays.asList(
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.FAST_REFLEXES
    )),
    MATCH_FIRST(Arrays.asList(
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.HIGH_JUMPER
    )),
    MATCH_LAST(null),
    EIGHT_FINAL(null),
    FOURTH_FINAL(Arrays.asList(
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER
    )),
    HALF_FINAL(null),
    FINAL(Arrays.asList(
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DIVER,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.DRIBBLE,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.BACK_HEEL,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.MAN_MARKING,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_REFLEXES,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.FAST_RUNNER,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.ZONE_DEFENSE,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER,
            Enemy.HIGH_JUMPER
    ));


    public enum Resource {
        BOOTS, BANANA, SHOULDER, THORNS, HELMET, HOOKED_BALL, RED_COLOR, SPYGLASS, WET_GRASS, FIST, GREASE, CORNER, SPRING,
        ADVANCED_PAPER
    }

    public enum Weakness {
        TACKLE(ImmutableMap.<Resource, Integer>builder().put(Resource.SHOULDER, 13).put(Resource.FIST, 6).build()),
        RED_CARD(ImmutableMap.<Resource, Integer>builder().put(Resource.ADVANCED_PAPER, 5).put(Resource.RED_COLOR, 5).build()),
        FOUL(ImmutableMap.<Resource, Integer>builder().put(Resource.BANANA, 7).put(Resource.WET_GRASS, 9).build()),
        BICYCLE_KICK(ImmutableMap.<Resource, Integer>builder().put(Resource.HOOKED_BALL, 6).put(Resource.SPRING, 9).build()),
        SLIDING_TACKLE(ImmutableMap.<Resource, Integer>builder().put(Resource.THORNS, 10).put(Resource.GREASE, 9).build()),
        PASS(ImmutableMap.<Resource, Integer>builder().put(Resource.BOOTS, 7).put(Resource.SPYGLASS, 7).build()),
        HEAD_STRIKE(ImmutableMap.<Resource, Integer>builder().put(Resource.HELMET, 7).put(Resource.SPRING, 4).build()),
        PENALTY(ImmutableMap.<Resource, Integer>builder().put(Resource.THORNS, 11).put(Resource.BOOTS, 8).build()),
        CORNER_KICK(ImmutableMap.<Resource, Integer>builder().put(Resource.BOOTS, 8).put(Resource.CORNER, 5).build());

        private final Map<Resource, Integer> recipe;

        Weakness(Map<Resource, Integer> recipe) {
            this.recipe = recipe;
        }

        public Map<Resource, Integer> getRecipe() {
            return recipe;
        }
    }

    public enum Enemy {
        DIVER(Arrays.asList(Weakness.RED_CARD, Weakness.FOUL)),
        DRIBBLE(Arrays.asList(Weakness.TACKLE, Weakness.FOUL)),
        BACK_HEEL(Arrays.asList(Weakness.SLIDING_TACKLE, Weakness.TACKLE)),
        MAN_MARKING(Arrays.asList(Weakness.FOUL, Weakness.HEAD_STRIKE)),
        FAST_REFLEXES(Arrays.asList(Weakness.BICYCLE_KICK, Weakness.PENALTY)),
        FAST_RUNNER(Arrays.asList(Weakness.SLIDING_TACKLE, Weakness.PASS)),
        ZONE_DEFENSE(Arrays.asList(Weakness.HEAD_STRIKE, Weakness.PASS)),
        HIGH_JUMPER(Arrays.asList(Weakness.HEAD_STRIKE, Weakness.PENALTY));

        private final List<Weakness> weaknesses;

        Enemy(List<Weakness> weaknesses) {
            this.weaknesses = weaknesses;
        }

        public List<Weakness> getWeaknesses() {
            return weaknesses;
        }
    }

    private final List<Enemy> enemies;

    Match(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
