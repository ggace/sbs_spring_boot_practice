package com.min.sbs.controller;

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
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
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

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if(joinRd.isFail()) {
			return joinRd;
		}
		
		int id = (int)joinRd.getData();
		
		Member member = memberService.getMemberById(id);

		return ResultData.newData(joinRd, member);

	}
}
