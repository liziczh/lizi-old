package com.qm.crawler.repository;

import com.qm.crawler.domain.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, String> {

}
