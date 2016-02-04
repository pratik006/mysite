var loginView = new LoginView();
var blogView = new BlogView();
var blogPostView = new BlogPostView();
var createPostView = new CreatePostView();

 var Router = Backbone.Router.extend({
    routes: {
        '': 'home',
        'blogPost/:id': 'blogPost',
        'create': 'create',
        'edit/:id': 'edit',
    }
});

var router = new Router();

router.on('route:home', function() {
    blogView.render();
    loginView.render();
});
router.on('route:blogPost', function(id) {
    blogPostView.render(id);
});
router.on('route:create', function() {
    createPostView.render();
});
router.on('route:edit', function(id) {
    createPostView.render(id);
});