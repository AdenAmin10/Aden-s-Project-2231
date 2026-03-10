package components.scoretracker;

/**
 * Score tracker enhanced interface.
 */
public interface ScoreTracker extends ScoreTrackerKernel {

    /**
     * Adds one point to the specified team.
     *
     * @param team
     *            the team to update
     * @updates this
     * @ensures this.score(team) = #this.score(team) + 1
     */
    void addFreeThrow(Team team);

    /**
     * Adds two points to the specified team.
     *
     * @param team
     *            the team to update
     * @updates this
     * @ensures this.score(team) = #this.score(team) + 2
     */
    void addTwoPointer(Team team);

    /**
     * Adds three points to the specified team.
     *
     * @param team
     *            the team to update
     * @updates this
     * @ensures this.score(team) = #this.score(team) + 3
     */
    void addThreePointer(Team team);

    /**
     * Reports whether the game is tied.
     *
     * @return true iff both teams have equal score
     * @ensures isTie = (this.score(HOME) = this.score(AWAY))
     */
    boolean isTie();

    /**
     * Reports the leading team.
     *
     * @return the team with the higher score
     * @requires not this.isTie()
     * @ensures <pre>
     * (this.score(HOME) > this.score(AWAY) implies leader = HOME) and
     * (this.score(AWAY) > this.score(HOME) implies leader = AWAY)
     * </pre>
     */
    Team leader();

    /**
     * Resets the tracker to the start-of-game state.
     *
     * @updates this
     * @ensures this = (0, 0, 0, 0, 1)
     */
    void resetGame();
}
