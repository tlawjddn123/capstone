<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="container-wrapper">
	<div class="container">
		<font size="6em" color="green"><strong>S</strong>afe <strong>F</strong>ire <strong>E</strong>xit</font>
		<h3><strong>L O G I N</strong></h3>
		
		
		<p>　</p>
		<c:if test="${not empty errorMsg}">
			<div style="color:#ff0000;"><h3>${errorMsg}</h3></div>
		</c:if>
		
		<c:if test="${not empty logoutMsg}">
			<div style="color:#0000ff;"><h3>${logoutMsg}</h3></div>
		</c:if>
		
		 <p> </p>
		<form action="<c:url value="/login"/>" method="post">
			<div class="form-group">
				<label for="username">ID  : </label>
				<input type="text"  id="username" placeholder=" 아이디"
					name="username" style="width:200px;height:45px; ">
			</div>
			
			<div class="form-group">
				<label for="password">PW : </label>
				<input type="password" id="pwd" placeholder=" 비밀번호"
					name="password" style="width:200px;height:45px; ">
			</div>
			 
			 
			 
			 
			<br><br>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button type="submit"style="width:200pt;height:60pt;font-size:20pt;" class="btn btn-dark"> 로그인 </button>
			
		</form>
	</div>
</div>
</body>
</html>



