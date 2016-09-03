/// <reference path="../typings/browser/ambient/es6-shim/index.d.ts" />
System.register(['@angular/platform-browser-dynamic', '@angular/core', '@angular/common', './app', '@angular/forms', './routes', '@angular/http', 'angular2-jwt', './auth-guard'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var platform_browser_dynamic_1, core_1, common_1, app_1, forms_1, routes_1, http_1, angular2_jwt_1, auth_guard_1;
    return {
        setters:[
            function (platform_browser_dynamic_1_1) {
                platform_browser_dynamic_1 = platform_browser_dynamic_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (app_1_1) {
                app_1 = app_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (routes_1_1) {
                routes_1 = routes_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (angular2_jwt_1_1) {
                angular2_jwt_1 = angular2_jwt_1_1;
            },
            function (auth_guard_1_1) {
                auth_guard_1 = auth_guard_1_1;
            }],
        execute: function() {
            platform_browser_dynamic_1.bootstrap(app_1.AppComponent, [
                forms_1.disableDeprecatedForms(),
                forms_1.provideForms(),
                http_1.HTTP_PROVIDERS,
                routes_1.APP_ROUTER_PROVIDER,
                core_1.bind(common_1.LocationStrategy).toClass(common_1.HashLocationStrategy),
                core_1.provide(angular2_jwt_1.AuthConfig, { useFactory: function () {
                        return new angular2_jwt_1.AuthConfig();
                    } }),
                angular2_jwt_1.AuthHttp,
                auth_guard_1.AuthGuard
            ]);
        }
    }
});
//# sourceMappingURL=boot.js.map