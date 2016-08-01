var ChatInputView = Backbone.View.extend({
    el: '#chatInputView',
    render: function() {
        var that = this;
        var html = render('chat-input-template');
        that.$el.html(html);        
    },
    events: {
    	"click #send": "send"
    },

    send: function() {
    	var that = this;
    	var msgDetail = new Object();
    	msgDetail.message = $('#msg').val();
    	msgDetail.lastIndex = -1;
    	$('#msg').val("");
    	var msg = new Message();
    	msg.save(msgDetail, {
    		success: function(result) {
    			console.log(result.models);
    			router.navigate('home', {trigger: true});
    		}
    	});
    }
});