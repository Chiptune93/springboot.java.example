<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layout/el/include_header.jsp" %>
<div>
    <h1>This is Sample</h1>
    <table>
        <thead>
            <th>user_id</th>
            <th>user_name</th>
            <th>user_age</th>
            <th>user_join_date</th>
        </thead>
        <tbody>
            <c:forEach var="items" items="${result.list}" varStatus="status">
                <tr>
                    <td>${items.userId}</td>
                    <td>${items.userName}</td>
                    <td>${items.userAge}</td>
                    <td>${items.userJoinDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>