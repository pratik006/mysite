var leftNavView = new LeftNavView();


 var Router = Backbone.Router.extend({
    routes: {
        'route:defaultRoute': 'home',
        'loginComplete': 'loginComplete'
    }
});

var router = new Router();

router.on('route:home', function() {
    console.log('in Router');
    leftNavView.render();   
});
router.on('route:logoutComplete', function() {console.log('in Router');
    loginView.render();
});