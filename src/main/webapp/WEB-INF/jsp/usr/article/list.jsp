<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name } 게시물 페이지" />
<%@include file="../common/header.jspf" %>
<main>
	<section class="mt-5">
		<div class="container mx-auto px-3">
			<div class="flex ">
				게시물 개수 : ${articlesCount }개
				<div class="flex flex-grow"></div>
				<div>
					<form action="" class="flex w-full">
						<input type="hidden" name="boardId" value="${board.id }"/>
						<select class="select select-bordered select-sm m-1 max-w-xs w-full" name="limit" value="10">
							<option onclick="location.replace('http://localhost:8012/usr/article/list?boardId=${board.id}&page=${page }&limit=5')" value="5">5</option>
							<option onclick="location.replace('http://localhost:8012/usr/article/list?boardId=${board.id}&page=${page }&limit=10')"  value="10">10</option>
							<option onclick="location.replace('http://localhost:8012/usr/article/list?boardId=${board.id}&page=${page }&limit=15')"  value="15">15</option>
							<option onclick="location.replace('http://localhost:8012/usr/article/list?boardId=${board.id}&page=${page }&limit=20')"  value="20">20</option>
						</select>
						
					</form>
					
				</div>
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
			
			<div class="flex justify-center">
				
			
				<c:if test="${currentPage != 1 }">
					<a class="btn btn-link btn-xs" href="/usr/article/list?boardId=${board.id }&page=1&limit=${limit}"><<</a>
					<a class="btn btn-link btn-xs" href="/usr/article/list?boardId=${board.id }&page=${currentPage-1}&limit=${limit}"><</a>
				</c:if>
				
				<c:forEach var="i" begin="1" end="${pages }">
					<c:if test="${i >= currentPage-2 && i <= currentPage+2 && i != currentPage }">
						
						<a class="btn btn-link btn-xs" href="/usr/article/list?boardId=${board.id }&page=${i}&limit=${limit}">${i }</a>
					</c:if>
					<c:if test="${i == currentPage }">
						<a class="btn btn-ghost btn-xs border" href="/usr/article/list?boardId=${board.id }&page=${i}&limit=${limit}">${i }</a>
					</c:if>
					
				</c:forEach>
				
				<c:if test="${currentPage != pages }">
					<a class="btn btn-link btn-xs" href="/usr/article/list?boardId=${board.id }&page=${currentPage+1}&limit=${limit}">></a>
					<a class="btn btn-link btn-xs" href="/usr/article/list?boardId=${board.id }&page=${pages}&limit=${limit}">>></a>
				</c:if>
				
				
			</div>
		</div>
	</section>				
</main>

	
	
<%@include file="../common/foot.jspf" %>