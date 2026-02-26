package components.scoretracker;

/**
 * Single-file proof-of-concept for a basketball score tracker component.
 */
public final class ScoreTrackerPOC {

    /** Two-team model for this proof-of-concept. */
    public enum Team {
        HOME,
        AWAY
    }

    private static final int REGULATION_PERIODS = 4;

    private int homeScore;
    private int awayScore;
    private int homeFouls;
    private int awayFouls;
    private int periodNumber;
    private Team possession;

    /** Initializes this tracker to a new game. */
    public ScoreTrackerPOC() {
        this.resetGame();
    }

    /**
     * Kernel-like method: adds 1, 2, or 3 points for the requested team.
     */
    public void addPoints(Team team, int points) {
        if (points < 1 || points > 3) {
            throw new IllegalArgumentException("points must be 1, 2, or 3");
        }
        if (team == Team.HOME) {
            this.homeScore += points;
        } else {
            this.awayScore += points;
        }
    }

    /** Kernel-like method: records one team foul for the current period. */
    public void addFoul(Team team) {
        if (team == Team.HOME) {
            this.homeFouls++;
        } else {
            this.awayFouls++;
        }
    }

    /**
     * Kernel-like method: advances the game period; team fouls reset each period.
     */
    public void nextPeriod() {
        this.periodNumber++;
        this.homeFouls = 0;
        this.awayFouls = 0;
    }

    /** Kernel-like method: reports score for a team. */
    public int getScore(Team team) {
        return team == Team.HOME ? this.homeScore : this.awayScore;
    }

    /** Kernel-like method: reports fouls for a team. */
    public int getFouls(Team team) {
        return team == Team.HOME ? this.homeFouls : this.awayFouls;
    }

    /** Kernel-like method: reports the current period label (Q1..Q4, OT1..). */
    public String getPeriod() {
        if (this.periodNumber <= REGULATION_PERIODS) {
            return "Q" + this.periodNumber;
        }
        return "OT" + (this.periodNumber - REGULATION_PERIODS);
    }

    /** Secondary-like method: convenience wrapper around addPoints(team, 1). */
    public void addFreeThrow(Team team) {
        this.addPoints(team, 1);
    }

    /** Secondary-like method: convenience wrapper around addPoints(team, 2). */
    public void addTwoPointer(Team team) {
        this.addPoints(team, 2);
    }

    /** Secondary-like method: convenience wrapper around addPoints(team, 3). */
    public void addThreePointer(Team team) {
        this.addPoints(team, 3);
    }

    /** Secondary-like method: reports whether game is tied. */
    public boolean isTie() {
        return this.homeScore == this.awayScore;
    }

    /**
     * Secondary-like method: reports leader, or null when tied.
     */
    public Team getLeader() {
        if (this.isTie()) {
            return null;
        }
        return this.homeScore > this.awayScore ? Team.HOME : Team.AWAY;
    }

    /** Secondary-like method: flips possession between home and away. */
    public void togglePossession() {
        this.possession = this.possession == Team.HOME ? Team.AWAY : Team.HOME;
    }

    /** Secondary-like method: reports current possession. */
    public Team getPossession() {
        return this.possession;
    }

    /** Secondary-like method: resets score, fouls, period, and possession. */
    public void resetGame() {
        this.homeScore = 0;
        this.awayScore = 0;
        this.homeFouls = 0;
        this.awayFouls = 0;
        this.periodNumber = 1;
        this.possession = Team.HOME;
    }

    private void printState() {
        System.out.println(
                "Period=" + this.getPeriod()
                        + " | HOME=" + this.homeScore
                        + " (fouls=" + this.homeFouls + ")"
                        + " | AWAY=" + this.awayScore
                        + " (fouls=" + this.awayFouls + ")"
                        + " | Possession=" + this.possession);
    }

    /**
     * Demo main proving the client-side value of the API.
     */
    public static void main(String[] args) {
        ScoreTrackerPOC game = new ScoreTrackerPOC();

        System.out.println("== Tip-off ==");
        game.printState();

        game.addTwoPointer(Team.HOME);
        game.addThreePointer(Team.AWAY);
        game.addFreeThrow(Team.HOME);
        game.addFoul(Team.HOME);
        game.togglePossession();

        System.out.println("== Mid Q1 ==");
        game.printState();
        System.out.println("Tie? " + game.isTie());
        System.out.println("Leader: " + game.getLeader());

        game.nextPeriod();
        game.addThreePointer(Team.HOME);
        game.addThreePointer(Team.HOME);
        game.addTwoPointer(Team.AWAY);

        System.out.println("== Q2 Action ==");
        game.printState();
        System.out.println("Leader: " + game.getLeader());

        game.resetGame();
        System.out.println("== New Game Reset ==");
        game.printState();
    }
}
