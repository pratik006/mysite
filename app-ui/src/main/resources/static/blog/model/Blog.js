var Blogs = Backbone.Collection.extend({
    url: '/rest/blog'
});
var BlogPost = Backbone.Model.extend({
    urlRoot: '/rest/blog',
    idAttribute: 'id'
});

var loggedUser;