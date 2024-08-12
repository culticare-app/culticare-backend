package com.culticare.information.repository;

import com.culticare.information.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {


}
