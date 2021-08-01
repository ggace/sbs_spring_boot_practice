package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.MemberDao;
import com.min.sbs.dto.Member;

@Service
public class MemberService {
	private MemberDao memberDao;

	public MemberService(MemberDao memberRepository) {
		this.memberDao = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {

		Member oldMember = memberDao.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return -1;
		}
		oldMember = memberDao.getMemberByNameAndEmail(name, email);

		if (oldMember != null) {
			return -2;
		}

		memberDao.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		return memberDao.getLastId();
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

}
