package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.MessageContent;

@Repository
public interface MessageContentRepository extends JpaRepository<MessageContent, String> {

}
