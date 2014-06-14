package com.tigra;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Match {
    TRAINING(ImmutableMap.<Enemy, Integer>builder()
            .put(Enemy.DIVER, 2)
            .put(Enemy.DRIBBLE, 3)
            .put(Enemy.BACK_HEEL, 2)
            .put(Enemy.MAN_MARKING, 4)
            .put(Enemy.FAST_REFLEXES, 1)
            .build()
    ),
    MATCH_FIRST(ImmutableMap.<Enemy, Integer>builder()
            .put(Enemy.DIVER, 3)
            .put(Enemy.MAN_MARKING, 8)
            .put(Enemy.FAST_REFLEXES, 1)
            .put(Enemy.FAST_RUNNER, 5)
            .put(Enemy.HIGH_JUMPER, 1)
            .build()
    ),
    MATCH_LAST(null),
    EIGHT_FINAL(null),
    FOURTH_FINAL(ImmutableMap.<Enemy, Integer>builder()
            .put(Enemy.DIVER, 8)
            .put(Enemy.DRIBBLE, 4)
            .put(Enemy.BACK_HEEL, 4)
            .put(Enemy.MAN_MARKING, 8)
            .put(Enemy.FAST_REFLEXES, 6)
            .put(Enemy.FAST_RUNNER, 4)
            .put(Enemy.ZONE_DEFENSE, 12)
            .put(Enemy.HIGH_JUMPER, 4)
            .build()
    ),
    HALF_FINAL(null),
    FINAL(ImmutableMap.<Enemy, Integer>builder()
            .put(Enemy.DIVER, 10)
            .put(Enemy.DRIBBLE, 10)
            .put(Enemy.BACK_HEEL, 22)
            .put(Enemy.MAN_MARKING, 7)
            .put(Enemy.FAST_REFLEXES, 10)
            .put(Enemy.FAST_RUNNER, 18)
            .put(Enemy.ZONE_DEFENSE, 18)
            .put(Enemy.HIGH_JUMPER, 8)
            .build()
    );


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

    public static class Hit {
        private final Enemy enemy;
        private final Weakness weakness;

        public Hit(Enemy enemy, Weakness weakness) {
            this.enemy = enemy;
            this.weakness = weakness;
        }

        public Enemy getEnemy() {
            return enemy;
        }

        public Weakness getWeakness() {
            return weakness;
        }
    }

    private final Map<Enemy, Integer> enemies;

    Match(Map<Enemy, Integer> enemies) {
        this.enemies = enemies;
    }

    public Map<Enemy, Integer> getEnemies() {
        return enemies;
    }
}
