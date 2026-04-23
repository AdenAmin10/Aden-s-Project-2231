import components.scoretracker.ScoreTracker;
import components.scoretracker.ScoreTracker1;
import components.scoretracker.ScoreTrackerKernel.Team;

/**
 * Demonstrates embedding {@code ScoreTracker} in a higher-level helper class.
 */
public final class ScoreTrackerLeadAlertDemo {

    /**
     * Thin wrapper that tracks lead changes.
     */
    private static final class LeadAlertBoard {
        private final ScoreTracker tracker;
        private Team lastLeader;

        LeadAlertBoard() {
            this.tracker = new ScoreTracker1();
            this.lastLeader = null;
        }

        void recordScoringPlay(Team team, int points) {
            this.tracker.addPoints(team, points);
            if (!this.tracker.isTie()) {
                Team currentLeader = this.tracker.leader();
                if (this.lastLeader != currentLeader) {
                    System.out.println("Lead change: " + currentLeader);
                    this.lastLeader = currentLeader;
                }
            }
        }

        void recordFoul(Team team) {
            this.tracker.addFoul(team);
        }

        void nextPeriod() {
            this.tracker.nextPeriod();
        }

        String snapshot() {
            return this.tracker.toString();
        }
    }

    private ScoreTrackerLeadAlertDemo() {
    }

    /**
     * Entry point.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {
        LeadAlertBoard board = new LeadAlertBoard();

        board.recordScoringPlay(Team.HOME, 2);
        board.recordScoringPlay(Team.AWAY, 3);
        board.recordScoringPlay(Team.HOME, 3);
        board.recordFoul(Team.HOME);
        board.nextPeriod();

        System.out.println("Live board:");
        System.out.println(board.snapshot());
    }
}
