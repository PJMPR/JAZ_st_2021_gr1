package pl.pjwstk.lab06.object;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class RepaymentSchedule {
    private long id;
    Map<String,String> schedule;

    public RepaymentSchedule(){
        this.schedule = new HashMap<>();
    }

    public RepaymentSchedule(long id, Map<String, String> schedule) {
        this.id = id;
        this.schedule = schedule;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(String key, String val) {
        this.schedule.put(key+" rata",val);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }
}
