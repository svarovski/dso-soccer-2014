package com.tigra;

import javax.inject.Singleton;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class MatchService {
    private static final Logger LOG = Logger.getLogger(MatchService.class.getName());

    public Soccer.Match[] matchList() {
        return Soccer.Match.values();
    }

    public Soccer.Match match(String name) {
        try {
            return Soccer.Match.valueOf(name);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.SEVERE, "Unknown match: " + name);
            return null;
        }
    }

    public Soccer.Resource[] resourceList() {
        return Soccer.Resource.values();
    }

    public Solution solve(Soccer.Match match, Map<Soccer.Resource, Integer> condition) {
        Solution solution0 = getSolution(match, condition, 0);
        Solution solution1 = getSolution(match, condition, 1);
        final Decision decision = new Decision(solution0, condition);
        final Solution result = decision.isBetter(solution1) ? solution1 : solution0;
        LOG.log(Level.INFO, String.format("Solution comparison: %s", decision.diffStr()));
        return result;
    }

    private Solution getSolution(Soccer.Match match, Map<Soccer.Resource, Integer> condition, int start) {
        LOG.log(Level.INFO, "Building solution " + start);
        Solution solution = new Solution(match, condition, start);
        while(true) {
            LOG.log(Level.INFO, "Attempting to adjust solution");
            if(!adjustSolution(solution, condition)) break;
        }
        return solution;
    }

    private static class Decision {
        private final Map<Soccer.Resource, Integer> condition;
        private final int shortage;
        private final double weight;
        private String diffStr;

        public Decision(Solution solution, Map<Soccer.Resource, Integer> condition) {
            this.condition = condition;
            this.shortage = solution.getShortage();
            this.weight = solution.getWeight(condition);
        }

        public boolean isBetter(Solution solution) {
            final int newShortage = solution.getShortage();
            final double newWeight = solution.getWeight(condition);
            diffStr = String.format(Locale.US, "%d->%d,%.6f->%.6f", shortage, newShortage, weight, newWeight);
            return shortage > newShortage || shortage == newShortage && weight > newWeight;
        }

        public String diffStr() {
            return diffStr;
        }
    }

    private boolean adjustSolution(Solution solution, Map<Soccer.Resource, Integer> condition) {
        boolean adjusted = false;
        for (int i = 0; i < solution.getHits().size(); i++) {
            Decision decision = new Decision(solution, condition);
            Soccer.Hit hit = solution.getHits().get(i);
            Soccer.Weakness weakness = hit.getWeakness();
            Soccer.Weakness attempt = getOtherWeakness(hit.getEnemy(), weakness);
            solution.changeHit(i, attempt);
            if(decision.isBetter(solution)) {
                LOG.log(Level.INFO, String.format("Changed %s: %s->%s (%s)",
                        hit.getEnemy(), weakness, attempt, decision.diffStr()));
                adjusted = true;
            }
            else {
//                LOG.log(Level.INFO, String.format("Tried %s: %s->%s (%s)",
//                        hit.getEnemy(), weakness, attempt, decision.diffStr()));
                solution.changeHit(i, weakness);
            }
        }
        return adjusted;
    }

    private Soccer.Weakness getOtherWeakness(Soccer.Enemy enemy, Soccer.Weakness weakness) {
        return enemy.getWeaknesses().get(enemy.getWeaknesses().get(0).equals(weakness) ? 1 : 0);
    }
}
