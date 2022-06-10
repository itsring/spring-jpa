package com.example.pro.make.repository;

import com.example.pro.make.entity.Developer;
import com.example.pro.make.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetiredDevRepository extends JpaRepository<RetiredDeveloper, Long> {





}
