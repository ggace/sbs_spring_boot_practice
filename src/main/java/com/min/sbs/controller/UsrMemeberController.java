package com.min.sbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.Member;
import com.min.sbs.service.MemberService;
import com.min.sbs.util.Util;

@Controller
public class UsrMemeberController {
	private MemberService memberService;

	public UsrMemeberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (!Util.isEmpty(loginId)) {
			return "loginId를 입력해주세요";
		}

		if (!Util.isEmpty(loginPw)) {
			return "loginPw를 입력해주세요";
		}

		if (!Util.isEmpty(name)) {
			return "name를 입력해주세요";
		}

		if (!Util.isEmpty(nickname)) {
			return "nickname를 입력해주세요";
		}

		if (!Util.isEmpty(cellphoneNo)) {
			return "cellphoneNo를 입력해주세요";
		}

		if (!Util.isEmpty(email)) {
			return "email를 입력해주세요";
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {
			return Util.format("해당 아이디(%s)는 이미 사용중입니다.", loginId);
		} else if (id == -2) {
			return Util.format("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email);
		}

		return memberService.getMemberById(id);

	}
}
