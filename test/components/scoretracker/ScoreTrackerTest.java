package components.scoretracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.scoretracker.ScoreTrackerKernel.Team;

/**
 * JUnit tests for secondary methods in {@link ScoreTrackerSecondary}.
 */
public final class ScoreTrackerTest {

    /**
     * Constructs a fresh {@link ScoreTracker} for each test.
     *
     * @return new tracker
     */
    private static ScoreTracker constructorTest() {
        return new ScoreTracker1();
    }

    @Test
    public void testAddFreeThrow() {
        ScoreTracker tracker = constructorTest();

        tracker.addFreeThrow(Team.HOME);

        assertEquals(1, tracker.score(Team.HOME));
        assertEquals(0, tracker.score(Team.AWAY));
        assertEquals(1, tracker.period());
    }

    @Test
    public void testAddTwoPointer() {
        ScoreTracker tracker = constructorTest();

        tracker.addTwoPointer(Team.AWAY);

        assertEquals(0, tracker.score(Team.HOME));
        assertEquals(2, tracker.score(Team.AWAY));
    }

    @Test
    public void testAddThreePointer() {
        ScoreTracker tracker = constructorTest();

        tracker.addThreePointer(Team.HOME);

        assertEquals(3, tracker.score(Team.HOME));
        assertEquals(0, tracker.score(Team.AWAY));
    }

    @Test
    public void testIsTieTrueAtStart() {
        ScoreTracker tracker = constructorTest();

        assertTrue(tracker.isTie());
    }

    @Test
    public void testIsTieFalseAfterScoring() {
        ScoreTracker tracker = constructorTest();
        tracker.addTwoPointer(Team.HOME);

        assertFalse(tracker.isTie());
    }

    @Test
    public void testLeaderHome() {
        ScoreTracker tracker = constructorTest();
        tracker.addThreePointer(Team.HOME);

        assertEquals(Team.HOME, tracker.leader());
    }

    @Test
    public void testLeaderAway() {
        ScoreTracker tracker = constructorTest();
        tracker.addTwoPointer(Team.AWAY);

        assertEquals(Team.AWAY, tracker.leader());
    }

    @Test
    public void testResetGame() {
        ScoreTracker tracker = constructorTest();
        tracker.addThreePointer(Team.HOME);
        tracker.addFoul(Team.AWAY);
        tracker.nextPeriod();

        tracker.resetGame();

        assertEquals(0, tracker.score(Team.HOME));
        assertEquals(0, tracker.score(Team.AWAY));
        assertEquals(0, tracker.fouls(Team.HOME));
        assertEquals(0, tracker.fouls(Team.AWAY));
        assertEquals(1, tracker.period());
    }

    @Test
    public void testToString() {
        ScoreTracker tracker = constructorTest();
        tracker.addTwoPointer(Team.HOME);
        tracker.addFoul(Team.AWAY);

        String expected = "HOME 2 - AWAY 0 | Fouls(H/A): 0/1 | Period 1";
        assertEquals(expected, tracker.toString());
    }

    @Test
    public void testEqualsAndHashCodeEqualObjects() {
        ScoreTracker tracker1 = constructorTest();
        ScoreTracker tracker2 = constructorTest();
        tracker1.addThreePointer(Team.HOME);
        tracker2.addThreePointer(Team.HOME);
        tracker1.addFoul(Team.AWAY);
        tracker2.addFoul(Team.AWAY);

        assertTrue(tracker1.equals(tracker2));
        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }

    @Test
    public void testEqualsDifferentObjects() {
        ScoreTracker tracker1 = constructorTest();
        ScoreTracker tracker2 = constructorTest();
        tracker1.addThreePointer(Team.HOME);
        tracker2.addThreePointer(Team.AWAY);

        assertFalse(tracker1.equals(tracker2));
    }
}
