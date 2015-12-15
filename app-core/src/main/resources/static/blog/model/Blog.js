 var Blogs = Backbone.Collection.extend({
    url: '/rest/blogs'
});
var BlogPost = Backbone.Model.extend({
    urlRoot: '/rest/blogs',
    idAttribute: 'id'
});