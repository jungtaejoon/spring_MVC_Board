<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="reply" action="reply" method="post">
		<table width="500" cellpadding="0" cellspacing="0" border="1"> 	

		
			<tr>
				<td>번호</td>
				<td>${reply_view.bId }<input type="hidden" name="bId" value="${reply_view.bId }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${reply_view.bName }<input type="hidden" name="bName" value="${reply_view.bName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="re) ${reply_view.bTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="bContent" >${reply_view.bContent }
				 ----------</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="javascript:reply.submit();">확인</a> <a href="content_view?bId=${reply_view.bId}">취소</a>
					<input type="hidden" name="bGroup" value="${reply_view.bGroup }">
					<input type="hidden" name="bStep" value="${reply_view.bStep }">
					<input type="hidden" name="bIndent" value="${reply_view.bIndent }">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>