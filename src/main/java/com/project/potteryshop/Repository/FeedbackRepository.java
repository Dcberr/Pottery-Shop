package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

}
