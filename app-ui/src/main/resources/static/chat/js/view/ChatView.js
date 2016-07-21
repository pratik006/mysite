var ChatView = Backbone.View.extend({
    el: '#chatView',
    render: function() {
        var that = this;
        var html = render('chat-view-template');
        that.$el.html(html);        
    }
});