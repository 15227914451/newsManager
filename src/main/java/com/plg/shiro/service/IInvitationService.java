package com.plg.shiro.service;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.Invitation;

public interface IInvitationService {

	List<Invitation> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	Invitation findOne(Integer valueOf);

	void update(Invitation invitation);

	void save(Invitation invitation);

	void delete(Invitation findOne);

}
