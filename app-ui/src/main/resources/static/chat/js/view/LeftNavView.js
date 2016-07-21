var LeftNavView = Backbone.View.extend({
    el: '#left-nav',
    render: function() {
        var that = this;
        var html = render('left-nav-template');
        that.$el.html(html);
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