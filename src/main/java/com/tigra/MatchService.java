package com.tigra;

import javax.inject.Singleton;
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
        Solution solution = new Solution(match, condition);
        while(true) {
            LOG.log(Level.INFO, "Attempting to adjust solution");
            if(!adjustSolution(solution)) break;
        }
        return solution;
    }

    private static class Decision {
        private final int shortage;

        public Decision(Solution solution) {
            this.shortage = solution.getShortage();
        }

        public boolean isBetter(Solution solution) {
            return shortage > solution.getShortage();
        }

        public String diffStr(Solution solution) {
            return String.format("$d->%d", shortage, solution.getShortage());
        }
    }

    private boolean adjustSolution(Solution solution) {
        boolean adjusted = false;
        for (int i = 0; i < solution.getHits().size(); i++) {
            Decision decision = new Decision(solution);
            Soccer.Hit hit = solution.getHits().get(i);
            Soccer.Weakness weakness = hit.getWeakness();
            Soccer.Weakness attempt = getOtherWeakness(hit.getEnemy(), weakness);
            solution.changeHit(i, attempt);
            if(decision.isBetter(solution)) {
                LOG.log(Level.INFO, String.format("Changed %s: %s->%s (%s)",
                        hit.getEnemy(), weakness, attempt, decision.diffStr(solution)));
                adjusted = true;
            }
            else {
                solution.changeHit(i, weakness);
            }
        }
        return adjusted;
    }

    private Soccer.Weakness getOtherWeakness(Soccer.Enemy enemy, Soccer.Weakness weakness) {
        return enemy.getWeaknesses().get(enemy.getWeaknesses().get(0).equals(weakness) ? 1 : 0);
    }
}
