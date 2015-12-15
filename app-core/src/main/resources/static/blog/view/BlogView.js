var BlogView = Backbone.View.extend({
    el: '#viewport',
    render: function() {
        var that = this;
        var blogs = new Blogs();
        blogs.fetch({
            success: function(blogs) {
                //var template = _.template($('#blog-list-template').html());
                var html = render('blog-list-template', {blogs: blogs.models, formatDate: formatDate});
                that.$el.html(html);        
            }
        });                
    }
});
var BlogPostView = Backbone.View.extend({
    el: '#viewport',
    render: function(id) {
        var that = this;
        var blogPost = new BlogPost({id: id});
        blogPost.fetch({
            success: function(blog) {
                var html = render('blog-post-template', {blog: blog, formatDate: formatDate});
                that.$el.html(html);        
            }
        });                
    }
});

var CreatePostView = Backbone.View.extend({
    el: '#viewport',
    
    render: function(id) {
        var that = this;
        if (id) {
           var blogPost = new BlogPost({id: id});
            blogPost.fetch({
                success: function(blogPost) {
                    console.log('title -> ' + blogPost.get("title"));
                    var html = render('create-blog-template', {blog: blogPost, formatDate: formatDate}, getAttribute);
                    that.$el.html(html);        
                    $("#rtContent").Editor("setText", blogPost.get("content"));
                }
            });   
        } else {
            var blogPost = new BlogPost();
            var html = render('create-blog-template', {blog: blogPost, getAttribute: getAttribute});
            that.$el.html(html);                  
        }
    },
    events: {
        'submit .edit-post-form': 'savePost'
    },
    savePost: function(ev) {
        var postDetail = $(ev.currentTarget).serializeObject();
        postDetail.content = $("#rtContent").Editor("getText");
        console.log(postDetail);
        blogPost = new BlogPost();
        blogPost.save(postDetail, {
            success: function(post) {
                console.log(post);
                blogPost.id = post.id;
                router.navigate('', {trigger: true});
            }
        });
        return false;
    },

});


function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = '/blog/templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';
        var tmpl_string;
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = _.template(tmpl_string);
    }
    return render.tmpl_cache[tmpl_name](tmpl_data);
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

function getAttribute(blog, id) {
    if(typeof blog != 'undefined')
        return blog.get(id);
}