var ChatInputView = Backbone.View.extend({
    el: '#chatInputView',
    render: function() {
        var that = this;
        var html = render('chat-input-template');
        that.$el.html(html);        
    }
});