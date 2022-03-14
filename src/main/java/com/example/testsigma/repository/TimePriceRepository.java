package com.example.testsigma.repository;

import com.example.testsigma.model.TimePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TimePriceRepository extends JpaRepository<TimePrice, Long> {

    @Transactional
    void deleteByCarNumber(String carNumber);

    List<TimePrice> findAllByCarNumber(String carNumber);
}
