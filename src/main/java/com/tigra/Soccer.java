package com.tigra;

import com.google.common.collect.ImmutableMap;

import java.util.*;

public class Soccer {
    public interface Entity {}

    public enum Resource implements Entity {
        BOOTS, BANANA, SHOULDER, THORNS, HELMET, HOOKED_BALL, RED_COLOR, SPYGLASS, WET_GRASS, FIST, GREASE, CORNER, SPRING,
        ADVANCED_PAPER
    }

    public enum Weakness implements Entity {
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

    public enum Enemy implements Entity {
        // Schwalbenkonig
        DIVER(Arrays.asList(Weakness.RED_CARD, Weakness.FOUL)),
        // Dribbelkunstler
        DRIBBLE(Arrays.asList(Weakness.TACKLE, Weakness.FOUL)),
        // Hackentrick
        BACK_HEEL(Arrays.asList(Weakness.SLIDING_TACKLE, Weakness.TACKLE)),
        // Manndeckung
        MAN_MARKING(Arrays.asList(Weakness.FOUL, Weakness.HEAD_STRIKE)),
        // Reaktionsstark
        FAST_REFLEXES(Arrays.asList(Weakness.BICYCLE_KICK, Weakness.PENALTY)),
        // Scheller Laufer
        FAST_RUNNER(Arrays.asList(Weakness.SLIDING_TACKLE, Weakness.PASS)),
        // Raumdeckung
        ZONE_DEFENSE(Arrays.asList(Weakness.CORNER_KICK, Weakness.PASS)),
        // Guter Springer
        HIGH_JUMPER(Arrays.asList(Weakness.HEAD_STRIKE, Weakness.PENALTY));

        private final List<Weakness> weaknesses;

        Enemy(List<Weakness> weaknesses) {
            this.weaknesses = weaknesses;
        }

        public List<Weakness> getWeaknesses() {
            return weaknesses;
        }
    }

    public enum Match implements Entity {
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
        MATCH_LAST(ImmutableMap.<Enemy, Integer>builder()
                .put(Enemy.DIVER, 2)
                .put(Enemy.DRIBBLE, 4)
                .put(Enemy.BACK_HEEL, 1)
                .put(Enemy.MAN_MARKING, 3)
                .put(Enemy.FAST_RUNNER, 3)
                .put(Enemy.ZONE_DEFENSE, 4)
                .put(Enemy.HIGH_JUMPER, 4)
                .build()
        ),
        EIGHT_FINAL(ImmutableMap.<Enemy, Integer>builder()
                .put(Enemy.DIVER, 7)
                .put(Enemy.DRIBBLE, 8)
                .put(Enemy.MAN_MARKING, 2)
                .put(Enemy.FAST_REFLEXES, 4)
                .put(Enemy.FAST_RUNNER, 3)
                .put(Enemy.ZONE_DEFENSE, 7)
                .put(Enemy.HIGH_JUMPER, 5)
                .build()
        ),
        FOURTH_FINAL(ImmutableMap.<Enemy, Integer>builder()
                .put(Enemy.DIVER, 8)
                .put(Enemy.DRIBBLE, 4)
                .put(Enemy.BACK_HEEL, 4)
                .put(Enemy.MAN_MARKING, 16)
                .put(Enemy.FAST_REFLEXES, 6)
                .put(Enemy.FAST_RUNNER, 4)
                .put(Enemy.ZONE_DEFENSE, 4)
                .put(Enemy.HIGH_JUMPER, 4)
                .build()
        ),
        HALF_FINAL(ImmutableMap.<Enemy, Integer>builder()
                .put(Enemy.DIVER, 5)
                .put(Enemy.DRIBBLE, 14)
                .put(Enemy.BACK_HEEL, 9)
                .put(Enemy.MAN_MARKING, 5)
                .put(Enemy.FAST_REFLEXES, 10)
                .put(Enemy.FAST_RUNNER, 3)
                .put(Enemy.ZONE_DEFENSE, 4)
                .put(Enemy.HIGH_JUMPER, 8)
                .build()
        ),
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

        private final Map<Enemy, Integer> enemies;

        Match(Map<Enemy, Integer> enemies) {
            this.enemies = enemies;
        }

        public Map<Enemy, Integer> getEnemies() {
            return enemies;
        }
    }

    public static class Hit {
        private final Enemy enemy;
        private Weakness weakness;

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

        public void setWeakness(Weakness weakness) {
            this.weakness = weakness;
        }
    }

    public static final Map<Entity, List<Entity>> entityLinks;
    static {
        Map<Entity, List<Entity>> linkBuilder = new LinkedHashMap<Entity, List<Entity>>();
        for(Entity[] entities : new Entity[][]{Resource.values(), Weakness.values(), Enemy.values()}) {
            for(Entity entity : entities) {
                linkBuilder.put(entity, new ArrayList<Entity>());
            }
        }

        // Weakness <-> Resource
        for(Weakness weakness : Weakness.values()) {
            for(Resource resource : weakness.getRecipe().keySet()) {
                linkBuilder.get(resource).add(weakness);
                linkBuilder.get(weakness).add(resource);
            }
        }

        // Weakness <-> Enemy
        for(Enemy enemy : Enemy.values()) {
            linkBuilder.get(enemy).addAll(enemy.getWeaknesses());
            for(Weakness weakness : enemy.getWeaknesses()) {
                linkBuilder.get(weakness).add(enemy);
            }
        }

        entityLinks = ImmutableMap.copyOf(linkBuilder);
    }
}
