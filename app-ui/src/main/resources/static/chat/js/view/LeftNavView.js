var LeftNavView = Backbone.View.extend({
    el: '#left-nav',
    render: function(user) {
        var html = render('left-nav-template', {user: user});
        this.$el.html(html);
        /*var blogs = new Blogs();
        blogs.fetch({
            success: function(blogs) {
                App.Context.setValue("blogs", blogs.models);
                //var template = _.template($('#blog-list-template').html());
                var html = render('blog-list-template', {blogs: blogs.models, formatDate: formatDate});
                that.$el.html(html);        
            }
        });*/                
    }
});