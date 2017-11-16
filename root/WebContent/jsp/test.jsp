<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>DWRtest</title>
		<script type='text/javascript' src='dwr/interface/TestHello.js'></script> 
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript">
			function testH(){
				var user = document.getElementById('user').value;
				alert(user);
				TestHello.sayHello(user,callback);
			}
			function callback(msg){
				DWRUtil.setValue('result',msg);
			}
		</script>
		
	</head>
	<body>
		<input id="user" type="text" />
		<input type='button' value='AAAA' onclick='testH()' />
		<div id="result"></div>
	</body>
</html>