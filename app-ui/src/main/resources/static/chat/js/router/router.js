var leftNavView = new LeftNavView();
var chatView = new ChatView();
var chatInputView = new ChatInputView();


 var Router = Backbone.Router.extend({
    routes: {
        'index': 'home',
        '': 'home',
        'loginComplete': 'loginComplete'
    }
});

var router = new Router();

router.on('route:home', function() {
    leftNavView.render();   
    chatView.render();   
    chatInputView.render();
});
router.on('route:logoutComplete', function() {console.log('in Router');
    loginView.render();
});