System.register(['@angular/router', './components/todo/todo', './components/about/about', './components/profile/profile', './auth-guard'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var router_1, todo_1, about_1, profile_1, auth_guard_1;
    var appRoutes, APP_ROUTER_PROVIDER;
    return {
        setters:[
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (todo_1_1) {
                todo_1 = todo_1_1;
            },
            function (about_1_1) {
                about_1 = about_1_1;
            },
            function (profile_1_1) {
                profile_1 = profile_1_1;
            },
            function (auth_guard_1_1) {
                auth_guard_1 = auth_guard_1_1;
            }],
        execute: function() {
            exports_1("appRoutes", appRoutes = [
                { path: '', component: todo_1.Todo },
                { path: 'about/:id', component: about_1.About },
                { path: 'profile', component: profile_1.Profile, canActivate: [auth_guard_1.AuthGuard] }
            ]);
            exports_1("APP_ROUTER_PROVIDER", APP_ROUTER_PROVIDER = router_1.provideRouter(appRoutes));
        }
    }
});
//# sourceMappingURL=routes.js.map