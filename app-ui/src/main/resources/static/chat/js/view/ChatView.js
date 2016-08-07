var ChatView = Backbone.View.extend({
    collection: messages,
    threadId: 1,
    lastIndex: '',
    interval: 10000,
    initialize: function(threadId) {
        this.threadId = threadId;
        this.collection.on('change add', this.render, this);
        lastIndex = App.Context.getLastIndex();
        this.autopoll();
    },
    el: '#chatView > div > ul',
    render: function(message) {
        //console.log('render'+message);
        var that = this;
        this.lastIndex = App.Context.getLastIndex();

        if (message && message.id) {
            //console.log(message.get("id")+' - '+message.get("msg"));
            var that = this;
            var contacts = App.Context.getValue("contacts");
            var html = render('chat-view-template', {messages: [message], contacts: contacts});
            that.$el.append(html);
            if (this.lastIndex - message.id < 5) {
                $("#chatView").animate({ scrollTop: $('#chatView').prop("scrollHeight")}, 1000);
                this.interval = 10000;
            }
        } else {
            messages.fetch({data: {lastIndex: this.lastIndex, threadId: this.threadId},
                success: function(messages) {}
            });
            $.get('./sounds/notification-sound', function(result) {
                $.playSound(result);
            });
        }
    },

    autopoll: function() {
        var that = this;

        window.setTimeout(function () {
            messages.fetch({data: {lastIndex: App.Context.getLastIndex(), threadId: that.threadId}});
            that.interval = (that.interval + 10000) % 60000;
            console.log(that.interval);
            that.autopoll();
        }, this.interval);        
    }
});

