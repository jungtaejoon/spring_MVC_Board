<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="modify" action="modify" method="post">
		<table width="500" cellpadding="0" cellspacing="0" border="1"> 	

		
			<tr>
				<td>번호</td>
				<td>${modify_view.bId }<input type="hidden" name="bId" value="${modify_view.bId }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${modify_view.bName }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${modify_view.bTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="bContent" >${modify_view.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"> <a href="javascript:modify.submit();">확인</a> <a href="content_view?bId=${modify_view.bId}">취소</a> <a href="delete?bId=${content_view.bId }">지우기</a></td>
			</tr>
		</table>
	</form>

</body>
</html>