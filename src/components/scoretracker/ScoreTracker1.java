package components.scoretracker;

/**
 * {@code ScoreTracker} represented by per-team score/foul counters and period.
 *
 * <p>
 * Convention: {@code homeScore >= 0}, {@code awayScore >= 0},
 * {@code homeFouls >= 0}, {@code awayFouls >= 0}, and {@code currentPeriod >= 1}
 * </p>
 *
 * <p>
 * Correspondence: this = ({@code homeScore}, {@code awayScore},
 * {@code homeFouls}, {@code awayFouls}, {@code currentPeriod})
 * </p>
 */
public class ScoreTracker1 extends ScoreTrackerSecondary {

    /*
     * Private members ---------------------------------------------------------
     */

    private int homeScore;
    private int awayScore;
    private int homeFouls;
    private int awayFouls;
    private int currentPeriod;

    /**
     * Creates a fresh start-of-game representation.
     */
    private void createNewRep() {
        this.homeScore = 0;
        this.awayScore = 0;
        this.homeFouls = 0;
        this.awayFouls = 0;
        this.currentPeriod = 1;
    }

    /**
     * No-argument constructor.
     */
    public ScoreTracker1() {
        this.createNewRep();
    }

    @Override
    public final ScoreTracker newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(ScoreTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source.getClass() == this.getClass()
                : "Violation of: source has dynamic type of this";

        ScoreTracker1 localSource = (ScoreTracker1) source;
        this.homeScore = localSource.homeScore;
        this.awayScore = localSource.awayScore;
        this.homeFouls = localSource.homeFouls;
        this.awayFouls = localSource.awayFouls;
        this.currentPeriod = localSource.currentPeriod;
        localSource.createNewRep();
    }

    @Override
    public final void addPoints(Team team, int points) {
        assert team != null : "Violation of: team is not null";
        assert points == 1 || points == 2 || points == 3
                : "Violation of: points = 1 or points = 2 or points = 3";

        if (team == Team.HOME) {
            this.homeScore += points;
        } else {
            this.awayScore += points;
        }
    }

    @Override
    public final void addFoul(Team team) {
        assert team != null : "Violation of: team is not null";

        if (team == Team.HOME) {
            this.homeFouls++;
        } else {
            this.awayFouls++;
        }
    }

    @Override
    public final void nextPeriod() {
        this.currentPeriod++;
    }

    @Override
    public final int score(Team team) {
        assert team != null : "Violation of: team is not null";
        if (team == Team.HOME) {
            return this.homeScore;
        }
        return this.awayScore;
    }

    @Override
    public final int fouls(Team team) {
        assert team != null : "Violation of: team is not null";
        if (team == Team.HOME) {
            return this.homeFouls;
        }
        return this.awayFouls;
    }

    @Override
    public final int period() {
        return this.currentPeriod;
    }
}
