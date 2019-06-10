<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Fire SensorValue</h2>
		<p>센서 값 현황</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>sensorId</th>
					<th>sensorValue</th>
	
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fireSensor" items="${fireSensors}">
					<tr>
						<td>${fireSensor.id}</td>
						<td>${fireSensor.sensorValue}</td>
						
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

</div>


