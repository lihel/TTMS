/**
 * Created by lmy on 17-12-17.
 */
'use strict';
function get_user_message() {
    let top = document.getElementById("headImg");
    if (user != null) {
        let head = document.createElement('img');
        if (user.head_path == null) {
            head.setAttribute("src", "/view/images/user.jpg");
        } else {
            head.setAttribute('src', "/view"+user.head_path);
        }
        top.appendChild(head);
    } else {
        window.location.href = '/index.jsp';
    }
}