package events.tgh2019.painauxraisins.taskmanager;


import events.tgh2019.painauxraisins.library.data.IEvent;
import java.util.Calendar;

/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 * Modified by Takumi Doi on 09/20/2019
 */
public class Event implements IEvent {

    // タスク状態 - open: 未着手、inProgress: 開始（仕掛り）、closed: 完了
    private enum EventStatus {open, inProgress, closed};

    private long mId;
    private Calendar mStartTime;
    private Calendar mEndTime;
    private String mName;
    private String mLocation;
    private int mColor;
    private Calendar mActualStartTime; // 実績開始時刻
    private Calendar mActualEndTime; // 実績終了時刻
    private float severity; // 重要度
    private EventStatus status = EventStatus.open;
    private int assessment = 0; //充実度自己評価



    public Event() {

    }

    public Event(long mId, Calendar mStartTime, Calendar mEndTime, String mName, String mLocation,
                 int mColor) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mName = mName;
        this.mLocation = mLocation;
        this.mColor = mColor;
    }

    public Event(long mId, Calendar mStartTime,
                 Calendar mEndTime, String mName, String mLocation, float severity,
                 int mColor) {
        this.mId = mId;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mName = mName;
        this.mLocation = mLocation;
        this.mColor = mColor;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public Calendar getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Calendar startTime) {
        this.mStartTime = startTime;
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Calendar endTime) {
        this.mEndTime = endTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public Calendar getActualStartTime() {
        return mActualStartTime;
    }

    public void setActualStartTime(Calendar mActualStartTime) {
        this.mActualStartTime = mActualStartTime;
    }

    public Calendar getActualEndTime() {
        return mActualEndTime;
    }

    public void setActualEndTime(Calendar mActualEndTime) {
        this.mActualEndTime = mActualEndTime;
    }

    public float getSeverity() {
        return severity;
    }

    public void setSeverity(float severity) {
        this.severity = severity;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }
}
