var HomeView = Backbone.View.extend({
	leftNavView: '',
	initialize: function() {
		this.leftNavView = new LeftNavView();;
		this.chatView = new ChatView(1);
		this.chatInputView = new ChatInputView(1);
	},
    el: '.row',
    render: function() {
        var html = render('home-layout-template');
        $(html).find(this.leftNavView.el);
        this.$el.html(html);
        //this.$el.html(this.template());
        var user = App.Context.getValue('user');
        this.leftNavView.$el = this.$('#left-nav');
		this.leftNavView.render(user);
		this.chatView.$el = this.$('#chatView > div > ul');
		this.chatView.render();
		this.chatInputView.$el = this.$('#chatInputView');
		this.chatInputView.render();
		this.chatInputView.delegateEvents();
    }
});