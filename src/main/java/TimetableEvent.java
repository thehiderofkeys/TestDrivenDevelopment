import java.time.LocalDateTime;

public class TimetableEvent {

    private LocalDateTime startTime;
    private int duration;

    public TimetableEvent(LocalDateTime startTime, int duration){
        this.startTime = startTime;
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration(){
        return duration;
    }
}
