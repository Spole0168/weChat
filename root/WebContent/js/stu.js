function addStu(){
	document.getElementById('tempData').style.display="block";//��ʾ myForm
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
		alert("�û�������Ϊ��");
		obj.focus();
		return false;
	}
	obj = document.getElementById('sex'); 
	if (obj.value==""){
		alert("�û��Ա���Ϊ��");
		obj.focus();
		return false;
	}
	obj = document.getElementById('age'); 
	if (obj.value==""){
		alert("�û����䲻��Ϊ��");
		obj.focus();
		return false;
	}
	var obj = document.getElementById('tempData'); 	//div����
	obj.style.display="none";					//��ʾ myForm
	return true;
}