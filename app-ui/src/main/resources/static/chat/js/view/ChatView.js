var ChatView = Backbone.View.extend({
    el: '#viewport',
    render: function() {
        var that = this;
        var blogs = new Blogs();
        blogs.fetch({
            success: function(blogs) {
                App.Context.setValue("blogs", blogs.models);
                //var template = _.template($('#blog-list-template').html());
                var html = render('blog-list-template', {blogs: blogs.models, formatDate: formatDate});
                that.$el.html(html);        
            }
        });                
    }
});