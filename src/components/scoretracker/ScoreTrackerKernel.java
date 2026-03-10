package components.scoretracker;

import components.standard.Standard;

/**
 * Score tracker kernel component with minimal game-state operations.
 */
public interface ScoreTrackerKernel extends Standard<ScoreTracker> {

    /**
     * The two teams tracked by this component.
     */
    enum Team {
        HOME, AWAY
    }

    /**
     * Adds points to the specified team.
     *
     * @param team
     *            the team to update
     * @param points
     *            points to add
     * @updates this
     * @requires points = 1 or points = 2 or points = 3
     * @ensures <pre>
     * this.score(team) = #this.score(team) + points and
     * for all t: Team where t /= team, this.score(t) = #this.score(t) and
     * this.fouls(HOME) = #this.fouls(HOME) and
     * this.fouls(AWAY) = #this.fouls(AWAY) and
     * this.period() = #this.period()
     * </pre>
     */
    void addPoints(Team team, int points);

    /**
     * Adds one foul to the specified team for the current game state.
     *
     * @param team
     *            the team to update
     * @updates this
     * @ensures <pre>
     * this.fouls(team) = #this.fouls(team) + 1 and
     * for all t: Team where t /= team, this.fouls(t) = #this.fouls(t) and
     * this.score(HOME) = #this.score(HOME) and
     * this.score(AWAY) = #this.score(AWAY) and
     * this.period() = #this.period()
     * </pre>
     */
    void addFoul(Team team);

    /**
     * Advances the game to the next period.
     *
     * @updates this
     * @ensures <pre>
     * this.period() = #this.period() + 1 and
     * this.score(HOME) = #this.score(HOME) and
     * this.score(AWAY) = #this.score(AWAY) and
     * this.fouls(HOME) = #this.fouls(HOME) and
     * this.fouls(AWAY) = #this.fouls(AWAY)
     * </pre>
     */
    void nextPeriod();

    /**
     * Reports the score for the specified team.
     *
     * @param team
     *            the team to query
     * @return the score for {@code team}
     * @ensures score = this.score(team)
     */
    int score(Team team);

    /**
     * Reports the foul count for the specified team.
     *
     * @param team
     *            the team to query
     * @return the foul count for {@code team}
     * @ensures fouls = this.fouls(team)
     */
    int fouls(Team team);

    /**
     * Reports the current period number.
     *
     * @return the current period, where 1 is the opening period
     * @ensures period = this.period()
     */
    int period();
}
