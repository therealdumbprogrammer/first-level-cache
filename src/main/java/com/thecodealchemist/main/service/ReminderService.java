package com.thecodealchemist.main.service;

import com.thecodealchemist.main.entity.Reminder;
import com.thecodealchemist.main.repository.ReminderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void firstLevelCacheWithRepository() {
        System.out.println("==== First call ========");
        Reminder r1 = reminderRepository.findById(Long.valueOf(1)).get();
        System.out.println(r1);

        System.out.println("==== Second call ========");
//        Reminder r2 = reminderRepository.findById(Long.valueOf(1)).get();
//        System.out.println(r2);
        r1.setTitle("ToDO - something -1");
    }

    public void cacheDemoWithFirstLevelCacheUsingEntityManager() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager(); // Hibernate session
        entityManager1.getTransaction().begin();
        Reminder r1 = entityManager1.find(Reminder.class, Long.valueOf(1));
        System.out.println("First call::::: " + r1);
        entityManager1.getTransaction().commit();

        System.out.println("===============");

        entityManager1.getTransaction().begin();
        Reminder r2 = entityManager1.find(Reminder.class, Long.valueOf(1));
        System.out.println("Second call::::: " + r2);
        entityManager1.getTransaction().commit();
        entityManager1.close();
    }
    public void cacheDemo() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager(); // Hibernate session 1
        entityManager1.getTransaction().begin();
        Reminder r1 = entityManager1.find(Reminder.class, Long.valueOf(1));
        System.out.println("First call::::: " + r1);
        entityManager1.getTransaction().commit();
        entityManager1.close();

        System.out.println("===============");

        EntityManager entityManager2 = entityManagerFactory.createEntityManager(); // Hibernate session 2
        entityManager2.getTransaction().begin();
        Reminder r2 = entityManager2.find(Reminder.class, Long.valueOf(1));
        System.out.println("Second call::::: " + r2);
        entityManager2.getTransaction().commit();
        entityManager2.close();
    }
}
