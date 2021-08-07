<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id}번 상세 페이지" />
<%@include file="../common/header.jspf" %>

	<section class="mt-5">
		<div class="flex container mx-auto div-table-type-1 flex-col">
			<div class="flex">
				<p>제목</p>
				<p class="break-all">${article.title}</p>
			</div>
			
			<div class="flex">
				<p>작성날짜</p>
				<p class="break-all">${article.regDate.substring(2,16)}</p>
			</div>
			
			<div class="flex">
				<p>수정날짜</p>
				<p class="break-all">${article.updateDate.substring(2,16)}</p>
			</div>
			
			<div class="flex">
				<p>작성자</p>
				<p class="break-all">${article.memberId}</p>
			</div>
			<div class="flex">
				<p>내용</p>
				<p class="break-all">${article.body}</p>
			</div>
		</div>
		
		
	</section>
	<%String error = (String)request.getAttribute("errors"); %>
	<%if(error != null  && !error.equals("")){ %>
		<script type="text/javascript">
			alert("${errors}")
		</script>
	<%} %>
<%@include file="../common/foot.jspf" %>