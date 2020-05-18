import java.time.LocalDateTime;

public class TimetableEvent {

    private LocalDateTime startTime;
    private int duration;
    private EventType eventType;

    public TimetableEvent(LocalDateTime startTime, int duration, EventType eventType){
        this.startTime = startTime;
        this.duration = duration;
        this.eventType = eventType;
    }


    enum EventType {
        Lecture,
        Lab,
        Tutorial
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration(){
        return duration;
    }
}
