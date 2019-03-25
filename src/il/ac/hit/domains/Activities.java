package il.ac.hit.domains;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Mapping Activities Table to JAVA object
 */
@Entity
@Table(name = "Activities")
public class Activities implements Serializable {
    /**
     * Instantiates a new Activity
     * @params activityName the name&id
     * @params calories How many calories is 1 set of the activity
     */
    @Id
    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "CALORIES_PER_SET")
    private long calories;

    public Activities(String activityName,long calories) {
        setActivityName(activityName);
        setCalories(calories);
    }

    public Activities() {

    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityId) {
        this.activityName = activityId;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }
    @Override
    public String toString() {
        return "Activities{" +
                "activityName='" + activityName + '\'' +
                '}';
    }

}
