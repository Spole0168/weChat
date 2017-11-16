function addStu(){
	document.getElementById('tempData').style.display="block";//显示 myForm
	document.getElementById('myForm').action = "insert.do";
}
function resetValue(){
	if(document.getElementById('myForm').action=='insert.do'){
		document.myForm.elements["id"].value 	= '';
		document.myForm.elements["name"].value 	= '';
		document.myForm.elements["sex"].value 	= '';
		document.myForm.elements["age"].value 	= '';
	}else if(document.getElementById('myForm').action=='pre4Update.do'){
		document.myForm.elements["id"].value 	= 	obj.id;
		document.myForm.elements["name"].value 	= 	obj.name;
		document.myForm.elements["sex"].value 	= 	obj.sex;
		document.myForm.elements["age"].value 	= 	obj.age;
	}
}
function checkform(){
	var obj = document.getElementById('name'); 
	if (obj.value==""){
		alert("用户名不能为空");
		obj.focus();
		return false;
	}
	obj = document.getElementById('sex'); 
	if (obj.value==""){
		alert("用户性别不能为空");
		obj.focus();
		return false;
	}
	obj = document.getElementById('age'); 
	if (obj.value==""){
		alert("用户年龄不能为空");
		obj.focus();
		return false;
	}
	var obj = document.getElementById('tempData'); 	//div对象
	obj.style.display="none";					//显示 myForm
	return true;
}