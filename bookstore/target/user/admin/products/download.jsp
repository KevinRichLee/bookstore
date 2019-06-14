<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="al" uri="http://yyf.admin-login-tag"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js">
</script>
</head>
<body>
<al:AdminLogin/>
	<br>
	<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/admin/sales/list.do" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" 
		bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请输入年份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="year" size="15" value="${year}"
									id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请选择月份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="month" id="month">
										<option value="0">--选择月份--</option>
										<option value="1" <c:if test="${month==1}">selected</c:if>>一月</option>
										<option value="2" <c:if test="${month==2}">selected</c:if>>二月</option>
										<option value="3" <c:if test="${month==3}">selected</c:if>>三月</option>
										<option value="4" <c:if test="${month==4}">selected</c:if>>四月</option>
										<option value="5" <c:if test="${month==5}">selected</c:if>>五月</option>
										<option value="6" <c:if test="${month==6}">selected</c:if>>六月</option>
										<option value="7" <c:if test="${month==7}">selected</c:if>>七月</option>
										<option value="8" <c:if test="${month==8}">selected</c:if>>八月</option>
										<option value="9" <c:if test="${month==9}">selected</c:if>>九月</option>
										<option value="10" <c:if test="${month==10}">selected</c:if>>十月</option>
										<option value="11" <c:if test="${month==11}">selected</c:if>>十一月</option>
										<option value="12" <c:if test="${month==12}">selected</c:if>>十二月</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" 
								bgColor="#f5fafe" class="ta_01">
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br><br>
								</td>
								<td align="center" bgColor="#ffffff" class="ta_01">
									<input type="submit" id="search" name="search" value="下载"
									class="button_view"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="重置" class="button_view" />
								</td>
							</tr>
							<table cellspacing="0" cellpadding="1" rules="all"
								   bordercolor="gray" border="1" id="DataGrid1"
								   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="24%">商品编号</td>
								<td align="center" width="18%">商品名称</td>
								<td align="center" width="9%">销售数量</td>
							</tr>

							<c:forEach items="${plist}" var="p">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="200">${p.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${p.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${p.salenum }</td>

								</tr>
							</c:forEach>
							</table>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

