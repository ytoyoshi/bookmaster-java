package com.example.helloService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.helloService.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	Optional<Message> findFirstByLanguageCodeOrderByIdDesc(String languageCode);
}