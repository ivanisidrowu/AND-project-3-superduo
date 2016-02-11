package barqsoft.footballscores.service;

import android.database.Cursor;

import barqsoft.footballscores.ScoresAdapter;
import barqsoft.footballscores.Utilies;

/**
 * Created by ivan on 2/11/16.
 */
public class MatchInfo {
    private String homeName;
    private String awayName;
    private String date;
    private String score;

    public MatchInfo() {

    }

    public MatchInfo(String homeName, String awayName, String date, String score) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.date = date;
        this.score = score;
    }

    public MatchInfo fromCursor(Cursor cursor) {
        this.homeName = cursor.getString(ScoresAdapter.COL_HOME);
        this.awayName = cursor.getString(ScoresAdapter.COL_AWAY);
        this.date = cursor.getString(ScoresAdapter.COL_DATE);
        this.score = Utilies.getScores(cursor.getInt(ScoresAdapter.COL_HOME_GOALS), cursor.getInt(ScoresAdapter.COL_AWAY_GOALS));

        return this;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
