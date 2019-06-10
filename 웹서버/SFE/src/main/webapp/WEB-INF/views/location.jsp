<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  http-equiv= "Refresh" content="1;" URL="http://localhost:8080/SFE/location" -->
<title>사용자 현재 위치</title>

<style>
			div { text-align: center; }
		</style>
</head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<div class="container1">
 
		<h1> L O C A  T I O N </h1>
		
<script>
	var canvas, context;
	
	var fireSensorSize = ${fireSensors.size()};	
	
	var userList = new Array();
	var userX = new Array();
	var userY = new Array();
	
	var overlapList = new Array();
	
	var fireImage = new Array();
	var check = false;
	var overCount1 = 0;
	var overCount2 = 0;

	function init() {
		canvas = document.getElementById("myCanvas");
		context = canvas.getContext("2d");
			
		var image = new Image();
		image.src = "resources/images/location.jpg";
		image.onload = function() {
			context.drawImage(image, 0, 0, 700, 700);
	
			<c:forEach var="fireSensor" items="${fireSensors}">			
				fireImage.push(new Image());
				
				if("${fireSensor.sensorValue}" == "on"){	
					check = true;
					fireImage[parseInt("${fireSensor.id}")].onload = function() {
						context.drawImage(fireImage[parseInt("${fireSensor.id}")], parseInt("${fireSensor.sensorX}"), parseInt("${fireSensor.sensorY}"), 30, 50);
					}
					fireImage[parseInt("${fireSensor.id}")].src = "resources/images/fire.png";
				}
			</c:forEach>
		
			<c:forEach var="overlap" items="${overlapUsers}">
				if(parseInt("${overlap.count}") >= 5){
					overlapList.push(parseInt("${overlap.node}"));
				}
			</c:forEach>
			for(var i = 0; i < overlapList.length; i++){
				console.log("overlap: " + overlapList[i]);
			}
			
			context.beginPath();//1/2
			context.lineWidth =15;
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++){
				if(overlapList[i] == 1 || overlapList[i] == 2)
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(170, 168);
			context.lineTo(200, 168);
			context.stroke();
		
			context.beginPath();//3
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 3){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(200, 168);
			context.lineTo(240, 168);
			context.stroke();
			context.beginPath();
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 3){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(220, 168);
			context.lineTo(220, 200);
			context.stroke();
			
			
			context.beginPath();//4
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 4){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(240, 168);
			context.lineTo(300, 168);
			context.stroke();
			
			context.beginPath();//5
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 5){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(300, 168);
			context.lineTo(350, 168);
			context.stroke();
			
			
			context.beginPath();//6
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 6){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(350, 168);
			context.lineTo(420, 168);
			context.stroke();
			
			
			context.beginPath();//7
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 7){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(420, 168);
			context.lineTo(500, 168);
			context.stroke();
		
		
			context.beginPath();//8
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 8){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(220, 200);
			context.lineTo(220, 260);
			context.stroke();
			
			context.beginPath();//9
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 9){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(220, 260);
			context.lineTo(220, 320);
			context.stroke();
			
			
			context.beginPath();//10
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 10){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(220, 320);
			context.lineTo(220, 390);
			context.stroke();
			
			
			context.beginPath();//11
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 11){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(220, 390);
			context.lineTo(220, 415);
			context.stroke();
			context.beginPath();
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 11){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(212, 415);
			context.lineTo(250, 415);
			context.stroke();
			
			context.beginPath();//12
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 12){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(250, 415);
			context.lineTo(290, 415);
			context.stroke();
			context.beginPath();
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 12){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(270, 415);
			context.lineTo(270, 440);
			context.stroke();
			
			context.beginPath();//13
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 13){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(290, 415);
			context.lineTo(310, 415);
			context.stroke();
			
			context.beginPath();//14
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 14){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(270, 440);
			context.lineTo(270, 480);
			context.stroke();
			
			context.beginPath();//15
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 15){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(270, 480);
			context.lineTo(270, 580);
			context.stroke();
			
			
			context.beginPath();//16
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 16){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(270, 580);
			context.lineTo(270, 640);
			context.stroke();
			context.beginPath();
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 16){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(320, 640);
			context.lineTo(230, 640);
			context.stroke();
			
			context.beginPath();//18
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 18){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(230, 640);
			context.lineTo(180, 640);
			context.stroke();
			
			context.beginPath();//17
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 17){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(180, 640);
			context.lineTo(100, 640);
			context.stroke();
			
			context.beginPath();//19
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 19){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(320, 640);
			context.lineTo(440, 640);
			context.stroke();
			
			context.beginPath();//20
			context.strokeStyle = '#00FF00';
			for(var i = 0; i < overlapList.length; i++)
				if(overlapList[i] == 20){
					context.strokeStyle = '#FF0000';				
			}
			context.moveTo(440, 640);
			context.lineTo(535, 640);
			context.stroke();
			///////////////////////////////////////
			/*
			context.beginPath();
			context.arc(700, 150, 8, 0, Math.PI * 2);
			context.fillStyle = '#00FF00';
			context.fill();
			context.stroke();
			
			context.font = "15px Comic Sans MS";
			context.fillStyle = '#00FF00';
			context.fillText("1~2명", 730, 155); 
			
			context.beginPath();
			context.arc(700, 180, 8, 0, Math.PI * 2);
			context.fillStyle = '#FFBB00';
			context.fill();
			context.stroke();
			
			context.font = "15px Comic Sans MS";
			context.fillStyle = '#FFBB00';
			context.fillText("3~4명", 730, 185); 
			
			context.beginPath();
			context.arc(700, 210, 8, 0, Math.PI * 2);
			context.fillStyle = '#FF0000';
			context.fill();
			context.stroke();
	
			context.font = "15px Comic Sans MS";
			context.fillStyle = '#FF0000';
			context.fillText("5명 이상", 730, 215); 
			
			*/
			if(check){
				alert("\n화재가 발생 하였습니다.\n신속한 대피를 안내하세요.");	
			}
		}
	}
	
	
	
	
</script>
	</div>


<body onload= "init()">
<br />
<div class="container1">
	<form action="<c:url value="/fireReset"/>" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn btn-dark">화재 초기화</button>
	</form>
	<br/>
	<form action="<c:url value="/randomLocation"/>" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn btn-dark">유저 위치 랜덤</button>
	</form>
	<canvas id="myCanvas" width="850" height="750">
</canvas>
</div>

</body>
</html>