package components.scoretracker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.scoretracker.ScoreTrackerKernel.Team;

/**
 * JUnit tests for kernel + Standard methods in {@link ScoreTracker1}.
 */
public final class ScoreTracker1Test {

    /**
     * Returns a tracker with a non-trivial state for observer checks.
     *
     * @return populated tracker
     */
    private static ScoreTracker makeSampleState() {
        ScoreTracker tracker = new ScoreTracker1();
        tracker.addPoints(Team.HOME, 3);
        tracker.addPoints(Team.AWAY, 2);
        tracker.addFoul(Team.HOME);
        tracker.nextPeriod();
        return tracker;
    }

    /**
     * Asserts full observable state.
     */
    private static void assertState(ScoreTracker tracker, int homeScore,
            int awayScore, int homeFouls, int awayFouls, int period) {
        assertEquals(homeScore, tracker.score(Team.HOME));
        assertEquals(awayScore, tracker.score(Team.AWAY));
        assertEquals(homeFouls, tracker.fouls(Team.HOME));
        assertEquals(awayFouls, tracker.fouls(Team.AWAY));
        assertEquals(period, tracker.period());
    }

    @Test
    public void testNoArgConstructor() {
        ScoreTracker tracker = new ScoreTracker1();
        assertState(tracker, 0, 0, 0, 0, 1);
    }

    @Test
    public void testAddPointsHomeThree() {
        ScoreTracker tracker = makeSampleState();

        tracker.addPoints(Team.HOME, 3);

        assertState(tracker, 6, 2, 1, 0, 2);
    }

    @Test
    public void testAddPointsAwayOne() {
        ScoreTracker tracker = makeSampleState();

        tracker.addPoints(Team.AWAY, 1);

        assertState(tracker, 3, 3, 1, 0, 2);
    }

    @Test
    public void testAddFoulHome() {
        ScoreTracker tracker = makeSampleState();

        tracker.addFoul(Team.HOME);

        assertState(tracker, 3, 2, 2, 0, 2);
    }

    @Test
    public void testAddFoulAway() {
        ScoreTracker tracker = makeSampleState();

        tracker.addFoul(Team.AWAY);

        assertState(tracker, 3, 2, 1, 1, 2);
    }

    @Test
    public void testNextPeriod() {
        ScoreTracker tracker = makeSampleState();

        tracker.nextPeriod();

        assertState(tracker, 3, 2, 1, 0, 3);
    }

    @Test
    public void testObserversDoNotMutateState() {
        ScoreTracker tracker = makeSampleState();
        ScoreTracker expected = makeSampleState();

        tracker.score(Team.HOME);
        tracker.score(Team.AWAY);
        tracker.fouls(Team.HOME);
        tracker.fouls(Team.AWAY);
        tracker.period();

        assertEquals(expected, tracker);
    }

    @Test
    public void testClear() {
        ScoreTracker tracker = makeSampleState();

        tracker.clear();

        assertState(tracker, 0, 0, 0, 0, 1);
    }

    @Test
    public void testNewInstanceProducesInitialValue() {
        ScoreTracker tracker = makeSampleState();

        ScoreTracker fresh = tracker.newInstance();

        assertState(fresh, 0, 0, 0, 0, 1);
    }

    @Test
    public void testTransferFrom() {
        ScoreTracker receiver = new ScoreTracker1();
        ScoreTracker source = makeSampleState();

        receiver.transferFrom(source);

        assertState(receiver, 3, 2, 1, 0, 2);
        assertState(source, 0, 0, 0, 0, 1);
    }
}
