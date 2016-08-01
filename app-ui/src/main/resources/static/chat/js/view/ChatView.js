var ChatView = Backbone.View.extend({
    el: '#chatView',
    render: function() {
        var that = this;
        var messages = new Messages();
        messages.fetch({data: {lastIndex: -1},
            success: function(messages) {
                var html = render('chat-view-template', {messages: messages.models});
                that.$el.html(html);
            }
        });
    }
});