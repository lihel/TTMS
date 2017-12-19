/**
 * Created by lmy on 17-12-18.
 */
var req;
function getEmployee()
{
    var url = "/user";
    if (window.XMLHttpRequest)
    {
        req = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (req)
    {
        //采用POST方式，异步传输
        req.open("post", url, true);
        //POST方式，必须加入如下头信息设定
        req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        req.onreadystatechange = complete;
        req.send();
    }
}
//回调函数
function complete()
{
    if (req.readyState == 4 && req.status == 200)
    {
        //给返回的json串加上圆括号，转变成json对象:说明eval函数
        var json =  eval("(" + req.responseText + ")");
        document.getElementById("emp_no").innerText=json.emp_no;
        document.getElementById("emp_name").innerText= json.emp_name;
        document.getElementById("emp_sex").innerText=json.emp_sex;
        document.getElementById("emp_tel_num").innerText= json.emp_tel_num;
        document.getElementById("emp_addr").innerText= json.emp_addr;
        document.getElementById("emp_email").innerText= json.emp_email;
    }
}