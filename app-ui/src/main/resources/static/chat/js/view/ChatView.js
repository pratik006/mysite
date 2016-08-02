var ChatView = Backbone.View.extend({
    collection: messages,
    initialize: function() {
        this.collection.on('change', this.render, this);
    },
    el: '#chatView',
    render: function() {
        console.log('render chat-view');
        var that = this;
        var threadId = 1;
        var lastIndex = App.Context.getValue(threadId);
        messages.fetch({data: {lastIndex: lastIndex, threadId: 1},
            success: function(messages) {
                var html = render('chat-view-template', {messages: messages.models});
                that.$el.html(html);
                $("#chatView").animate({ scrollTop: $('#chatView').prop("scrollHeight")}, 1000);
            }
        });
    }
});