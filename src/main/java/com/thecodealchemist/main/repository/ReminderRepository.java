package com.thecodealchemist.main.repository;

import com.thecodealchemist.main.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
