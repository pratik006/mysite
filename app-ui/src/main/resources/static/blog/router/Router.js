var loginView = new LoginView();
var logoutView = new LogoutView();
var footerView = new FooterView();
var blogView = new BlogView();
var blogPostView = new BlogPostView();
var createPostView = new CreatePostView();


 var Router = Backbone.Router.extend({
    routes: {
        '': 'home',
        'loginComplete': 'loginComplete',
        'blogPost/:id/:code': 'blogPost',
        'blogPost/:id': 'blogPost',
        'create': 'create',
        'edit/:id': 'edit',
        'edit/:id/:code': 'edit',
        'album/:id':'album'
    }
});

var router = new Router();

router.on('route:home', function() {
    blogView.render();
    if (!user) {
        loginView.render();
    }
    footerView.render();
});
router.on('route:loginComplete', function() {
    logoutView.render();
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
router.on('route:album', function(id) {
    var blogPostAlbumView = new BlogPostAlbumView();
    blogPostAlbumView.render(id);
});