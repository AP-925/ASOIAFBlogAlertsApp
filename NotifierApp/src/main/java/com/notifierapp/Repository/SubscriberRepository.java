package com.notifierapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notifierapp.Entity.SubscriberVO;

public interface SubscriberRepository extends JpaRepository<SubscriberVO, String> {

	public boolean existsByEmailID(String emailID);

	public List<SubscriberVO> findByEmailID(String emailID);
}
