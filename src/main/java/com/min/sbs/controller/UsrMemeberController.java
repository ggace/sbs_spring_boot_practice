package com.min.sbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.Member;
import com.min.sbs.dto.ResultData;
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
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Util.isEmpty(loginId)) {
			return ResultData.from("F-A", "loginId를 입력해주세요");
		}

		if (Util.isEmpty(loginPw)) {
			return ResultData.from("F-B", "loginPw를 입력해주세요");
		}

		if (Util.isEmpty(name)) {
			return ResultData.from("F-C", "name를 입력해주세요");
		}

		if (Util.isEmpty(nickname)) {
			return ResultData.from("F-D", "nickname를 입력해주세요");
		}

		if (Util.isEmpty(cellphoneNo)) {
			return ResultData.from("F-E", "cellphoneNo를 입력해주세요");
		}

		if (Util.isEmpty(email)) {
			return ResultData.from("F-F", "email를 입력해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if(joinRd.isFail()) {
			return (ResultData)joinRd;
		}
		
		int id = joinRd.getData();
		
		Member member = memberService.getMemberById(id);

		return ResultData.newData(joinRd, member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw, HttpSession session) {
		if (Util.isEmpty(loginId)) {
			return ResultData.from("F-A", "loginId를 입력해주세요");
		}

		if (Util.isEmpty(loginPw)) {
			return ResultData.from("F-B", "loginPw를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			session.setAttribute("loginedMemberId ", null);
			return ResultData.from("F-1", "해당 회원이 존재하지 않습니다.");
		}
		if(!(member.getLoginPw().equals(loginPw))) {
			session.setAttribute("loginedMemberId ", null);
			return ResultData.from("F-2", "비밀번호가 일치하지 않습니다.");
		}
		
		session.setAttribute("loginedMemberId ", member.getId());
		return ResultData.from("S-1", Util.format("%s님 환영합니다.", loginId), member.getId());
	}
}
