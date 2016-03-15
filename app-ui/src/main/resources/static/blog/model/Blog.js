var Blogs = Backbone.Collection.extend({
    url: '/rest/blog'
});
var BlogComments = Backbone.Collection.extend({
    url: '/rest/blog/comment'
});

var BlogPost = Backbone.Model.extend({
    urlRoot: '/rest/blog',
    idAttribute: 'id'
});

var BlogComment = Backbone.Model.extend({
    urlRoot: '/rest/blog/comment',
    idAttribute: 'id'
});

var loggedUser;