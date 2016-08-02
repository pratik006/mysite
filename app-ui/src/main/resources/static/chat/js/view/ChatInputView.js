var ChatInputView = Backbone.View.extend({
    el: '#chatInputView',
    collection: messages,
    render: function() {
        var that = this;
        var html = render('chat-input-template');
        that.$el.html(html);        
    },
    events: {
    	"click #send": "send",
        "keyup #msg": "onKeyUp",
    },

    send: function() {
    	var that = this;
    	var msgDetail = new Object();
    	msgDetail.message = $('#msg').val();
    	msgDetail.lastIndex = App.Context.getValue(1);
    	msgDetail.threadId=1;
    	$('#msg').val("");
        this.collection.create(msgDetail);
    },
    onKeyUp: function(e) {
        if(e.keyCode == 13) {
            this.send();
        }
    }
});