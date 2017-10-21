$(function() {
	$("#navbarToggle").blur(function(event) {
		var screenWidth = window.innerWidth;
		if (screenWidth < 768) {
			$("#collapsableNavbar").collapse('hide');
		}
	});
});

function deleteComment(id) {
	var xhttp = new XMLHttpRequest();
	console.log(id);
	xhttp.open("DELETE", "/blogger/1.0/comments/" + id, true);
	xhttp.onload = function() {
		if (xhttp.readyState == 4 && xhttp.status == "200") {
			location.reload();
		} else {
		}
	}
	xhttp.send(null);
}