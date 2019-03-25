package il.ac.hit.domains;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Mapping UsersActivities Table to JAVA object
 */
@Entity
@Table(name = "UsersActivities")
public class UsersActivities implements Serializable {

    /**
     * Instantiates a new UsersActivities
     * @params id id
     * @params activityName Activity name
     * @params repeats sets/reapets
     * @params calories total calories per the activity
     */

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "ACTIVITY_NAME")
    private String activityName;
    @Column(name = "REPEATS")
    private int repeats;
    @Column(name = "CALORIES")
    private long calories;

    public UsersActivities(String activityName, int repeats,long calories) {
       setActivityName(activityName);
       setRepeats(repeats);
       setCalories(calories);
    }
    public UsersActivities(){};
    public UsersActivities(String activityName, int repeats) {
        this.activityName = activityName;
        this.repeats = repeats;
        this.calories = 0;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }
}
