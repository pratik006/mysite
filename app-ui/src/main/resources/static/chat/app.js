user = {};
var lastIndex = -1;
refreshTime = 10000;
$(document).ready(function() {
  $('#login').click(function() {
    var data = $('#loginForm').serialize();
    console.log(data);
    $.ajax({
      url: '/rest/blog/login',
      data: data,
      dataType: "json",
      type: "POST",
      success: function(result) {
        console.log(result);
        user = result;
        $('.viewport').load('./chat-template.html');
        $('#refresh').click();
        
      }
    });
  });
  //$(vewport').load('./chat-template.html');

});