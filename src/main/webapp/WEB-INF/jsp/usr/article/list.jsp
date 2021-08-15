<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name } 게시물 페이지" />
<%@include file="../common/header.jspf" %>
<main>
	<section class="mt-5">
		<div class="container mx-auto px-3">
			<div>
				게시물 개수 : ${articlesCount }개
			</div>
			<div class="table-box-type-1">
				<table>
					<colgroup>
						<col width="50"/>
						<col width="150"/>
						<col width="150"/>
						<col width="150"/>
						<col />
					</colgroup>
					
					
					<thread>
						<tr>
							<th>번호</th>
							<th>작성날짜</th>
							<th>수정날짜</th>
							<th>작성자</th>
							<th>제목</th>
						</tr>
					</thread>
					
					<tbody>
						<c:forEach var="article" items="${articles}">
							<tr>
								<td>${article.id }</td>
								<td>${article.regDateForPrint }</td>
								<td>${article.updateDateForPrint }</td>
								<td>${article.extra__writerName }</td>
								<td>
									<a class="btn btn-link btn-sm" href="/usr/article/detail?id=${article.id }">${article.title }</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="mt-2 flex justify-end">
				
				<a class="btn btn-link btn-sm" href="/usr/article/write">글쓰기</a>
			</div>
		</div>
	</section>				
</main>

	
	
<%@include file="../common/foot.jspf" %>