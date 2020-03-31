package com.plg.shiro.dao;

import java.util.List;
import java.util.Map;

import com.plg.shiro.entity.Invitation;

public interface InvitationMapper {

	List<Invitation> findAllList(Map<String, Object> param);

	Integer findSize(Map<String, Object> param);

	Invitation findOne(Integer id);

	void update(Invitation invitation);

	Integer insert(Invitation invitation);

	void delete(Invitation invitation);

}
