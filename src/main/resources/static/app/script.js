
$(function () { 
  $("#navbarToggle").blur(function (event) {
    var screenWidth = window.innerWidth;
    if (screenWidth < 768) {
      $("#collapsableNavbar").collapse('hide');
    }
  });
});

function deleteComment(id)
{
	var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/blogger/1.0/comments/" + id, true);
    xhttp.send();
    location.reload();
}