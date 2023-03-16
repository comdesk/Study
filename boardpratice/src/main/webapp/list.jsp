<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록 보기</title>
</head>

<body>
    <h3>/board/list.jsp</h3>
    <hr>

    <h1>게시판 목록 보기</h1>

    <table border="1">
        <tr>
            <td colspan="5">
                <form action="/board/search.do">
                    <select name="searchName" size="1">
                        <option value="author">작성자</option>
                        <option value="title">글제목</option>
                    </select>
                    <input type="text" name="searchValue">
                    <input type="submit" value="찾기">
                </form>
            </td>
        </tr>

        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>
        </tr>

        <c:forEach var="dto" items="${list}">
            <tr>
                <td>${dto.num}</td>
                <td>
                    <c:forEach begin="1" end="${dto.repIndent}">
                    	&nbsp;&nbsp;
                    </c:forEach>
                    <a href="/board/retrieve.do?num=${dto.num}">${dto.title}</a>
                </td>
                <td>${dto.author}</td>
                <td>${dto.writeday}</td>
                <td>${dto.readcnt}</td>
            </tr>
        </c:forEach>
    </table>

    <a href="/board/list.do">목록보기</a>

</body>

</html>