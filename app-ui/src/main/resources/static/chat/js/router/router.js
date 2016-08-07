var loginView = new LoginView();
var homeView = new HomeView();
/*var leftNavView = new LeftNavView();
var chatView = new ChatView();
var chatInputView = new ChatInputView();*/


 var Router = Backbone.Router.extend({
    routes: {
        'index': 'home',
        '': 'home',
        'home': 'home',
        'loginComplete': 'loginComplete'
    }
});

var router = new Router();

router.on('route:home', function() {
    if (App.Context.getValue('user')) {
        homeView.render(1);
    } else {
        loginView.render();
    }
});
router.on('route:logoutComplete', function() {console.log('in Router');
    loginView.render();
});