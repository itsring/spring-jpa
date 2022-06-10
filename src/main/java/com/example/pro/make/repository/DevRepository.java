package com.example.pro.make.repository;

import com.example.pro.make.entity.Developer;
import com.example.pro.make.entity.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevRepository extends JpaRepository<Developer, Long> {


    Optional<Developer> findByMemberId(String memberId);
    List<Developer> findDevelopersStatusCodeEqaulsBy(StatusCode statusCode);


}
