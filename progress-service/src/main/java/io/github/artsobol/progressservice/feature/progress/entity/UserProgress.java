package io.github.artsobol.progressservice.feature.progress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_progress")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProgress {

    @Id
    @Getter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Getter
    @Column(name = "total_workouts", nullable = false)
    private int totalWorkouts;

    @Getter
    @Column(name = "current_streak", nullable = false)
    private int currentStreak;

    @Getter
    @Column(name = "longest_streak", nullable = false)
    private int longestStreak;

    @Getter
    @Column(name = "last_workout_date")
    private LocalDate lastWorkoutDate;

    public static UserProgress create(Long userId) {
        UserProgress entity = new UserProgress();
        entity.userId = userId;
        return entity;
    }

    public void recordWorkoutCompleted(LocalDate completedDate) {
        if (completedDate == null) {
            throw new IllegalArgumentException("completedDate must not be null");
        }

        totalWorkouts++;

        if (lastWorkoutDate == null) {
            currentStreak = 1;
        } else if (lastWorkoutDate.equals(completedDate)) {
            currentStreak = Math.max(currentStreak, 1);
        } else if (lastWorkoutDate.plusDays(1).equals(completedDate)) {
            currentStreak++;
        } else if (completedDate.isAfter(lastWorkoutDate)) {
            currentStreak = 1;
        }

        longestStreak = Math.max(longestStreak, currentStreak);
        if (lastWorkoutDate == null || completedDate.isAfter(lastWorkoutDate)) {
            lastWorkoutDate = completedDate;
        }
    }
}
