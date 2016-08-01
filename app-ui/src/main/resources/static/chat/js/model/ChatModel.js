var Messages = Backbone.Collection.extend({
    url: 'http://localhost:8080/rest/chat',
    parse: function(data) {
    	return data.messages;
  	}
});

var Message = Backbone.Model.extend({
    urlRoot: 'http://localhost:8080/rest/chat',
    idAttribute: 'id'
});
