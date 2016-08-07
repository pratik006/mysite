var ChatInputView = Backbone.View.extend({
    el: '#chatInputView',
    collection: messages,
    threadId: '',
    initialize: function(threadId) {
        this.threadId = threadId;
        console.log('init chat input '+threadId);
    },
    render: function() {
        var that = this;
        var html = render('chat-input-template');
        that.$el.html(html);        
    },
    events: {
    	"click #send": "send",
        "keyup #msg": "onKeyUp",
        "click #refresh": "refresh"
    },

    send: function() {
    	var that = this;
    	var msgDetail = new Object();
    	msgDetail.msg = $('#msg').val();
        msgDetail.message = $('#msg').val();
    	msgDetail.lastIndex = App.Context.getLastIndex();
    	msgDetail.threadId=1;
        var user = App.Context.getValue("user");
        msgDetail.userName = user.userName;
    	$('#msg').val("");
        var returnedObject = this.collection.create(msgDetail, {wait: true});
    },

    onKeyUp: function(e) {
        if(e.keyCode == 13) {
            this.send();
        }
    },

    refresh: function() {
        messages.fetch({data: {lastIndex: App.Context.getLastIndex(), threadId: this.threadId}});
    }
});  