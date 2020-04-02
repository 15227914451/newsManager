package com.plg.shiro.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.plg.shiro.dao.InvitationMapper;
import com.plg.shiro.entity.Invitation;
import com.plg.shiro.service.IInvitationService;
@Service
public class InvitationService  implements IInvitationService{
	@Resource
	private InvitationMapper invitationMapper;
	@Override
	public List<Invitation> findAllList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return invitationMapper.findAllList(param);
	}

	@Override
	public Integer findSize(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return invitationMapper.findSize(param);
	}

	@Override
	public Invitation findOne(Integer id) {
		// TODO Auto-generated method stub
		return invitationMapper.findOne(id);
	}

	@Override
	public void update(Invitation invitation) {
		// TODO Auto-generated method stub
		invitationMapper.update(invitation);
	}

	@Override
	public void save(Invitation invitation) {
		// TODO Auto-generated method stub
		Integer id = invitationMapper.insert(invitation);
	}

	@Override
	public void delete(Invitation invitation) {
		// TODO Auto-generated method stub
		invitationMapper.delete(invitation);
	}

	@Override
	public List<Invitation> findByRate() {
		return invitationMapper.findByRate();
	}

	@Override
	public List<Invitation> findAllSize() {
		// TODO Auto-generated method stub
		return invitationMapper.selectAll();
	}

}
