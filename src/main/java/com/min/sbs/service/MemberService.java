package com.min.sbs.service;

import org.springframework.stereotype.Service;

import com.min.sbs.dto.Member;
import com.min.sbs.repository.MemberRepository;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {

		Member oldMember = memberRepository.getMemberByLoginId(loginId);

		if (oldMember != null) {
			return -1;
		}
		oldMember = memberRepository.getMemberByNameAndEmail(name, email);

		if (oldMember != null) {
			return -2;
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		return memberRepository.getLastId();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
