import components.scoretracker.ScoreTracker;
import components.scoretracker.ScoreTracker1;
import components.scoretracker.ScoreTrackerKernel.Team;

/**
 * Demonstrates a simple play-by-play sequence using {@code ScoreTracker}.
 */
public final class ScoreTrackerPlayByPlayDemo {

    private ScoreTrackerPlayByPlayDemo() {
    }

    /**
     * Entry point.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args) {
        ScoreTracker tracker = new ScoreTracker1();

        tracker.addThreePointer(Team.HOME);
        tracker.addTwoPointer(Team.AWAY);
        tracker.addFreeThrow(Team.HOME);
        tracker.addFoul(Team.AWAY);
        tracker.nextPeriod();
        tracker.addTwoPointer(Team.AWAY);

        System.out.println("End of simulation:");
        System.out.println(tracker);

        if (!tracker.isTie()) {
            System.out.println("Leader: " + tracker.leader());
        } else {
            System.out.println("Game is tied.");
        }
    }
}
