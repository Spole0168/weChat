function testHello(){
	var user = document.getElementById('user').value;
	alert(user);
	TestHello.sayHello(user,callback);
}
function callback(msg){
	DWRUtil.setValue('result',msg);
}