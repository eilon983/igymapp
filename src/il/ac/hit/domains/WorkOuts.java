package il.ac.hit.domains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * Mapping WorkOuts Table to JAVA object
 */
@Entity
@Table(name = "Workouts")
public class WorkOuts implements Serializable {

    private static final long serialVersionUID = 8268800253932817168L;

    /**
     * Instantiates a new UsersActivities
     * @params id id
     * @params userId User id
     * @params workOutDate date of workout
     * @params calories total calories per the workout
     */
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long workourId;

    @Column(name = "WORKOUT_DATE")
    private Date workOutDate;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "WORKOUT_NAME")
    private String workoutName;

    @Column(name = "CALORIES")
    private long calories;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "Workouts_UsersActivities",
            joinColumns = { @JoinColumn(name = "Workouts_id") },
            inverseJoinColumns = { @JoinColumn(name = "activitiesList_id") }
    )
    private List<UsersActivities> activitiesList;

    public WorkOuts(String userId,String name,ArrayList<UsersActivities> arr,long calories) {
        this.workOutDate = new Date();
      setUserId(userId);
      setWorkoutName(name);
      setActivitiesList(arr);
      setCalories(calories);
    }
    public WorkOuts(){};

    public Long getRegionId() {
        return workourId;
    }

    public void setRegionId(Long workourId) {
        this.workourId = workourId;
    }

    public Date getWorkOutDate() {
        return workOutDate;
    }

    public void setWorkOutDate(Date workOutDate) {
        this.workOutDate = workOutDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UsersActivities> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<UsersActivities> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Long getWorkourId() {
        return workourId;
    }

    public void setWorkourId(Long workourId) {
        this.workourId = workourId;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "WorkOuts{" +
                "workourId=" + workourId +
                ", workOutDate=" + workOutDate +
                ", userId='" + userId + '\'' +
                ", workoutName='" + workoutName + '\'' +
                ", calories=" + calories +
                ", activitiesList=" + activitiesList +
                '}';
    }
}