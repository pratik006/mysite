var LoginView = Backbone.View.extend({
    el: '.row',
    render: function() {
        console.log('render login-view');
        var html = render('login-template');
        this.$el.html(html);
    },
    events: {
    	"click #login": "signIn",
    },
    signIn: function() {
    	$.ajax({
	      type: "POST",
	      url: '/rest/blog/login',
	      data: $('#loginForm').serialize(),
	      success: function(result) {
	        //try {
	          userDetails = JSON.parse(result);
	          App.Context.setValue('user', userDetails.user);
	          //window.location.href = "./index.html";
	          Backbone.history.loadUrl(Backbone.history.fragment);
	        /*}catch(error) {
	          console.log('error occurred while logging in');
	          console.log(error);
	        }*/
	      }
	    });
    }
});