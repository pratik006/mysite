var LoginView = Backbone.View.extend({
    el: '.dropdown',
    render: function() {
        var that = this;        
        var html = render('login-form-template');
        that.$el.html(html);                
    },

    events: {
        "click #btnSignIn": "signIn"
    },

    signIn: function () {
        var that = this;
        $.ajax({
          type: "POST",
          url: ACTION_PATHS['login'],
          data: $('#navbar-form').serialize(),
          success: function(result) {
        	  try {
        		  user = JSON.parse(result);
        		  loggedUser = user;
                  //that.$el.toggle();
                  //$("#signInDropdownMenu").text("Sign Out");
                  if ($('#mnuCreate').length == 0) {
                    $('.navbar-collapse ul').append('<li id="mnuCreate"><a href="#create">Create</a></li>');
                  }
                  
                  $(' <div id="success-alert" class="alert alert-success fade in">'
                		  +'<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
                	      +'<strong>Success!</strong> Successfully Logged in.'
                	      +'</div>'
                	      ).insertAfter('nav');
                  $("#success-alert").fadeTo(5000, 500).slideUp(500, function(){
                	  $("#success-alert").alert('close');
                  });
                  router.navigate('loginComplete', {trigger: true});
                  logoutView.render();
        	  }catch(error) {
        		  console.log('error occurred while logging in');
        	  }
          }
        });
        
        
    },
});
var LogoutView = Backbone.View.extend({
    el: '.dropdown',
    render: function() {
        var that = this;        
        var html = render('logout-form-template');
        that.$el.html(html);                
    },

    events: {
        "click #logout": "logout"
    },

    logout: function () {
        var that = this;
        $.ajax({
          type: "POST",
          url: ACTION_PATHS['logout'],
          success: function(result) {
              console.log('logged out.');
              user = null;
              $("#mnuCreate").remove();
              router.navigate('logoutComplete', {trigger: true});
          }
        });
        
        
    },
});

var FooterView = Backbone.View.extend({
    el: 'footer',
    render: function() {
        var that = this;        
        var html = render('footer-template');
        that.$el.html(html);                
    }
});

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
    initialize: function() {
        this.inner = new BlogCommentsView();
    },

    el: '#viewport',
    render: function(id) {
        var that = this;
        var blogPost = new BlogPost({id: id});
        blogPost.fetch({
            success: function(blog) {
                var html = render('blog-post-template', {blog: blog, formatDate: formatDate, loggedUser: loggedUser, user: user});
                that.$el.html(html);
                //inner view rendering
                that.inner.$el = this.$('.comments');
                that.inner.render(id);
                that.$('.comments').html(that.inner.$el.html());
            }
        });                
    },
    events: {
        "submit #comment-form": "saveComment"
    },
    saveComment: function(ev) {
        var that = this;        
        var postDetail = $(ev.currentTarget).serializeObject();
        var blogComment = new BlogComment();
        blogComment.save(postDetail, {
            success: function(comment) {
                blogComment.id = comment.id;
                that.inner.render(postDetail.blogId);
                that.$('#comment-form')[0].reset();
            },
            error: function (model, xhr, options) {
                console.log(xhr.result.Errors);
            } 
        });
        return false;
    }
});

var BlogCommentsView = Backbone.View.extend({
    el: '.comments',
    render: function(blogId) {
        var that = this;
        var blogComments = new BlogComments();
        blogComments.fetch({data: {blogId: blogId}, 
            success: function(comments) {
                var html = render('blog-post-comments-template', {comments: comments.models, formatDate: formatDate});
                that.$el.html(html);     
            }
        });
        return this;                
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
        blogPost = new BlogPost();
        blogPost.save(postDetail, {
            success: function(post) {
                blogPost.id = post.id;
                router.navigate('', {trigger: true});
            }
        });
        return false;
    },

});

var BlogPostAlbumView = Backbone.View.extend({
    el: '#viewport',
    render: function(blogId) {
        console.log('before fetching BlogPostLinks for blog-id: '+blogId);
        var that = this;
        var blogPostLinks = new BlogPostLinks();
        blogPostLinks.fetch({data: {blogId: blogId},
            success: function(blogPostLinks) {
                console.log(blogPostLinks);
                var html = render('blog-post-album-template', {blogPostLinks: blogPostLinks.models});
                that.$el.html(html);
            }
        });                
    }
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