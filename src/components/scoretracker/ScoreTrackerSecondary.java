package components.scoretracker;

/**
 * Score tracker secondary methods layered on top of the kernel.
 */
public abstract class ScoreTrackerSecondary implements ScoreTracker {

    @Override
    public final void addFreeThrow(Team team) {
        this.addPoints(team, 1);
    }

    @Override
    public final void addTwoPointer(Team team) {
        this.addPoints(team, 2);
    }

    @Override
    public final void addThreePointer(Team team) {
        this.addPoints(team, 3);
    }

    @Override
    public final boolean isTie() {
        return this.score(Team.HOME) == this.score(Team.AWAY);
    }

    @Override
    public final Team leader() {
        assert !this.isTie() : "Violation of: not this.isTie()";
        if (this.score(Team.HOME) > this.score(Team.AWAY)) {
            return Team.HOME;
        }
        return Team.AWAY;
    }

    @Override
    public final void resetGame() {
        this.clear();
    }

    @Override
    public final String toString() {
        return "HOME " + this.score(Team.HOME) + " - AWAY " + this.score(Team.AWAY)
                + " | Fouls(H/A): " + this.fouls(Team.HOME) + "/" + this.fouls(Team.AWAY)
                + " | Period " + this.period();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScoreTracker)) {
            return false;
        }
        ScoreTracker other = (ScoreTracker) obj;
        return this.score(Team.HOME) == other.score(Team.HOME)
                && this.score(Team.AWAY) == other.score(Team.AWAY)
                && this.fouls(Team.HOME) == other.fouls(Team.HOME)
                && this.fouls(Team.AWAY) == other.fouls(Team.AWAY)
                && this.period() == other.period();
    }

    @Override
    public final int hashCode() {
        int result = 17;
        result = 31 * result + this.score(Team.HOME);
        result = 31 * result + this.score(Team.AWAY);
        result = 31 * result + this.fouls(Team.HOME);
        result = 31 * result + this.fouls(Team.AWAY);
        result = 31 * result + this.period();
        return result;
    }
}
